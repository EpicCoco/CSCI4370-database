package uga.cs4370.mydbimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uga.cs4370.mydb.Cell;
import uga.cs4370.mydb.Predicate;
import uga.cs4370.mydb.RA;
import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.RelationBuilder;
import uga.cs4370.mydb.Type;

public class MyRAImpl implements RA {

    /**
     * Performs cartisian product on relations rel1 and rel2.
     * 
     * @return The resulting relation after applying cartisian product.
     * 
     * @throws IllegalArgumentException if rel1 and rel2 have common attibutes.
     */
    @Override
    public Relation cartesianProduct(Relation rel1, Relation rel2) throws IllegalArgumentException {
        for (int i = 0; i < rel1.getAttrs().size(); i++) {
            for (int j = 0; j < rel2.getAttrs().size(); j++) {
                if (rel1.getAttrs().get(i).equals(rel2.getAttrs().get(j))) {
                    throw new IllegalArgumentException("CartesianProduct Error: two relations have common attributes");
                }
            }
        }

        List<String> nameList = rel1.getAttrs();
        List<Type> typeList = rel1.getTypes();
        for (int i = 0; i < rel2.getAttrs().size(); i++) {
            nameList.add(rel2.getAttrs().get(i));
            typeList.add(rel2.getTypes().get(i));
        }

        Relation temp = new RelationBuilder().attributeNames(nameList).attributeTypes(typeList).build();

        List<Cell> row = new ArrayList<Cell>();
        List<Cell> rel1row = new ArrayList<Cell>();
        for (int i = 0; i < rel1.getSize(); i++) {   
            rel1row = rel1.getRow(i);
            for (int j = 0; j < rel2.getSize(); j++) {
                for (int l = 0; l < rel1.getRow(i).size(); l++) {
                    row.add(rel1.getRow(i).get(l));
                }
                for (int rel2col = 0; rel2col < rel2.getAttrs().size(); rel2col ++) {
                     row.add(rel2.getRow(j).get(rel2col));
                }
            temp.insert(row);
            row = new ArrayList<Cell>();
            }
        }
        return temp;
    } //cartesianProduct

    /**
     * Performs the set difference operaion on the relations rel1 and rel2.
     * 
     * @return The resulting relation after applying the set difference operation.
     * 
     * @throws IllegalArgumentException If rel1 and rel2 are not compatible.
     */
    @Override
    public Relation diff(Relation rel1, Relation rel2) throws IllegalArgumentException {
        // Check if relations are compatible 
        if (!areCompatible(rel1, rel2)) {
            throw new IllegalArgumentException("Difference Error: Relations are not compatible.");
        }

        // Create a new relation with the same schema as rel1
        Relation diffResult = new RelationBuilder()
                .attributeNames(rel1.getAttrs())
                .attributeTypes(rel1.getTypes())
                .build();

        // Add rows from rel1 that are not present in rel2 to the new relation
        for (int i = 0; i < rel1.getSize(); i++) {
            if (!containsRow(rel2, rel1.getRow(i))) {
                diffResult.insert(rel1.getRow(i));
            }
        }

        return diffResult;
    }

    /**
     * Peforms natural join on relations rel1 and rel2.
     * Implemented by Codey Borrelli
     * 
     * @return The resulting relation after applying natural join.
     */
    @Override
    public Relation join(Relation rel1, Relation rel2) {
        String sharedAttribute = "";
        List<String> rel1attrs = rel1.getAttrs(), rel2attrs = rel2.getAttrs();
        List<Type> rel1types = rel1.getTypes(), rel2types = rel2.getTypes();
        //find shared attribute
        for (int i = 0; i < rel1attrs.size(); i++) {
            String attr1 = rel1attrs.get(i);
            for (int j = 0; j < rel2attrs.size(); j++) {
                //check name AND type
                if (attr1.equals(rel2attrs.get(j)) && rel1types.get(i) == rel2types.get(j)) {
                    sharedAttribute = attr1;
                    break;
                } //if
            } //for
        } //for
        //case that relations didn't share attribute + type (return empty set)
        if (sharedAttribute.equals("")) {
            rel1attrs.addAll(rel2attrs);
            rel1types.addAll(rel2types);
            Relation toReturn = new RelationBuilder().attributeNames(rel1attrs).attributeTypes(rel1types).build();
            return toReturn;
        } //if
        //create temp1 relation
        Relation temp1 = new RelationBuilder().attributeNames(rel1attrs).attributeTypes(rel1.getTypes()).build();
        temp1 = rename(temp1, Arrays.asList(new String[]{sharedAttribute}), Arrays.asList(new String[]{"1." + sharedAttribute}));
        for (int i = 0; i < rel1.getSize(); i++) temp1.insert(rel1.getRow(i));
        //create temp2 relation
        Relation temp2 = new RelationBuilder().attributeNames(rel2attrs).attributeTypes(rel2.getTypes()).build();
        temp2 = rename(temp2, Arrays.asList(new String[]{sharedAttribute}), Arrays.asList(new String[]{"2." + sharedAttribute}));
        for (int i = 0; i < rel2.getSize(); i++) temp2.insert(rel2.getRow(i));
        //get cross product
        Relation cpRelation = cartesianProduct(temp1, temp2);
        //make final relation (select rows with common attribute names)
        int l = cpRelation.getAttrIndex("1." + sharedAttribute), r = cpRelation.getAttrIndex("2." + sharedAttribute);
        List<String> returnAttrs = cpRelation.getAttrs();
        returnAttrs.remove(r);
        List<Type> returnTypes = cpRelation.getTypes();
        returnTypes.remove(r);
        Relation toReturn = new RelationBuilder().attributeNames(returnAttrs).attributeTypes(returnTypes).build();
        for (int i = 0; i < cpRelation.getSize(); i++) {
            List<Cell> row = cpRelation.getRow(i);
            if (row.get(l).equals(row.get(r))) {
                row.remove(r);
                toReturn.insert(row);
            } //if
        } //for
        //clean up toReturn (rename column)
        toReturn = rename(toReturn, Arrays.asList(new String[]{"1." + sharedAttribute}), Arrays.asList(new String[]{sharedAttribute}));
        return toReturn;
    } //join

    /**
     * Performs theta join on relations rel1 and rel2 with predicate p.
     * Implemented by Codey Borrelli
     * 
     * @return The resulting relation after applying theta join.
     * 
     * @throws IllegalArgumentException if rel1 and rel2 have common attributes.
     */
    @Override
    public Relation join(Relation rel1, Relation rel2, Predicate p) throws IllegalArgumentException {
        //check for common attributes
        for (String attr : rel1.getAttrs()) {
            if (rel2.getAttrs().contains(attr)) throw new IllegalArgumentException("Join Error: Relations have common attributes");
        } //for
        //do cartesian product
        Relation cpRelation = cartesianProduct(rel1, rel2);
        return select(cpRelation, p);
    } //join

    /**
     * Performs the project operation on the relation rel
     * given the attributes list attrs.
     * Implemented by Zach Bloodworth
     * 
     * @return The resulting relation after applying the project operation.
     * 
     * @throws IllegalArgumentException If attributes in attrs are not 
     * present in rel.
     */
    @Override
    public Relation project(Relation rel, List<String> attrs) throws IllegalArgumentException {
        // throw an exception if any of the specified types are not contained in the relation
        for (String attr : attrs) {
            if (!rel.getAttrs().contains(attr)) {
                throw new IllegalArgumentException("Project Error: Attributes do not match relation schema.");
            }
        }

        // create a new list of attributes which matches the order in the original relation
        // this ensures that the new relation will preserve the original ordering of attributes, even if
        // they are provided out of order in the function parameter
        List<String> orderedAttrs = new ArrayList<String>();
        for (String attr : rel.getAttrs()) {
            if (attrs.contains(attr)) {
                orderedAttrs.add(attr);
            }
        }
        
        // initialize a list of types which correspond to the specified attributes
        List<Type> relTypes = rel.getTypes();
        List<Type> projectTypes = new ArrayList<Type>();
        for (String attr : orderedAttrs) {
            int typeIndex = rel.getAttrIndex(attr);
            Type attrType = relTypes.get(typeIndex);
            projectTypes.add(attrType);
            //projectTypes.add(rel.getTypes().get(rel.getAttrIndex(attr)));
        }
        
        
        // construct a new relation with a schema that only includes the types that are to be projected
        Relation newRel = new RelationBuilder()
                .attributeNames(attrs)
                .attributeTypes(projectTypes)
                .build();
        
        // iterate through the original relation: for each row, add a new row to the new relation
        // which only contains the specified attributes
        for (int i = 0; i < rel.getSize(); i++) {
            List<Cell> newRow = new ArrayList<Cell>();
            for (String attr : orderedAttrs) {
                if (attrs.contains(attr)) {
                    // add a cell to newRow with this attribute name and type
                    int attrIndex = rel.getAttrIndex(attr);
                    newRow.add(rel.getRow(i).get(attrIndex));
                }
            }
            newRel.insert(newRow);
        }
        

        return newRel;
    } //project

    /**
     * Renames the attributes in origAttr of relation rel to corresponding 
     * names in renamedAttr.
     * 
     * @return The resulting relation after renaming the attributes.
     * 
     * @throws IllegalArgumentException If attributes in origAttr are not present in 
     * rel or origAttr and renamedAttr do not have matching argument counts.
     */
    @Override
    public Relation rename(Relation rel, List<String> origAttr, List<String> renamedAttr) throws IllegalArgumentException {
        List<String> ogList = new ArrayList<String>();
        for (int i = 0; i < origAttr.size(); i++) {
            if (!rel.hasAttr(origAttr.get(i))) {
                throw new IllegalArgumentException("Attributes in origAttr are not present in the relation.");
            }
        }
        if (origAttr.size() != renamedAttr.size()) {
            throw new IllegalArgumentException("Original and new attributes do not have macthing counts.");
        }
        for (int i = 0; i < rel.getAttrs().size(); i++) {
            if (origAttr.contains(rel.getAttrs().get(i))) {
                int gotIt = origAttr.indexOf(rel.getAttrs().get(i));
                ogList.add(renamedAttr.get(gotIt));
            } else {
                ogList.add(rel.getAttrs().get(i));
            }
        }
        Relation newRel = new RelationBuilder().attributeNames(ogList).attributeTypes(rel.getTypes()).build();
        for (int i = 0; i < rel.getSize(); i++) {
            newRel.insert(rel.getRow(i));
        }
        return newRel;
    } //rename

    /**
     * Performs the select operation on the relation rel
     * by applying the predicate p.
     * Implemented by Zach Bloodworth
     * 
     * @return The resulting relation after applying the select operation.
     */
    @Override
    public Relation select(Relation rel, Predicate p) { 
        // initialize a new relation with types and attributes matching the operand
        Relation newRel = new RelationBuilder()
                .attributeNames(rel.getAttrs())
                .attributeTypes(rel.getTypes())
                .build();
        
        // check each row in the operand, and if it matches the predicate, add it
        // to newRel
        for (int i = 0; i < rel.getSize(); i++) {
            if (p.check(rel.getRow(i))) {
                newRel.insert(rel.getRow(i));
            }
        }

        return newRel;
    } //select

    /**
     * Performs the union operation on the relations rel1 and rel2.
     * 
     * @return The resulting relation after applying the union operation.
     * 
     * @throws IllegalArgumentException If rel1 and rel2 are not compatible.
     */
    @Override
    public Relation union(Relation rel1, Relation rel2) throws IllegalArgumentException {
        // Check compatibility of relations
        if (!areCompatible(rel1, rel2)) {
            throw new IllegalArgumentException("Union Error: Relations are not compatible.");
        } 
    
        // Create a new relation with the same schema as rel1
        Relation unionResult = new RelationBuilder()
                .attributeNames(rel1.getAttrs())
                .attributeTypes(rel1.getTypes())
                .build();
    
        // Add rows from rel1 to the new relation
        for (int i = 0; i < rel1.getSize(); i++) {
            unionResult.insert(rel1.getRow(i));
        }
    
        // Add unique rows from rel2 to the new relation
        for (int i = 0; i < rel2.getSize(); i++) {
            if (!containsRow(rel1, rel2.getRow(i))) {
                unionResult.insert(rel2.getRow(i));
            }
        }
    
        return unionResult;
    }

    /**
    * Helper method to check if relations are compatible
    * 
    * @param rel1 The first relation.
    * @param rel2 The second relation.
    * @return True if the relations are compatible, false otherwise.
    */
    private boolean areCompatible(Relation rel1, Relation rel2) {
        return rel1.getAttrs().equals(rel2.getAttrs()) && rel1.getTypes().equals(rel2.getTypes());
    }

    /**
    * Helper method for diff 
    * Checks if a relation contains a specific row.
    * 
    * @param rel The relation to check.
    * @param targetRow The row to search for.
    * @return True if the row is present in the relation, false otherwise.
    */
    private boolean containsRow(Relation rel, List<Cell> targetRow) {
        for (int i = 0; i < rel.getSize(); i++) {
            if (rel.getRow(i).equals(targetRow)) {
                return true;
            }
        }
        return false;
    } // containsRow
} //MyRAImpl

package uga.cs4370.mydbimpl;

import java.util.List;

import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.RelationBuilder;
import uga.cs4370.mydb.Type;
import java.util.ArrayList;

/**
 * Remember - don't edit anything in mydb directory, only mydpimpl!
 */

public class Driver {
    
    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        //5 interesting queries
        Query1();
        Query2();
        Query3();
        Query4();
        Query5();
    } //main

    public static void Query1() {
        MyRAImpl ra = new MyRAImpl();
        System.out.println("Query 1: List all student who received an C- in a Computer Science Course");
        //Load the three relations needed
        Relation courses = new RelationBuilder()
                .attributeNames(List.of("course_id", "title", "dept_name", "credits"))
                .attributeTypes(List.of(Type.INTEGER, Type.STRING, Type.STRING, Type.INTEGER))
                .build();
        courses.loadData("mydb_project/resources/mysql-files/course_export.csv");
        Relation takes = new RelationBuilder()
                .attributeNames(List.of("ID", "course_id", "sec_id", "semester", "year", "grade"))
                .attributeTypes(List.of(Type.INTEGER, Type.INTEGER, Type.INTEGER, Type.STRING, Type.DOUBLE, Type.STRING))
                .build();
        takes.loadData("mydb_project/resources/mysql-files/takes_export.csv");
        Relation students = new RelationBuilder()
                .attributeNames(List.of("ID", "name", "dept_name", "tot_cred"))
                .attributeTypes(List.of(Type.INTEGER, Type.STRING, Type.STRING, Type.INTEGER))
                .build();
        students.loadData("mydb_project/resources/mysql-files/student_export.csv");
        //join together the "takes" and "courses" relations
        Relation mix1 = ra.join(takes, courses);
        //select only rows in which the department name fro the class is Comp Sci and the grade received for the class was a C-
        Relation select1 = ra.select(mix1, (row) -> row.get(mix1.getAttrIndex("dept_name")).getAsString().equals("Comp. Sci."));
        Relation select2 = ra.select(select1, (row) -> row.get(select1.getAttrIndex("grade")).getAsString().equals("C-"));
        //join this relation with selected values with the ID, name, and credits of all the students
        Relation mix2 = ra.join(select2, ra.project(students, List.of("ID", "name", "tot_cred")));
        List<String> list = new ArrayList<String>();
        list.add("name");
        //project only the names of said students
        Relation rel = ra.project(mix2, list);
        rel.print();

    } //Query1

    public static void Query2() {
        MyRAImpl ra = new MyRAImpl();
        //Load the two relations needed
        System.out.println("Query 2: List the Identification Numbers for each student in the Math department with more that 120 credit hours");
        Relation courses = new RelationBuilder()
                .attributeNames(List.of("course_id", "title", "dept_name", "credits"))
                .attributeTypes(List.of(Type.INTEGER, Type.STRING, Type.STRING, Type.INTEGER))
                .build();
        courses.loadData("mydb_project/resources/mysql-files/course_export.csv");
        Relation students = new RelationBuilder()
                .attributeNames(List.of("ID", "name", "dept_name", "tot_cred"))
                .attributeTypes(List.of(Type.INTEGER, Type.STRING, Type.STRING, Type.INTEGER))
                .build();
        students.loadData("mydb_project/resources/mysql-files/student_export.csv");
        //join the courses and students relations
        Relation mix1 = ra.join(courses, students);
        //select only the rows in which the department name is Math and the student's total credits is greater than 120
        Relation select1 = ra.select(mix1, (row) -> row.get(mix1.getAttrIndex("dept_name")).getAsString().equals("Math"));
        Relation select2 = ra.select(select1, (row) -> row.get(select1.getAttrIndex("tot_cred")).getAsInt() > 120);
        //project the ID's of these students
        ra.project(select2, List.of("ID")).print();
    } //Query2

    public static void Query3() {
        MyRAImpl ra = new MyRAImpl();
        System.out.println("Query 3: Get the names of courses from the Astronomy department taught in Fall semesters");
        Relation relTeaches = new RelationBuilder()
                .attributeNames(List.of("tID", "course_id", "sec_id", "semester", "year"))
                .attributeTypes(List.of(Type.INTEGER, Type.INTEGER, Type.INTEGER, Type.STRING, Type.INTEGER))
                .build();
        relTeaches.loadData("mydb_project/resources/mysql-files/teaches_export.csv");
        Relation relCourse = new RelationBuilder()
                .attributeNames(List.of("cID", "name", "department", "credits"))
                .attributeTypes(List.of(Type.INTEGER, Type.STRING, Type.STRING, Type.INTEGER))
                .build();
        relCourse.loadData("mydb_project/resources/mysql-files/course_export.csv");
        
        //perform theta join (course.cID == teaches.course_id)
        Relation joinedRelation = ra.join(relCourse, relTeaches, 
                (row) -> row.get(relCourse.getAttrIndex("cID")).equals(row.get(relCourse.getAttrs().size() + relTeaches.getAttrIndex("course_id"))));
        //select (department == "Astronomy" && semester == "Fall")
        Relation selectedRelation  = ra.select(joinedRelation, 
                (row) -> row.get(joinedRelation.getAttrIndex("department")).getAsString().equals("Astronomy")
                && row.get(joinedRelation.getAttrIndex("semester")).getAsString().equals("Fall")
                );
        Relation result = ra.project(selectedRelation, List.of("name"));
        result.print();
    } //Query3

    public static void Query4() {
        MyRAImpl ra = new MyRAImpl();
        System.out.println("Query 4: List the names of instructors who either teach Computer Science courses or have a salary greater than $70,000");
        
        Relation relInstructor = new RelationBuilder()
                .attributeNames(List.of("ID", "name", "dept_name", "salary"))
                .attributeTypes(List.of(Type.INTEGER, Type.STRING, Type.STRING, Type.DOUBLE))
                .build();
        relInstructor.loadData("mydb_project/resources/mysql-files/instructor_export.csv");
    
        Relation relCourse = new RelationBuilder()
                .attributeNames(List.of("cID", "name", "department", "credits"))
                .attributeTypes(List.of(Type.INTEGER, Type.STRING, Type.STRING, Type.INTEGER))
                .build();
        relCourse.loadData("mydb_project/resources/mysql-files/course_export.csv");
    
        // Instructors teaching Computer Science courses
        Relation csInstructors = ra.select(relInstructor, (row) -> row.get(2).getAsString().equals("Comp. Sci."));
    
        // Instructors with a salary greater than $70,000
        Relation highSalaryInstructors = ra.select(relInstructor, (row) -> row.get(3).getAsDouble() > 70000);
    
        // Union
        Relation unionResult = ra.union(csInstructors, highSalaryInstructors);
        Relation result = ra.project(unionResult, List.of("name"));
        result.print();
    } //Query4

    public static void Query5() {
        MyRAImpl ra = new MyRAImpl();
        System.out.println("Query 5: List all department names which are hosted in a building with a room # > 400");

        // load the section relation
        Relation section = new RelationBuilder()
        .attributeNames(List.of("course_id", "sec_id", "semester", "year", "building", "room_number", "time_slot_id"))
        .attributeTypes(List.of(Type.INTEGER, Type.INTEGER, Type.STRING, Type.INTEGER, Type.STRING, Type.INTEGER, Type.STRING)).build();
        section.loadData("mydb_project/resources/mysql-files/section_export.csv");

        // load the department relation
        Relation department = new RelationBuilder()
        .attributeNames(List.of("dept_name", "building", "budget"))
        .attributeTypes(List.of(Type.STRING, Type.STRING, Type.DOUBLE)).build();
        department.loadData("mydb_project/resources/mysql-files/department_export.csv");
        
        // create a relation which includes only one attribute, "building", consisting of all buildings which have a room number > 400
        // do this by first selecting all entries from the section relation which have a room number > 400, and then projecting "building"
        // from this new relation
        Relation roomGreater400Buildings = ra.project(ra.select(section, (row) -> row.get(5).getAsInt() > 400), List.of("building"));

        // create a relation which includes only one attribute, "dept_name", consisting of all departments which are hosted in a building
        // that has a room # > 400
        // do this by first joining department with the previously created relation, and then projecting the dept_name attribute
        Relation deptJoinG400 = ra.project(ra.join(department, roomGreater400Buildings), List.of("dept_name"));
        deptJoinG400.print();
    } //Query5

} //Driver

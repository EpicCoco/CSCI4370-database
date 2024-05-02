package uga.cs4370.projback.models;

public class Actor {
    private final String actorId;

    private final String fname;

    private final String lname;

    private final String age;

    public Actor(String actorId, String fname, String lname, String age) {
        this.actorId = actorId;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
    }
  
    public String getActorId() {
        return actorId;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }
     
    public String getAge() {
        return age;
    }
    public int getAge() {
        return age;
    }
}

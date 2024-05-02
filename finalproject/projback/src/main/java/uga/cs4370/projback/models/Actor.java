package uga.cs4370.projback.models;

public class Actor {

    private String actorId;

    private String fname;

    private String lname;

    private int age;

    public Actor(String actorId, String fname, String lname, int age) {
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

    public int getAge() {
        return age;
    }
    
}

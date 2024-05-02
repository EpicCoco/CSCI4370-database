package uga.cs4370.projback.models;

public class User {
    private final String userId;

    private final String fName;

    private final String lName;

    public User(String userId, String fName, String lName) {
        this.userId = userId;
        this.fName = fName;
        this.lName = lName;
    }

    public String getUserId() {
        return userId;
    }

    public String getFName() {
        return fName;
    }

    public String getLName() {
        return lName;
    }
}

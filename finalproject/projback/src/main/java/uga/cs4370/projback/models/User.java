package uga.cs4370.projback.models;

public class User {
    private final String userId;

    private final String username;

    private final String fName;

    private final String lName;

    public User(String userId, String username, String fName, String lName) {
        this.userId = userId;
        this.username = username;
        this.fName = fName;
        this.lName = lName;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return this.username;
    }

    public String getFName() {
        return fName;
    }

    public String getLName() {
        return lName;
    }
}

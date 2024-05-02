package uga.cs4370.projback.models;

public class Award {

    private final String awardId;

    private final String awardName;

    private final String actorId;

    private final String movieId;

    public Award(String awardId, String awardName, String actorId, String movieId) {
        this.awardId = awardId;
        this.awardName = awardName;
        this.actorId = actorId;
        this.movieId = movieId;
    }

    public String getAwardId() {
        return awardId;
    }

    public String getAwardName() {
        return awardName;
    }

    public String getActorId() {
        return actorId;
    }

    public String getMovieId() {
        return movieId;
    }
}

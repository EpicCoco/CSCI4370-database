package uga.cs4370.projback.models;

public class Review {
        private final String reviewId;

    private final String rating;

    private final String text;

    private final String postDate;

    private final String movieId;

    private final String userId;

    public Review(String reviewId, String rating, String text, String postDate, String movieId, String userId) {
        this.reviewId = reviewId;
        this.rating = rating;
        this.text = text;
        this.postDate = postDate;
        this.movieId = movieId;
        this.userId = userId;
    }

    public String getReviewId() {
        return reviewId;
    }

    public String getRating() {
        return rating;
    }

    public String getText() {
        return text;
    }

    public String getPostDate() {
        return postDate;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getUserId() {
        return userId;
    }
}

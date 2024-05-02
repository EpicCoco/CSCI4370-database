package uga.cs4370.projback.models;

public class Movie {

    private final String movieId;

    private final String title;

    private final String genre;

    private final String releaseDate;

    public Movie(String movieId, String title, String genre, String releaseDate) {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    public Movie(String title) {
        this.title = title;
    }
  
    public String getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
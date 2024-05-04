package uga.cs4370.projback.services;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;
import java.util.concurrent.atomic.AtomicBoolean;


import org.springframework.stereotype.Service;

import uga.cs4370.projback.models.Actor;
import uga.cs4370.projback.models.Award;
import uga.cs4370.projback.models.Movie;

import java.sql.Timestamp;

@Service
public class ActorService {
    private final DataSource dataSource;

    public ActorService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Award> getActorsAwards(String actorId) throws SQLException {

        final String sql = "select * from award where actorID = ?";
        List<Award> awards = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, actorId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String awardId = rs.getString("awardID");
                    String awardName = rs.getString("awardName");
                    String movieId = rs.getString("movieID");
                    Award awardToAdd = new Award(awardId, awardName, actorId, movieId);
                    awards.add(awardToAdd);
                } //while
            } //try 
        } //try 
        return awards;
    }

    public List<Movie> getActorsMovies(String actorId) throws SQLException {
        final String sql = "";
        List<Movie> movies = new ArrayList<>();

        return movies;
    }

    // ryan
    public Actor getActorById(String actorId) throws SQLException {
        final String sql = "select * from actor where actorID = ?";
        Actor actor = null;

        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, actorId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String fname = rs.getString("firstName");
                    String lname = rs.getString("lastName");
                    String age = rs.getString("age");
                    actor = new Actor(actorId, fname, lname, age);
                }
            }
        }

        if (actor == null) {
            throw new SQLException("Actor not found for ID: " + actorId);
        }

        return actor;
    }

}
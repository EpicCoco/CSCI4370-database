package uga.cs4370.projback.services;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.security.Timestamp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import uga.cs4370.projback.models.Movie;
import uga.cs4370.projback.models.Award;
import uga.cs4370.projback.models.Review;

public class AwardService {
    
    private final DataSource dataSource;

    public AwardService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Award getAwardInfo(String awardId) throws SQLException {
        final String sql = "select * from award where awardId = ?";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, awardId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // awardId awardName actorId movieId
                    String awardName = rs.getString("awardName");
                    String actorId = rs.getString("actorId");
                    String movieId = rs.getString("movieId");
                    return new Award(awardId, awardName, actorId, movieId);
                }
            }
            //System.out.println("Num reviews:");
            //System.out.println(reviews.size());
            return null;
        }
    }

    public List<Award> getMovieAwards(String movieId) throws SQLException {
        final String sql = "select * from award where movieId = ?";
        List<Award> awards = new ArrayList<Award>();
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, movieId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // awardId awardName actorId movieId
                    String awardId = rs.getString("awardId");
                    String awardName = rs.getString("awardName");
                    String actorId = rs.getString("actorId");
                    awards.add(new Award(awardId, awardName, actorId, movieId));
                }
            }
            //System.out.println("Num reviews:");
            //System.out.println(reviews.size());
            return awards;
        }
    }

    public List<Award> getActorAwards(String actorId) throws SQLException {
        final String sql = "select * from award where actorId = ?";
        List<Award> awards = new ArrayList<Award>();
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, actorId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // awardId awardName actorId movieId
                    String awardId = rs.getString("awardId");
                    String awardName = rs.getString("awardName");
                    String movieId = rs.getString("movieId");
                    awards.add(new Award(awardId, awardName, actorId, movieId));
                }
            }
            //System.out.println("Num reviews:");
            //System.out.println(reviews.size());
            return awards;
        }
    }

}

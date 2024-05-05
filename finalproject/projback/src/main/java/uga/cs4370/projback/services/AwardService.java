package uga.cs4370.projback.services;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import uga.cs4370.projback.models.Movie;
import uga.cs4370.projback.models.Award;
import uga.cs4370.projback.models.Review;

@Service
public class AwardService {
    
    private final DataSource dataSource;

    public AwardService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Award getAwardInfo(String awardId) throws SQLException {
        final String sql = "select * from award where awardID = ?";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, awardId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // awardId awardName actorId movieId
                    String awardName = rs.getString("awardName");
                    String actorId = rs.getString("actorID");
                    String movieId = rs.getString("movieID");
                    return new Award(awardId, awardName, actorId, movieId);
                }
            }
            //System.out.println("Num reviews:");
            //System.out.println(reviews.size());
            return null;
        }
    }

    public List<Award> getMovieAwards(String movieId) throws SQLException {
        final String sql = "select * from award where movieID = ?";
        List<Award> awards = new ArrayList<Award>();
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, movieId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // awardId awardName actorId movieId
                    String awardId = rs.getString("awardID");
                    String awardName = rs.getString("awardName");
                    String actorId = rs.getString("actorID");
                    awards.add(new Award(awardId, awardName, actorId, movieId));
                }
            }
            //System.out.println("Num reviews:");
            //System.out.println(reviews.size());
            return awards;
        }
    }

    public List<Award> getActorAwards(String actorId) throws SQLException {
        final String sql = "select * from award where actorID = ?";
        List<Award> awards = new ArrayList<Award>();
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, actorId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // awardId awardName actorId movieId
                    String awardId = rs.getString("awardID");
                    String awardName = rs.getString("awardName");
                    String movieId = rs.getString("movieID");
                    awards.add(new Award(awardId, awardName, actorId, movieId));
                }
            }
            //System.out.println("Num reviews:");
            //System.out.println(reviews.size());
            return awards;
        }
    }

    public String getAwardCount(String awardName) throws SQLException {
        final String sql = "select count(awardID) from award where awardName = ?";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, awardName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String count = rs.getString("count(awardID)");
                    return count;
                }
            }
            return null; // If no movie found with the given ID
        }
    }

}

package uga.menik.cs4370.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import javax.sql.DataSource;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import uga.menik.cs4370.models.FollowableUser;
import uga.menik.cs4370.models.BasicPost;
import uga.menik.cs4370.models.Post;
import uga.menik.cs4370.models.User;
import uga.menik.cs4370.utility.Utility;
import uga.menik.cs4370.services.UserService;

@Service
public class ProfileService {
    private final DataSource dataSource;


    public ProfileService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Post> postList(String userId) throws SQLException{
        List<Post> postList = new ArrayList<>();
        String sql = "select * from post where userId = ? order by postDate desc";
        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String postId = rs.getString("postId");
                    String postDate = rs.getString("postDate");
                    postDate = formatDate(postDate);
                    String postText = rs.getString("postText");
                    Boolean isHearted = isHearted(postId, userId);
                    Boolean isBookmarked = isBookmarked(postId, userId);
                    User correctUser = returnUserInfo(userId);
                    int heartCount = currentHeartCount(postId);
                    int commentCount = currentCommentCount(postId);
                    Post newPost = new Post(postId, postText, postDate, correctUser, heartCount, commentCount, isHearted, isBookmarked);
                    postList.add(newPost);
                }
            }
            
        }
        return postList;
    }

    public Boolean isHearted(String postId, String currentUser) throws SQLException{
        String sql = "select count(*) from heart where postId = ? and userId = ?";
        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, postId);
                pstmt.setString(2, currentUser);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        return count > 0;
                    }   
                }
        }
        return false;
    }

    public Boolean isBookmarked(String postId, String currentUser) throws SQLException {
        String sql = "select count(*) from bookmark where postId = ? and userId = ?";
        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, postId);
                pstmt.setString(2, currentUser);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        return count > 0;
                    }   
                }
        }
        return false;
    }

    public int currentHeartCount(String postId) throws SQLException {
        String sql = "select count(userId) from heart where postId = ?";
        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, postId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        return count;
                    }   
                }
            }
        return -1;
    }

    public int currentCommentCount(String postId) throws SQLException {
        String sql = "select count(userId) from comment where postId = ?";
        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, postId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        return count;
                    }   
                }
            }
        return -1;
    }

    public User returnUserInfo(String userId) throws SQLException {
        String sql = "select firstname, lastname from user where userId = ?";
        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, userId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        String fname = rs.getString(1);
                        String lname = rs.getString(2);
                        User myUser = new User(userId, fname, lname);
                        return myUser;
                    }
                }
            }
            return null;
    }

    public String formatDate(String date) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Format date into desired output format
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy, hh:mm a");
        String outputDateStr;
        try {
            outputDateStr = outputFormat.format(inputFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return "error";
        }
        return outputDateStr;
    }
    
}

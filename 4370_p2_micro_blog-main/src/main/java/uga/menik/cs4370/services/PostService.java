/**
Copyright (c) 2024 Sami Menik, PhD. All rights reserved.

This is a project developed by Dr. Menik to give the students an opportunity to apply database concepts learned in the class in a real world project. Permission is granted to host a running version of this software and to use images or videos of this work solely for the purpose of demonstrating the work to potential employers. Any form of reproduction, distribution, or transmission of the software's source code, in part or whole, without the prior written consent of the copyright owner, is strictly prohibited.
*/
package uga.menik.cs4370.services;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCreation;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import uga.menik.cs4370.models.Post;
import uga.menik.cs4370.models.ExpandedPost;
import uga.menik.cs4370.models.User;
import uga.menik.cs4370.models.Comment;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import uga.menik.cs4370.models.FollowableUser;
import uga.menik.cs4370.utility.Utility;

/**
 * This service contains people related functions.
 */
@Service
@SessionScope
public class PostService {
    
    private final DataSource dataSource;
    private final UserService userService;

    public PostService(DataSource dataSource, UserService userService) {
        this.dataSource = dataSource;
        this.userService = userService;
    }

    /**
     * This function should query and return all users that 
     * are followable. The list should not contain the user 
     * with id userIdToExclude.
     */
    public void makePost(String userId, String postText) throws SQLException {
        final String sql = "insert into post (userId, postDate, postText) values (?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Following line replaces the first place holder with username.
            System.out.println("Post statement 2");
            java.sql.Timestamp currentDate = new java.sql.Timestamp(System.currentTimeMillis());
            pstmt.setString(1, userId);
            pstmt.setTimestamp(2, currentDate);
            pstmt.setString(3, postText);
            System.out.println("Post statement 3");

            int numChanged = pstmt.executeUpdate();
            if (numChanged > 0) {
                System.out.println("Post creation successful.");
            } else {
                System.out.println("Post creation failed.");
            }
        }
    }

    /**
     * The userId parameter is the id of the current logged in
     * user, which is used to check for any posts from users that they follow.
     */
    public List<Post> getPostsFromFollowed(int userId) throws SQLException{
        final String sql = "select * from post, follow where follow.followerUserId = ? and post.userId = follow.followeeUserId ORDER BY post.postDate DESC"; // ryan change here "order by"
        List<Post> posts = new ArrayList<Post>();
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Following line replaces the first place holder with username.
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // id content date user
                    String postId = rs.getString("postId");
                    String postContent = rs.getString("postText");
                    String postDate = rs.getTimestamp("postDate").toString();
                    //System.out.println("POST DATE" + postDate);
                    postDate = formatDate(postDate);
                    //System.out.println("POST DATE" + postDate);
                    User postUser = userService.getUserById(rs.getInt("userId"));
                    // need to add functionality which provides accurate values for hearts, comments, and is hearted and is bookmarked
                    int numHearts = getHeartsCount(postId);
                    int numComments = getCommentsCount(postId);
                    boolean isHearted = getIsHearted(postId, userService.getLoggedInUser().getUserId());
                    boolean isBookmarked = getIsBookmarked(postId, userService.getLoggedInUser().getUserId());
                    posts.add(new Post(postId, postContent, postDate, postUser, numHearts, numComments, isHearted, isBookmarked));
                }
            }
            System.out.println("Num posts:");
            System.out.println(posts.size());
            return posts;
        }
    }

    /**
     * The query "select * from post" gets all posts from the database.
     * This method is used by the HashtagSearchController to get all
     * posts, where it then further filters them based on the 
     * hashtag filter provided. 
     */
    public List<Post> getAllPosts() throws SQLException{
        final String sql = "select * from post";
        List<Post> posts = new ArrayList<Post>();
        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // id content date user
                    String postId = rs.getString("postId");
                    String postContent = rs.getString("postText");
                    String postDate = rs.getTimestamp("postDate").toString();
                    User postUser = userService.getUserById(rs.getInt("userId"));
                    int numHearts = getHeartsCount(postId);
                    int numComments = getCommentsCount(postId);
                    boolean isHearted = getIsHearted(postId, userService.getLoggedInUser().getUserId());
                    boolean isBookmarked = getIsBookmarked(postId, userService.getLoggedInUser().getUserId());
                    posts.add(new Post(postId, postContent, postDate, postUser, numHearts, numComments, isHearted, isBookmarked));
                }
            }
            System.out.println("Num posts:");
            System.out.println(posts.size());
            return posts;
        }
    }

    /**
     * 
     * @param postId the id of the post to calculate like count for
     * @return
     */
    private int getHeartsCount(String postId) throws SQLException {
        final String sql = "select count(*) from heart where postId = ?";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Following line replaces the first place holder with username.
            pstmt.setString(1, postId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt("count(*)");
                }
            }
        }
        return -1;
    }

    private int getCommentsCount(String postId) throws SQLException {
        final String sql = "select count(*) from comment where postId = ?";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Following line replaces the first place holder with username.
            pstmt.setString(1, postId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    return rs.getInt("count(*)");
                }
            }
        }
        return -1;
    }

    private boolean getIsHearted(String postId, String userId) throws SQLException {
        final String sql = "select * from heart where postId = ? and userId = ?";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Following line replaces the first place holder with username.
            pstmt.setString(1, postId);
            pstmt.setString(2, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.print("IS HEARTED IS TRUE: POSTID = ");
                    System.out.print(postId);
                    System.out.print(", USERID = ");
                    System.out.println(userId);
                    return true;
                }
            }
        }
        System.out.print("IS HEARTED IS FALSE: POSTID = ");
        System.out.print(postId);
        System.out.print(", USERID = ");
        System.out.println(userId);
        return false;
    }

    private boolean getIsBookmarked(String postId, String userId) throws SQLException {
        final String sql = "select * from bookmark where postId = ? and userId = ?";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Following line replaces the first place holder with username.
            pstmt.setString(1, postId);
            pstmt.setString(2, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addLike(String postId, String userId) throws SQLException {
        final String sql = "insert into heart values (?,?)";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Following line replaces the first place holder with username.
            pstmt.setString(1, postId);
            pstmt.setString(2, userId);
            int numChanged = pstmt.executeUpdate();
            if (numChanged > 0) {
                System.out.println("Like creation successful.");
            } else {
                System.out.println("Like creation failed.");
            }
        }
    }

    public void removeLike(String postId, String userId) throws SQLException {
        final String sql = "delete from heart where postId = ? and userId = ?";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Following line replaces the first place holder with username.
            pstmt.setString(1, postId);
            pstmt.setString(2, userId);
            int numChanged = pstmt.executeUpdate();
            if (numChanged > 0) {
                System.out.println("Like removal successful.");
            } else {
                System.out.println("Like removal failed.");
            }
        }
    }

    public void addBookmark(String postId, String userId) throws SQLException {
        final String sql = "insert into bookmark values (?,?)";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Following line replaces the first place holder with username.
            pstmt.setString(1, postId);
            pstmt.setString(2, userId);
            int numChanged = pstmt.executeUpdate();
            if (numChanged > 0) {
                System.out.println("Bookmark creation successful.");
            } else {
                System.out.println("Bookmark creation failed.");
            }
        }
    }

    public void removeBookmark(String postId, String userId) throws SQLException {
        final String sql = "delete from bookmark where postId = ? and userId = ?";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Following line replaces the first place holder with username.
            pstmt.setString(1, postId);
            pstmt.setString(2, userId);
            int numChanged = pstmt.executeUpdate();
            if (numChanged > 0) {
                System.out.println("Bookmark removal successful.");
            } else {
                System.out.println("Bookmark removal failed.");
            }
        }
    }

    public void makeComment(String postId, String userId, String commentText) throws SQLException {
        final String sql = "insert into comment (postId, userId, commentDate, commentText) values (?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            java.sql.Timestamp currentDate = new java.sql.Timestamp(System.currentTimeMillis());
            pstmt.setString(1, postId);
            pstmt.setString(2, userId);
            pstmt.setTimestamp(3, currentDate);
            pstmt.setString(4, commentText);
            int numChanged = pstmt.executeUpdate();
            if (numChanged > 0) {
                System.out.println("Comment creation successful.");
            } else {
                System.out.println("Comment creation failed.");
            }
        }
    }

    public ExpandedPost getPostWithComments(String postId) {        
        ExpandedPost postWithComments = null;
        // got a weird error when trying to write the function without a return statement outside
        // of the try block: this could use some cleaning
        try {
            List<Comment> postComments = getCommentsForPost(postId);
            return getExpandedPost(postComments, postId);
        } catch (SQLException exception) {
            System.out.println("Failure to get post with comments: SQL Error");
            System.out.println(exception.getMessage());
        }
        return postWithComments;
    }

    private List<Comment> getCommentsForPost(String postId) throws SQLException {
        List<Comment> postComments = new ArrayList<Comment>();

        final String sql = "select * from comment where postId = ?";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Following line replaces the first place holder with username.
            pstmt.setString(1, postId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // id content date user
                    String commentId = rs.getString("commentId");
                    String commentText = rs.getString("commentText");
                    String commentDate = rs.getTimestamp("commentDate").toString();
                    commentDate = formatDate(commentDate);
                    User commentUser = userService.getUserById(rs.getInt("userId"));
                    postComments.add(new Comment(commentId, commentText, commentDate, commentUser));
                }
            }
            return postComments;
        }
    }

    private ExpandedPost getExpandedPost(List<Comment> comments, String postId) throws SQLException {
        final String sql = "select * from post where postId = ?";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Following line replaces the first place holder with username.
            pstmt.setString(1, postId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // String postId, String content, String postDate, User user, int heartsCount, int commentsCount, boolean isHearted, boolean isBookmarked, List<Comment> comments
                    // id content date user
                    String postContent = rs.getString("postText");
                    String postDate = rs.getTimestamp("postDate").toString();
                    postDate = formatDate(postDate);
                    User postUser = userService.getUserById(rs.getInt("userId"));
                    // need to add functionality which provides accurate values for hearts, comments, and is hearted and is bookmarked
                    int numHearts = getHeartsCount(postId);
                    int numComments = getCommentsCount(postId);
                    boolean isHearted = getIsHearted(postId, userService.getLoggedInUser().getUserId());
                    boolean isBookmarked = getIsBookmarked(postId, userService.getLoggedInUser().getUserId());
                    ExpandedPost postWithComments = new ExpandedPost(postId, postContent, postDate, postUser, numHearts, numComments, isHearted, isBookmarked, comments);
                    return postWithComments;
                }
                
            }
            throw new SQLException();
        }
    }
    // test ryan
    public List<Post> getBookmarkedPosts(String userId) throws SQLException {
        final String sql = "SELECT * FROM post INNER JOIN bookmark ON post.postId = bookmark.postId WHERE bookmark.userId = ?";
        List<Post> bookmarkedPosts = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // Retrieve post details from the result set
                    String postId = rs.getString("postId");
                    String postContent = rs.getString("postText");
                    String postDate = rs.getTimestamp("postDate").toString();
                    postDate = formatDate(postDate);
                    User postUser = userService.getUserById(rs.getInt("userId"));
                    int numHearts = getHeartsCount(postId);
                    int numComments = getCommentsCount(postId);
                    boolean isHearted = getIsHearted(postId, userId);
                    boolean isBookmarked = getIsBookmarked(postId, userId);
                    // Create Post object and add to list
                    bookmarkedPosts.add(new Post(postId, postContent, postDate, postUser, numHearts, numComments, isHearted, isBookmarked));
                }
            }
        }
        return bookmarkedPosts;
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

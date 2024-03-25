In PeopleService:
    In getFollowableUsers:
        "select * from user where userId != ?"
        -- The ? is replaced by the provided userIdToExclude in the method getFollowableUsers
    In changeFollowing:
        "DELETE FROM follow WHERE followerUserId = ? AND followeeUserId = ?"
        -- Replaced with appropriate userId's and used if follow data is already in the table
        "INSERT INTO follow (followerUserId, followeeUserId) VALUES (?, ?)"
        -- Replaced with appropriate userId's and used if follow data is not already in the table
    In followedInTable:
        "select count(followerUserId) from follow where followerUserId = ? AND followeeUserId = ?"
        -- This will return the count of relationships with the specific follower and followee.
        -- Will return 1 if relationship exists in the table, and 0 if not
    In lastPostDate:
        "select max(postDate) from post where userId = ?"
        -- The ? is replaced by the given userId, and this returns the most recent date the user posted

In PostService:
    In makePost:
        "insert into post (userId, postDate, postText) values (?, ?, ?)"
        -- Inserts a row into the post table with the above variables specified as input to the method
    In getPostsFromFollowed:
        "select * from post, follow where follow.followerUserId = ? and post.userId = follow.followeeUserId ORDER BY post.postDate DESC"
        -- Returns all the posts in which the active user is following the maker of the post (ordered by tiem posted)
    In getAllPosts:
        "select * from post"
        -- Pretty self-explanatory, returns every row in the post table
    In getHeartsCount:
        "select count(*) from heart where postId = ?"
        -- Returns how many hearts have been logged in the heart table for the specififed postId
    In getCommentsCount:
        "select count(*) from comment where postId = ?"
        -- Returns how many comments have been logged in the comment table for the specified postId
    In getIsHearted:
        "select * from heart where postId = ? and userId = ?"
        -- Returns the rows where the current user has hearted the current post (if 0, implies post has not been hearted by current user)
    In getIsBookmarked:
        "select * from bookmark where postId = ? and userId = ?"
        -- Returns the rows where the current user has bookmarked the current post (if 0, implies post has not been bookmarked by current user)
    In addLike:
        "insert into heart values (?,?)"
        -- Inserts a row into the heart table with specified userId and postId
    In removeLike:
        "delete from heart where postId = ? and userId = ?"
        -- Removes the row from the heart table with specified userId and postId
    In addBookmark:
        "insert into bookmark values (?,?)"
        -- Inserts a row into the bookmark table with specfifed userId and postId
    In removeBookmark:
        "delete from bookmark where postId = ? and userId = ?"
        -- Removes the row from the bookmark table with specified userId and postId
    In makeComment:
        "insert into comment (postId, userId, commentDate, commentText) values (?, ?, ?, ?)"
        -- Inserts a comment row with the specifed postId, userId, commentText (as inputs to the method) and current time
    In getCommentsForPost:
        "select * from comment where postId = ?"
        -- Returns every comment for a specified post
    In getExpandedPost:
        "select * from post where postId = ?"
        -- Returns every post with a specified postId
    In getBookmarkedPosts:
        "SELECT * FROM post INNER JOIN bookmark ON post.postId = bookmark.postId WHERE bookmark.userId = ?"
        -- Returns all posts that have been bookmarked by the current user

In ProfileService:
    In postList:
        "select * from post where userId = ? order by postDate desc"
        -- Returns all posts from the specified user from most recent to oldest
    In isHearted:
        "select count(*) from heart where postId = ? and userId = ?"
        -- Returns the rows where the current user has hearted the current post (if 0, implies post has not been hearted by current user)
    In isBookmarked:
        "select count(*) from bookmark where postId = ? and userId = ?"
        -- Returns the rows where the current user has bookmarked the current post (if 0, implies post has not been bookmarked by current user)
    In currentHeartCount:
        "select count(userId) from heart where postId = ?"
        -- Returns how many hearts have been logged in the heart table for the specififed postId
    In currentCommentCount:
        "select count(userId) from comment where postId = ?"
        -- Returns how many comments have been logged in the comment table for the specified postId
    In returnUserInfo:
        "select firstname, lastname from user where userId = ?"
        -- Returns the firt and last name of the specified user
    
In UserService:
    In authenticate:
        "select * from user where username = ?"
        -- Returns all user rows with the specified username
    In registerUser:
        "insert into user (username, password, firstName, lastName) values (?, ?, ?, ?)"
        -- Adds a user row into the user table with specified values as inputs to the method
    In getUserById:
        "select * from user where userId = ?"
        -- returns all user rows with the specified userId



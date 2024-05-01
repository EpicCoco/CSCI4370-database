# Demo video link: https://youtu.be/dW6_U32ifDU

#Group 10

# Project contributors/contributions:
* Codey Borrelli - Project organization, bug fixes, hashtag search/tagging, date formatting,
add comments

* Ryan Albright - Bookmark page, home page, add comments, database design, comments,
hashtag search

* Jude Mullins - Write dml.sql, populate database in database_setup.sql, people page,
database design, ProfileService

* Zach Bloodworth - Features of a post (like, bookmark, comment ordering), add comments,
making posts, comments 

# Instructions to run the project:
1. Run docker container and start mysql server.
2. Run database_setup.sql
3.. Navigate to the directory with the pom.xml using the terminal and run the following
command:
4. mvn spring-boot:run -D"spring-boot.run.jvmArguments='-Dserver.port=8081'"
5. Open the browser and navigate to the following URL: http://localhost:8081/
6. Create an account and login.
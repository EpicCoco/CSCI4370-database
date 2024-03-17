/**
Copyright (c) 2024 Sami Menik, PhD. All rights reserved.

This is a project developed by Dr. Menik to give the students an opportunity to apply database concepts learned in the class in a real world project. Permission is granted to host a running version of this software and to use images or videos of this work solely for the purpose of demonstrating the work to potential employers. Any form of reproduction, distribution, or transmission of the software's source code, in part or whole, without the prior written consent of the copyright owner, is strictly prohibited.
*/
package uga.menik.cs4370.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import uga.menik.cs4370.models.Post;
import uga.menik.cs4370.utility.Utility;

/**
 * Handles /hashtagsearch URL and possibly others.
 * At this point no other URLs.
 */
@Controller
public class HashtagSearchController {

    @GetMapping("/hashtagsearch")
    public ModelAndView searchPostsByHashtags(@RequestParam(name = "hashtags") String hashtags) {
        System.out.println("User is searching hashtags: " + hashtags);

        // Split the input hashtags by space to get individual hashtags
        String[] hashtagArray = hashtags.split(" ");
        List<String> hashtagsList = new ArrayList<>();
        for (String hashtag : hashtagArray) {
            // Remove '#' symbol if present and add to the list
            hashtagsList.add(hashtag.replace("#", ""));
        }

        // Retrieve posts from the database (replace this with your actual data retrieval logic)
        List<Post> allPosts = Utility.createSamplePostsListWithoutComments();

        // Filter posts based on hashtags
        List<Post> filteredPosts = allPosts.stream()
                .filter(post -> postContainsAllHashtags(post, hashtagsList))
                .collect(Collectors.toList());

        ModelAndView mv = new ModelAndView("posts_page");
        mv.addObject("posts", filteredPosts);

        return mv;
    }

    private boolean postContainsAllHashtags(Post post, List<String> hashtagsList) {
        for (String hashtag : hashtagsList) {
            // Check if the post content contains all hashtags in the list
            if (!post.getContent().toLowerCase().contains("#" + hashtag.toLowerCase())) {
                return false;
            }
        }
        return true;
    }
}

package com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.controllers;

import com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.*;
import com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.ReactionRepository;
import com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.TweetReactionRepository;
import com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.TweetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tweets")
public class TweetController {

    @Autowired
    private TweetRepository tweetRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TweetReactionRepository tweetReactionRepository;
    @Autowired
    private ReactionRepository reactionRepository;

    @GetMapping("/all")
    public ResponseEntity<?> getTweets(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) return ResponseEntity.status(401).body("User not found");

        User currentUser = userOpt.get();
        Page<Tweet> tweets = tweetRepository.findAll(pageable);

        Page<Map<String, Object>> result = tweets.map(tweet -> {
            Optional<TweetReaction> reaction = tweetReactionRepository.findByTweetAndUser(tweet, currentUser);

            Map<String, Long> reactionCounts = new HashMap<>();
            for (EReaction tipo : EReaction.values()) {
                Long count = tweetReactionRepository.countByTweetAndReaction_Description(tweet, tipo);
                reactionCounts.put(tipo.name(), count);
            }

            Map<String, Object> map = new HashMap<>();
            map.put("id", tweet.getId());
            map.put("tweet", tweet.getTweet());
            map.put("postedBy", tweet.getPostedBy().getUsername());
            map.put("reactionSelected", reaction.map(r -> r.getReaction().getDescription().name()).orElse(null));
            map.put("reactionCounts", reactionCounts);
            return map;
        });

        return ResponseEntity.ok(result);
    }


    @PostMapping("/create")
    public Tweet createTweet(@Valid @RequestBody Tweet tweet) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        System.out.println("userid : " + userId  );


        User user = getValidUser(userId);
        System.out.println("user");

        System.out.println(user);
        Tweet myTweet = new Tweet(tweet.getTweet());
        myTweet.setPostedBy(user);
        tweetRepository.save(myTweet);

        return myTweet;
    }

    private User getValidUser(String userId) {
        Optional<User> userOpt = userRepository.findByUsername(userId);
        if (!userOpt.isPresent()) {
            throw new RuntimeException("User not found");
        }
        return userOpt.get();
    }


}


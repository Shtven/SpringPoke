package com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.controllers;

import com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Tweet;
import com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.TweetComment;
import com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.TweetReaction;
import com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.User;
import com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.request.TweetCommentRequest;
import com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.payload.response.TweetCommentResponse;
import com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.TweetCommentRepository;
import com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.TweetRepository;
import com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/comments")
public class TweetCommentController {

    @Autowired
    private TweetCommentRepository tweetCommentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TweetRepository tweetRepository;

    @GetMapping("/commentsByTweet/{tweetId}")
    public List<TweetCommentResponse> comments(@PathVariable("tweetId") Long tweetId){
        return tweetCommentRepository.findAllByTweetId(tweetId).stream().map(
                c -> new TweetCommentResponse(c.getId(), c.getContent(), c.getAuthor().getUsername())).toList();
    }

    @PostMapping("/create")
    public TweetComment createComment(@Valid @RequestBody TweetCommentRequest tweetCommentRequest){
        System.out.println("tweetid : " + tweetCommentRequest.getTweetId()  );
        System.out.println("comment : " + tweetCommentRequest.getComment()  );

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        System.out.println("userid : " + userId  );

        User user = getValidUser(userId);
        System.out.println("user");

        System.out.println(user);

        TweetComment tweetComment = new TweetComment();
        Tweet myTweet = getValidTweet(tweetCommentRequest.getTweetId());
        System.out.println("object tweet : " );
        System.out.println(myTweet );

        tweetComment.setAuthor(user);
        tweetComment.setTweet(myTweet);
        tweetComment.setContent(tweetCommentRequest.getComment());

        tweetCommentRepository.save(tweetComment);

        return tweetComment;
    }

    private User getValidUser(String userId) {
        Optional<User> userOpt = userRepository.findByUsername(userId);
        if (!userOpt.isPresent()) {
            throw new RuntimeException("User not found");
        }
        return userOpt.get();
    }

    private Tweet getValidTweet(Long tweetId) {
        Optional<Tweet> tweetOpt = tweetRepository.findById(tweetId);
        if (!tweetOpt.isPresent()) {
            throw new RuntimeException("Tweet not found");
        }
        return tweetOpt.get();
    }

}

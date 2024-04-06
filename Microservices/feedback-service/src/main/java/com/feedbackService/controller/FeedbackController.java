package com.feedbackService.controller;

import com.UserService.dto.UserResponse;
import com.UserService.service.UserService;
import com.feedbackService.dto.FeedbackRequest;
import com.feedbackService.model.Feedback;
import com.feedbackService.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/addFeedback")
    @ResponseStatus(HttpStatus.CREATED)
    public void addFeedback(FeedbackRequest feedbackRequest){

        // Retrieve user details from User module
        UserResponse user = userService.getUserDetailsByID(feedbackRequest.getUserId());

        UserResponse userResponse = restTemplate.getForObject("http://user-service/api/user/getUserDetailsByID/{userId}", UserResponse.class, feedbackRequest.getUserId());

        // Create Feedback object and associate it with the user
        Feedback feedback = new Feedback();
        feedback.setMessage(feedbackRequest.getMessage());
        feedback.setCreationDate(new Date());
        feedback.setUserId(user.getId());

        // Save
        feedbackService.addFeedback(feedbackRequest);

    }
    @GetMapping("/getFeedbackById/{id}")
    public Feedback getFeedbackById(@PathVariable int id) {
        return feedbackService.getFeedbackById(id);
    }

    @PutMapping("/updateFeedback/{id}")
    public Feedback updateFeedback(@PathVariable int id, @RequestBody FeedbackRequest feedbackRequest) {
        return feedbackService.updateReview(id, feedbackRequest);
    }

    @DeleteMapping("/deleteFeedback/{id}")
    public void deleteFeedback(@PathVariable int id) {feedbackService.deleteFeedback(id);}


}

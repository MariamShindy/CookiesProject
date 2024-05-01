package com.feedbackService.controller;

import com.feedbackService.dto.FeedbackRequest;
import com.feedbackService.model.Feedback;
import com.feedbackService.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("")
    public String contact() {
        return "contact"; // Assuming contact.html is located in the appropriate directory
    }

    @PostMapping("/addFeedback")
    public void addFeedback(FeedbackRequest feedbackRequest){

        feedbackService.addFeedback(feedbackRequest);
    }

    //admin will use this method
    @GetMapping("/getFeedbackById/{id}")
    public Feedback getFeedbackById(@PathVariable int id) {

        return feedbackService.getFeedbackById(id);
    }

    //admin will use this method
    @PutMapping("/updateFeedback/{id}")
    public Feedback updateFeedback(@PathVariable int id, @RequestBody FeedbackRequest feedbackRequest) {
        return feedbackService.updateFeedback(id, feedbackRequest);
    }
    //admin will use this method
    @DeleteMapping("/deleteFeedback/{id}")
    public void deleteFeedback(@PathVariable int id) {
        feedbackService.deleteFeedback(id);
    }
}

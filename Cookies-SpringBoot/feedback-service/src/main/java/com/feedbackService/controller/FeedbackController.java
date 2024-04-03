package com.feedbackService.controller;

import com.feedbackService.dto.FeedbackRequest;
import com.feedbackService.model.Feedback;
import com.feedbackService.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @PostMapping("/addFeedback")
    public void addFeedback(FeedbackRequest feedbackRequest){
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
    public void deleteFeedback(@PathVariable int id) {
        feedbackService.deleteFeedback(id);
    }
}

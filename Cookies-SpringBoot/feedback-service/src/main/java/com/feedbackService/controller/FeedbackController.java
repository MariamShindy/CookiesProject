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
    @PostMapping
    public void addFeedback(FeedbackRequest feedbackRequest){
        feedbackService.addFeedback(feedbackRequest);
    }
    @GetMapping("/{id}")
    public Feedback getReviewById(@PathVariable int feedbackId) {
        return feedbackService.getFeedbackById(feedbackId);
    }

    @PutMapping("/{id}")
    public Feedback updateReview(@PathVariable int feedbackId, @RequestBody FeedbackRequest feedbackRequest) {
        return feedbackService.updateReview(feedbackId, feedbackRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable int feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
    }
}

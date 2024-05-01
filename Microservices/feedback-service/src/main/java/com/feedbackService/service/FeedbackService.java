package com.feedbackService.service;

import com.feedbackService.dto.FeedbackRequest;
import com.feedbackService.model.Feedback;
import com.feedbackService.repository.FeedbackReposiotry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.logging.Logger;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackReposiotry feedbackReposiotry;
    private static final Logger logger = Logger.getLogger(FeedbackService.class.getName());

    public void addFeedback(FeedbackRequest feedbackRequest){
      Feedback feedback = mapFeedbackRequestToFeedback(feedbackRequest);
      feedbackReposiotry.save(feedback);
        logger.info("Feedback created successfully");
    }

    public Feedback getFeedbackById(int feedbackId) {
        return feedbackReposiotry.findById(feedbackId)
                .orElseThrow(() -> new RuntimeException("feedback not found"));
    }

    //Admin use this method
    public Feedback updateFeedback(int feedbackId, FeedbackRequest feedbackRequest) {
        Feedback existingFeedback = getFeedbackById(feedbackId);
        existingFeedback.setMessage(feedbackRequest.getMessage());
        existingFeedback.setCreationDate(feedbackRequest.getCreationDate());
        return feedbackReposiotry.save(existingFeedback);
    }

    //Admin use this method
    public void deleteFeedback(int feedbackId) {
        Feedback existingReview = getFeedbackById(feedbackId);
        feedbackReposiotry.delete(existingReview);
        logger.info("Feedback deleted successfully");
    }

    //FeedbackRequest ==> Feedback
    private Feedback mapFeedbackRequestToFeedback(FeedbackRequest feedbackRequest){
        Feedback feedback = new Feedback();
        feedback.setCreationDate(new Date());
        feedback.setMessage(feedbackRequest.getMessage());
        feedback.setUserId(feedbackRequest.getUserId());
        return feedback;
    }

}
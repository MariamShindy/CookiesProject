package com.feedbackService.dto;

import java.util.Date;

public class FeedbackRequest {
    String message ;
    Date creationDate;

    private int userId;

    public FeedbackRequest(){

    }
    public FeedbackRequest(String message, Date creationDate) {
        this.message = message;
        this.creationDate = creationDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}

package com.feedbackService.model;

import com.UserService.model.User;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int  id;
    int userId;
    String message ;
    Date creationDate;

    // Add a reference to the user who submitted the feedback
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Feedback(){

    }

    public Feedback(int id, String message, Date creationDate, User user) {
        this.id = id;
        this.message = message;
        this.creationDate = creationDate;
        this.user = user;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

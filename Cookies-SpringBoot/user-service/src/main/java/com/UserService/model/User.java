package com.UserService.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"ApplicationUsers\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id ;
    String userName ;
    String phone ;
    String email;
    String password ;
    String address ;
    int userType ;
    public User(){

    }
    public User(int id, String userName, String phone, String email, String password, String address, int userType) {
        this.id = id;
        this.userName = userName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.address = address;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }


}

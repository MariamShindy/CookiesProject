package com.UserService.dto;

import lombok.Setter;

// To represent the response data structure returned by the getUserDetailsByID
public class UserResponse {
    @Setter
    int id;
    @Setter
    String userName ;
    @Setter
    String phone ;
    String email;
    @Setter
    String password ;
    @Setter
    String address ;
    @Setter
    int userType ;

    public UserResponse(){
    }

    public UserResponse(int id, String userName, String phone, String email, String password, String address, int userType) {
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

    public String getUserName() {
        return userName;
    }

    public String getPhone() {
        return phone;
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

    public String getAddress() {
        return address;
    }

    public int getUserType() {
        return userType;
    }

}

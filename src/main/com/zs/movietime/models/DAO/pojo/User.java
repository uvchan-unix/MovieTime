package com.zs.movietime.models.DAO.pojo;

public class User {

    private int user_id;
    private String userName;
    private String mailiID;
    private String phoneNumber;
    private int userType;

    public int getUser_id() {
        return user_id;
    }

    public User(int user_id, String userName, String mailiID, String phoneNumber, int userType) {
        this.user_id = user_id;
        this.userName = userName;
        this.mailiID = mailiID;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMailiID() {
        return mailiID;
    }

    public void setMailiID(String mailiID) {
        this.mailiID = mailiID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    

}

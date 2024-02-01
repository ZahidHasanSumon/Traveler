package com.example.traveller.JavaClasses;



public class NewUser {
    private String username,email,imageurl,activationdate;


    public NewUser(String username, String email, String imageurl,String activationdate) {
        this.username = username;
        this.email = email;
        this.imageurl = imageurl;
        this.activationdate = activationdate;
    }

    public NewUser() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getActivationdate() {
        return activationdate;
    }

    public void setActivationdate(String activationdate) {
        this.activationdate = activationdate;
    }
}

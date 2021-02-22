package com.example.GOLAUNDRY;

public class UserProfile {
    public String username;
    public String useremail;

    public UserProfile() {
    }

    public UserProfile(String username, String useremail) {
        this.username = username;
        this.useremail = useremail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }
}

package com.example.GOLAUNDRY;

import android.widget.EditText;

public class UserProfile {
    public String username;
    public String useremail;

    public UserProfile(EditText username, EditText userpass, EditText useremail){
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

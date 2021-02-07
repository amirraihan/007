package com.example.GOLAUNDRY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class homepage extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        firebaseAuth = FirebaseAuth.getInstance();

    }

    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(homepage.this, Login.class));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.logoutMenu:{
                Logout();
            }
            case R.id.ProfileMenu:
                startActivity(new Intent(homepage.this, profilePage.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
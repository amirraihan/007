package com.example.GOLAUNDRY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth firebaseAuth;
    //Variable
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    CardView dobiBoy;
    CardView dobiQueen;
    CardView laundryBar;
    CardView laundryDoc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        // card view function
        dobiBoy = findViewById(R.id.card_boy);
        dobiQueen = findViewById(R.id.card_queen);
        laundryBar = findViewById(R.id.card_bar);
        laundryDoc = findViewById(R.id.card_doc);

        dobiBoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepage.this , BookingFormDobiBoy.class));
            }
        });

        dobiQueen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepage.this , BookingFormDobiQueen.class));
            }
        });

        laundryBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepage.this , BookingFormLaundryBar.class));
            }
        });

        laundryDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepage.this , BookingFormLaundryDoc.class));
            }
        });


        firebaseAuth = FirebaseAuth.getInstance();

        /*--Hooks-*/
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        //toolbar
        setSupportActionBar(toolbar);

        /*- Navigation Drawer Menu -*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);

    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home: //home
                startActivity(new Intent(homepage.this, homepage.class));
                break;

            case R.id.ProfileMenu://profile
                startActivity(new Intent(homepage.this, profilePage.class));
                break;

            case R.id.nav_about:
                startActivity(new Intent(homepage.this, AboutUs.class));
                break;

            case R.id.nav_contact:
                startActivity(new Intent(homepage.this, ContactUs.class));
                break;

            case R.id.logoutMenu: //logout punya part
                firebaseAuth.signOut();         //MISS SYIRA TAMBAH
                finish();                       //MISS SYIRA TAMBAH
                startActivity(new Intent(homepage.this, Login.class));
                break;

        }
        return true;
    }
}

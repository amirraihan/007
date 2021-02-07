package com.example.GOLAUNDRY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profilePage extends AppCompatActivity {

    private ImageView profilePic;
    private TextView profileName, profileEmail;
    private Button profileUpdate;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        profilePic = findViewById(R.id.iv_profilePic);
        profileName = findViewById(R.id.tv_Name);
        profileEmail = findViewById(R.id.tv_Email);
        profileUpdate = findViewById(R.id.btn_edit);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                profileName.setText("Name: " + userProfile.getUsername());
                profileEmail.setText("Email: " + userProfile.getUseremail());
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {
                Toast.makeText(profilePage.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
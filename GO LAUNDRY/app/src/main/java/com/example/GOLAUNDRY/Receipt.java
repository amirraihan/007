package com.example.GOLAUNDRY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class Receipt extends AppCompatActivity {

    TextView nameRc, timeRc, dateRc, capacityRc;
    ImageView backReceiptBoy;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        nameRc = findViewById(R.id.tv_nameReceipt);
        timeRc = findViewById(R.id.tv_timeReceipt);
        dateRc = findViewById(R.id.tv_dateReceipt);
        capacityRc = findViewById(R.id.tv_capacityReceipt);
        backReceiptBoy = findViewById(R.id.iv_backReceiptBoy);

        backReceiptBoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Receipt.this, BookingFormDobiBoy.class );
                startActivity(intent);
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference("Dobi Boy").child(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                BookingDetail bookingDetail = dataSnapshot.getValue(BookingDetail.class);
                nameRc.setText(bookingDetail.getName());
                timeRc.setText(bookingDetail.getTime());
                dateRc.setText(bookingDetail.getDate());
                capacityRc.setText(bookingDetail.getCapacity());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Receipt.this, error.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    }

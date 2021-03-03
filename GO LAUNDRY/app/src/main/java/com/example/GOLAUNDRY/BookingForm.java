package com.example.GOLAUNDRY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

public class BookingForm extends AppCompatActivity {

    EditText nameForm, phoneForm, timeForm, capacityForm;
    Button submitForm;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference storageReference;
    String name, phone, time, capacity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_form);

        nameForm = findViewById(R.id.pt_nameform);
        phoneForm = findViewById(R.id.pt_phoneform);
        timeForm = findViewById(R.id.pt_timeform);
        capacityForm = findViewById(R.id.pt_Capacityform);

        submitForm = findViewById(R.id.btn_submitform);

        submitForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name, phone, time, capacity;
                firebaseDatabase = FirebaseDatabase.getInstance();
                name = nameForm.getEditableText().toString().trim();
                storageReference = firebaseDatabase.getReference("Dobi Boy").child(name);
                phone = phoneForm.getEditableText().toString().trim();
                time = timeForm.getEditableText().toString().trim();
                capacity = capacityForm.getEditableText().toString().trim();
                validate();
                BookingDetail bookingDetail = new BookingDetail(name, phone, time, capacity);
                storageReference.setValue(bookingDetail);



            }

        });


    }

    private boolean validate(){
        Boolean result = false;

        name = nameForm.getText().toString();
        phone = phoneForm.getText().toString();
        time = timeForm.getText().toString();
        capacity = capacityForm.getText().toString();

        if(name.isEmpty() || phone.isEmpty() || time.isEmpty() || capacity.isEmpty()){
            Toast.makeText(this, "Please Enter All Detail !", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(BookingForm.this, BookingForm.class));
        }else{
            result = true;
            Toast.makeText(this, "Booking Submitted !", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(BookingForm.this, ConfirmationOrder.class));
        }
        return result;
    }
}
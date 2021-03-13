package com.example.GOLAUNDRY;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class BookingFormLaundryBar extends AppCompatActivity {

    EditText nameForm, phoneForm, timeForm, capacityForm;
    Button submitForm, dateButton;
    DatePickerDialog datePickerDialog;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference storageReference;
    String name, phone, time, capacity, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_form_laundry_bar);

        nameForm = findViewById(R.id.pt_nameform);
        phoneForm = findViewById(R.id.pt_phoneform);
        timeForm = findViewById(R.id.pt_timeform);
        capacityForm = findViewById(R.id.pt_Capacityform);
        dateButton = findViewById(R.id.datelaundrybar);
        initDatePicker();
        dateButton.setText(getTodaysDate());

        submitForm = findViewById(R.id.btn_submitform);

        submitForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name, phone, date, time, capacity;
                firebaseDatabase = FirebaseDatabase.getInstance();
                name = nameForm.getEditableText().toString().trim();
                storageReference = firebaseDatabase.getReference("Laundry Bar").child(name);
                phone = phoneForm.getEditableText().toString().trim();
                date = dateButton.getEditableText().toString().trim();
                time = timeForm.getEditableText().toString().trim();
                capacity = capacityForm.getEditableText().toString().trim();
                validate();
                BookingDetail bookingDetail = new BookingDetail(name, phone, date, time, capacity);
                storageReference.setValue(bookingDetail);



            }

        });


    }

    private String getTodaysDate() {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);

    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);

            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);

    }

    private String makeDateString(int day, int month, int year){
        return getMonthformat(month) + " " + day + " " + year;
    }

    private String getMonthformat(int month) {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";
        // default should never happen
        return "JAN";

    }

    public void openDatePicker(View view){
        datePickerDialog.show();
    }

    private boolean validate(){
        Boolean result = false;

        name = nameForm.getText().toString();
        phone = phoneForm.getText().toString();
        time = timeForm.getText().toString();
        capacity = capacityForm.getText().toString();
        date = dateButton.getText().toString();

        if(name.isEmpty() || phone.isEmpty() || time.isEmpty() || capacity.isEmpty()){
            Toast.makeText(this, "Please Enter All Detail !", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(BookingFormLaundryBar.this, BookingFormDobiBoy.class));
        }else{
            result = true;
            Toast.makeText(this, "Booking Submitted !", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(BookingFormLaundryBar.this, Receipt.class));
        }
        return result;
    }
}
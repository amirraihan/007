package com.example.GOLAUNDRY;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class BookingFormDobiQueen extends AppCompatActivity {

    String[] timepicker;
    Spinner timeForm;
    EditText nameForm, phoneForm, capacityForm;
    Button submitForm, dateButton;
    DatePickerDialog datePickerDialog;
    ImageView backHomeQueen;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference storageReference;
    String name, phone, time, capacity, date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_booking_form);           //CODE NIY SALAH
        setContentView(R.layout.activity_booking_form_dobi_queen);

        nameForm = findViewById(R.id.pt_nameform);
        phoneForm = findViewById(R.id.pt_phoneform);
        capacityForm = findViewById(R.id.pt_Capacityform);
        dateButton = findViewById(R.id.datedobiqueen);
        //timeForm = findViewById(R.id.spboy);              //CODE NIY SALAH
        timeForm = findViewById(R.id.spqueen);
        backHomeQueen = findViewById(R.id.iv_backHomeQueen);
        initDatePicker();
        dateButton.setText(getTodaysDate());

        submitForm = findViewById(R.id.btn_submitform);

        backHomeQueen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingFormDobiQueen.this, homepage.class );
                startActivity(intent);
            }
        });

        timepicker = getResources().getStringArray(R.array.timepicker_array);
        timeForm = (Spinner) findViewById(R.id.spqueen);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, timepicker);                           //tukar jadi simple_list_item_1
        timeForm.setAdapter(adapter);
        timeForm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                firebaseDatabase = firebaseDatabase.getInstance();
                timeForm.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        submitForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name, phone, date, time, capacity ;
                firebaseAuth = FirebaseAuth.getInstance();
                firebaseDatabase = FirebaseDatabase.getInstance();
                name = nameForm.getEditableText().toString().trim();
                storageReference = firebaseDatabase.getReference("Dobi Queen").child(firebaseAuth.getUid());
                firebaseDatabase = FirebaseDatabase.getInstance();
                phone = phoneForm.getEditableText().toString().trim();
                firebaseDatabase = FirebaseDatabase.getInstance();
                date = dateButton.getText().toString().trim();
                firebaseDatabase = FirebaseDatabase.getInstance();
                time = timeForm.getSelectedItem().toString().trim();
                firebaseDatabase = FirebaseDatabase.getInstance();
                capacity = capacityForm.getEditableText().toString().trim();
                firebaseDatabase = FirebaseDatabase.getInstance();
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
        time = timeForm.getSelectedItem().toString();
        capacity = capacityForm.getText().toString();
        date = dateButton.getText().toString();

        if(name.isEmpty() || phone.isEmpty()  || time.isEmpty()  || capacity.isEmpty() || date.isEmpty()){
            Toast.makeText(this, "Please Enter All Detail !", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(BookingFormDobiQueen.this, BookingFormDobiQueen.class));
        }else{
            result = true;
            Toast.makeText(this, "Booking Submitted !", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(BookingFormDobiQueen.this, ReceiptQueen.class));
        }
        return result;
    }
}
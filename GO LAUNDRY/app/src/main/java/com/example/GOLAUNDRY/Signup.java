package com.example.GOLAUNDRY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class Signup extends AppCompatActivity {
    private EditText username, userpass, useremail;
    public Button button;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        username = (EditText) findViewById(R.id.pt_username2);
        userpass = (EditText) findViewById(R.id.tv_pass2);
        useremail = (EditText) findViewById(R.id.pt_email);

        firebaseAuth=FirebaseAuth.getInstance();
        /////////////////////////////button link//////////////////////////////////////////////////
        button = (Button) findViewById(R.id.btn_signup2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate()){
                     String user_pass = userpass.getText().toString().trim();
                     String user_email = useremail.getText().toString().trim();

                     firebaseAuth.createUserWithEmailAndPassword(user_email, user_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                 Toast.makeText(Signup.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                 startActivity(new Intent(Signup.this, Login.class));
                              } else {
                                 Toast.makeText(Signup.this, "SignUp Failed", Toast.LENGTH_SHORT).show();
                             }
                         }
                     });
                 }
            }
        });

    }
    private Boolean validate(){
        Boolean result = false;

        String name = username.getText().toString();
        String password = userpass.getText().toString();
        String email = useremail.getText().toString();

        if(name.isEmpty() || password.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }

        return result;

    }
}



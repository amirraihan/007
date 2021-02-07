package com.example.GOLAUNDRY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private EditText username, userpass, useremail;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    public Button button;
    private TextView ForgotPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userpass = (EditText) findViewById(R.id.tv_pass);
        useremail = (EditText) findViewById(R.id.pt_email1);
        ForgotPassword = (TextView)findViewById(R.id.tv_forgotpass);

        firebaseAuth=FirebaseAuth.getInstance();
        /////////////////////////////button link//////////////////////////////////////////////////
        button = (Button) findViewById(R.id.btn_signup);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Signup.class );
                startActivity(intent);
            }
        });
        button = (Button) findViewById(R.id.btn_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate()){
                    String user_pass = userpass.getText().toString().trim();
                    String user_email = useremail.getText().toString().trim();

                    firebaseAuth.signInWithEmailAndPassword(user_email, user_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                checkEmailVerification();
                            } else {
                                Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, ForgotPass.class));
            }
        });

    }
    private Boolean validate(){
        Boolean result = false;

        String password = userpass.getText().toString();
        String email = useremail.getText().toString();

        if(password.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }

        return result;

    }
    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        startActivity(new Intent(Login.this,homepage.class));

 //       if (emailflag){
 //           finish();
 //           startActivity(new Intent(Login.this,homepage.class));
 //       }else{
//            Toast.makeText(this,"Verify Email",Toast.LENGTH_SHORT).show();
  //         firebaseAuth.signOut();
     //   }
    }
}
package com.ticket.m.signup;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ProviderQueryResult;


public class SignUpActivity extends AppCompatActivity {
//business logic


    EditText email,password,repassword;
    Button signupbtn;
    TextView message;
    private FirebaseAuth mAuth;
    public void loginAccount(View view)
    {
        Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
        startActivity(intent);
    }
    public void insertOperation()
    {
        if(email.getError()!=null || email.getText().length() == 0)
        {
            email.setError("Please check your email");
            return;
        }
        if(password.getError()!=null || password.getText().length() == 0)
        {
            password.setError("Please check your check your password");
            return;
        }
        if(repassword.getError()!=null || repassword.getText().length() == 0)
        {
            repassword.setError("Please check your please check your password");
            return;
        }


        if (password.getText().toString().equals(repassword.getText().toString())) {
            repassword.setBackgroundColor(Color.parseColor("#ace5ae"));
        } else {
            repassword.setBackgroundColor(Color.parseColor("#eaadb2"));
            repassword.setTextColor(Color.BLACK);
            repassword.setError("Password do not match ");
            return;


        }






    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email=findViewById(R.id.email);
        password=findViewById(R.id.ogPassword);
        signupbtn=findViewById(R.id.signUpBtn);
        message=findViewById(R.id.message);
        repassword=findViewById(R.id.rePassword);



        signupbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                insertOperation();

                mAuth=FirebaseAuth.getInstance();
                mAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                   Intent intent =new Intent(SignUpActivity.this,LoginActivity.class);
                                   intent.putExtra("successful","please login with your registered credentials");
                                   startActivity(intent);
                                }
                                else
                                {
                                    message.setText("Authentication failed");
                                }
                            }
                        });


            }
        });





    }

    }


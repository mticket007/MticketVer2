package com.ticket.m.signup;

import android.content.Intent;
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

public class LoginActivity extends AppCompatActivity {

    private Button loginBtn;
    private TextView createAccountId,information;
    private EditText emailId,password;
    private FirebaseAuth auth;
    public void createAccount(View v)
    {
        Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        information=findViewById(R.id.information);
        loginBtn=findViewById(R.id.loginBtn);
        createAccountId=findViewById(R.id.createAccountId);
        emailId=findViewById(R.id.emailId);
        password=findViewById(R.id.password);
        auth=FirebaseAuth.getInstance();
        //checking if user already loggedIn
        if(auth.getCurrentUser()!=null)
        {
            information.setVisibility(View.VISIBLE);
            information.setText("already logged in"+auth.getCurrentUser().getEmail().toString());
        }
       /*Intent intent=getIntent();
       if(intent!=null)
       {
           information.setVisibility(View.VISIBLE);
           information.setText(intent.getStringExtra("successful").toString());
       }*/
       loginBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               auth.signInWithEmailAndPassword(emailId.getText().toString(),password.getText().toString())
                       .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if(task.isSuccessful())
                               {
                                   finish();
                                   Intent intent1=new Intent(LoginActivity.this,Book.class);
                                   startActivity(intent1);
                               }
                           }
                       });

           }
       });
    }
}

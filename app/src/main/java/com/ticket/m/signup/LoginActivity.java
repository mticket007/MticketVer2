package com.ticket.m.signup;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
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

public class LoginActivity extends BasicActivity {

    private Button loginBtn;
    private TextView createAccountId,information,forgetPassword;
    private EditText emailId,password;
    private FirebaseAuth auth;
    //clicking on Create account
    public void createAccount(View v)
    {
        Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
        startActivity(intent);
    }
    //clickign on reset button
    public void forgetPassword(View view)
    {

        Intent intent =new Intent(LoginActivity.this,ForgetPassword.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        forgetPassword=findViewById(R.id.forgetPassword);
        information=findViewById(R.id.information);
        loginBtn=findViewById(R.id.loginBtn);
        createAccountId=findViewById(R.id.createAccountId);
        emailId=findViewById(R.id.emailId);
        password=findViewById(R.id.password);
        auth=FirebaseAuth.getInstance();
        //checking if user already loggedIn
        if(auth!=null)
        {
            /*startActivity(new Intent(this,Book.class));
*/        }
       Intent intent=getIntent();
       if(intent!=null)
       {
           information.setVisibility(View.VISIBLE);
           information.setText(intent.getStringExtra("successful").toString());
       }
       loginBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(emailId.getText().toString()==null)
               {
                   emailId.setError("please provide your email address ");
               }
               if(password.getText().toString()==null)
               {
                   password.setError("please provide your password ");
               }
               if(password.getText().toString().length()<8)
               {
                   password.setError(" Password should be of minimum of size 8");
               }


               //signin
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
                               else
                               {
                                   AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
                                   builder.setTitle("Your username or password is wrong ");
                                   builder.setCancelable(true);
                                   builder.setIcon(R.drawable.ic_error);
                                   builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                                       @Override
                                       public void onClick(DialogInterface dialog, int which) {
                                           dialog.cancel();
                                       }
                                   });
                                   AlertDialog alertDialog=builder.create();
                                   alertDialog.show();
                               }
                           }
                       });

           }
       });
    }

}

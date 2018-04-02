package com.ticket.m.signup;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends BasicActivity {
    private FirebaseAuth  auth;
    private EditText emailId;
    private Button resetbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        emailId=findViewById(R.id.emailId);
        resetbtn=findViewById(R.id.resetBtn);
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth=FirebaseAuth.getInstance();
                auth.sendPasswordResetEmail(emailId.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    AlertDialog.Builder builder=new AlertDialog.Builder(ForgetPassword.this);
                                    builder.setTitle("Please check your email ");
                                    builder.setCancelable(true);
                                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                                    AlertDialog alertDialog=builder.create();
                                    alertDialog.show();
                                }
                                else
                                {
                                    AlertDialog.Builder builder=new AlertDialog.Builder(ForgetPassword.this);
                                    builder.setTitle("Please enter valid email address");
                                    builder.setIcon(R.drawable.ic_error);
                                    builder.setCancelable(true);
                                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });

                                }
                            }
                        });
            }
        });

    }
}

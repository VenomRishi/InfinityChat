package com.example.acer.infinitychat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Toolbar loginToolbar;

    private ProgressDialog mLogDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //toolbar
        loginToolbar= (Toolbar) findViewById(R.id.loginToolbar);
        setSupportActionBar(loginToolbar);
        getSupportActionBar().setTitle("Login Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();

        mLogDialog=new ProgressDialog(this);

        emailEditText=(EditText) findViewById(R.id.emailEditText);
        passwordEditText=(EditText) findViewById(R.id.passwordEditText);
        loginButton=(Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=emailEditText.getText().toString();
                String password=passwordEditText.getText().toString();
                if(!TextUtils.isEmpty(email)||!TextUtils.isEmpty(password)){

                    mLogDialog.setTitle("Logging in");
                    mLogDialog.setMessage("Please wait while we check your credentials");
                    mLogDialog.setCanceledOnTouchOutside(false);
                    mLogDialog.show();
                    loginUser(email, password);
                }

            }
        });

    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                  if (task.isSuccessful()){
                      mLogDialog.dismiss();
                      Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                      startActivity(intent);
                      finish();
                  }
                  else {
                      mLogDialog.hide();
                      Toast.makeText(LoginActivity.this, "Cannot Sign in. Please check the form and try again", Toast.LENGTH_SHORT).show();
                  }
            }
        });
    }
}

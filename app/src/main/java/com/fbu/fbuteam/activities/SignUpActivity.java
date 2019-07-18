package com.fbu.fbuteam.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fbu.fbuteam.R;
import com.google.android.material.textfield.TextInputEditText;
import com.parse.ParseUser;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText emailSignup;
    private TextInputEditText usernameSignup;
    private TextInputEditText passwordSignup;
    private Button signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setupComponents();
        setSignupBtnClickListener();
    }

    private void setupComponents() {
        emailSignup = findViewById(R.id.email_et);
        usernameSignup = findViewById(R.id.username_et);
        passwordSignup = findViewById(R.id.password_et);
        signupBtn = findViewById(R.id.create_new_signup);
    }

    private void setSignupBtnClickListener() {
        signupBtn.setOnClickListener((view) -> {
            signUp();
        });
    }

    private void signUp() {
        ParseUser user = createParseUser();

        if (anyFieldIsEmpty()) {
            Toast.makeText(SignUpActivity.this, "Please fill out all fields.", Toast.LENGTH_LONG).show();
        } else {
            signUpInBackground(user);
        }
    }

    private void signUpInBackground(ParseUser user) {
        user.signUpInBackground((error) -> {
            if (error == null) {
                Toast.makeText(SignUpActivity.this, "Successfully signed up", Toast.LENGTH_SHORT).show();
                goToTags();
            } else {
                Log.e("SignUpActivity.", "Failed to sign up", error);
            }
        });
    }

    private boolean anyFieldIsEmpty() {
        return usernameSignup.equals("") || passwordSignup.equals("") || emailSignup.equals("");
    }

    private ParseUser createParseUser() {
        ParseUser user = new ParseUser();
        user.setUsername(getText(usernameSignup));
        user.setPassword(getText(passwordSignup));
        user.setEmail(getText(emailSignup));

        return user;
    }

    private void goToTags() {
        Intent intent = new Intent(this, TagActivity.class);
        startActivity(intent);
        finish();
    }

    private String getText(EditText et) {
        return et.getText().toString();
    }
}




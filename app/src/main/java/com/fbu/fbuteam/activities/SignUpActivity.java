package com.fbu.fbuteam.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fbu.fbuteam.R;
import com.fbu.fbuteam.models.User;
import com.google.android.material.textfield.TextInputEditText;
import com.parse.ParseException;
import com.parse.ParseUser;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText emailSignup;
    private TextInputEditText nameSignup;
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
        nameSignup = findViewById(R.id.name_et);
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
                handleSignUpError(error);
            }
        });
    }

    private void handleSignUpError(ParseException error) {
        String message = error.getMessage();
        Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_LONG).show();
    }

    private boolean anyFieldIsEmpty() {
        boolean isUsernameEmpty = getText(usernameSignup).isEmpty();
        boolean isNameEmpty = getText(nameSignup).isEmpty();
        boolean isEmailEmpty = getText(emailSignup).isEmpty();
        boolean isPasswordEmpty = getText(passwordSignup).isEmpty();

        return isUsernameEmpty || isNameEmpty || isEmailEmpty || isPasswordEmpty;
    }

    private ParseUser createParseUser() {
        User user = new User();
        user.setUsername(getText(usernameSignup));
        user.setName(getText(nameSignup));
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




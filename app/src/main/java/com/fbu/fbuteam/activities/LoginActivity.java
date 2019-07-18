package com.fbu.fbuteam.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.fbu.fbuteam.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;
    private Button loginButton;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        persistUser();
        setupComponents();

        setupLoginClickListener();
        setupSignupClickListener();
    }

    private void persistUser() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        checkSession(currentUser);
    }

    private void setupComponents() {
        usernameInput = findViewById(R.id.usernameText);
        passwordInput = findViewById(R.id.passwordText);
        loginButton = findViewById(R.id.loginButton);
        signupButton = findViewById(R.id.signupButton);
    }

    private void setupLoginClickListener() {
        loginButton.setOnClickListener((view) -> {
            final String username = usernameInput.getText().toString();
            final String password = passwordInput.getText().toString();

            login(username, password);
        });
    }

    private void setupSignupClickListener() {
        signupButton.setOnClickListener((view) -> {
            goToSignUp();
        });
    }

    public void checkSession(ParseUser currentUser) {
        if (userIsLogged(currentUser)) {
            goToHome();
        }
    }

    private boolean userIsLogged(ParseUser currentUser) {
        return (currentUser != null);
    }

    private void login(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException error) {
                if (error == null) {
                    Log.d("LoginActivity", "Login successful!");
                    Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_LONG).show();
                    goToHome();
                } else {
                    Log.e("LoginActivity", "Login failure.");
                    Toast.makeText(LoginActivity.this, "Login failure", Toast.LENGTH_LONG).show();
                    error.printStackTrace();
                }
            }
        });
    }

    private void goToSignUp() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    private void goToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}

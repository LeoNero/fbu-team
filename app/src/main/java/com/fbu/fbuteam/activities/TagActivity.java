package com.fbu.fbuteam.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.fbu.fbuteam.R;

public class TagActivity extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;
    private Button sportsTag;
    private Button entTag;
    private Button moneyTag;
    private Button techTag;
    private Button envTag;
    private Button govTag;
    private Button socialTag;
    private Button militaryTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);

        initializeObjects();

    }

    private void initializeObjects() {
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        sportsTag = findViewById(R.id.sportsTag);
        entTag = findViewById(R.id.entTag);
        moneyTag = findViewById(R.id.moneyTag);
        techTag = findViewById(R.id.techTag);
        envTag = findViewById(R.id.envTag);
        govTag = findViewById(R.id.govTag);
        socialTag = findViewById(R.id.socialTag);
        militaryTag = findViewById(R.id.militaryTag);
    }
}

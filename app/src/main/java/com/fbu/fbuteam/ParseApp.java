package com.fbu.fbuteam;

import android.app.Application;

import com.facebook.soloader.SoLoader;
import com.fbu.fbuteam.models.User;
import com.parse.Parse;
import com.parse.ParseObject;

import com.fbu.fbuteam.models.Node;

public class ParseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        registerParseSubclasses();
        setUpParse();
        setupSoLoader();
    }

    private void registerParseSubclasses() {
        ParseObject.registerSubclass(User.class);
        ParseObject.registerSubclass(Node.class);
    }

    private void setUpParse() {
        final Parse.Configuration configuration = new Parse.Configuration.Builder(this)
                .applicationId("LeoHaleyPlaxides")
                .clientKey("LeoHaleyPlaxides")
                .server("https://fbu-team-app.herokuapp.com/parse")
                .build();

        Parse.initialize(configuration);
    }

    private void setupSoLoader() {
        SoLoader.init(this, false);
    }
}

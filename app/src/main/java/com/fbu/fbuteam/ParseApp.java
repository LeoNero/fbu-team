package com.fbu.fbuteam;

import android.app.Application;

import com.facebook.soloader.SoLoader;
import android.content.Context;
import com.fbu.fbuteam.models.NewsArticle;
import com.facebook.soloader.SoLoader;
import com.fbu.fbuteam.models.Node;
import com.fbu.fbuteam.models.User;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        ParseApp.context = getApplicationContext();

        registerParseSubclasses();
        setUpParse();
        setupSoLoader();
    }

    private void registerParseSubclasses() {
        ParseObject.registerSubclass(User.class);
        ParseObject.registerSubclass(Node.class);
    }

    private void setUpParse() {
        ParseObject.registerSubclass(NewsArticle.class);
        final Parse.Configuration configuration = new Parse.Configuration.Builder(this)
                .applicationId("LeoHaleyPlaxides")
                .clientKey("LeoHaleyPlaxides")
                .server("https://fbu-team-app.herokuapp.com/parse")
                .build();
        Parse.initialize(configuration);
    }

    public static Context getAppContext() {
        return ParseApp.context;
    }

    private void setupSoLoader() {
        SoLoader.init(this, false);
    }
}

package com.fbu.fbuteam;

import android.app.Application;
import android.content.Context;

import com.fbu.fbuteam.Models.NewsArticle;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        ParseApp.context = getApplicationContext();
        setUpParse();
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
}
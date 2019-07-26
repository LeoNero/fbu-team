package com.fbu.fbuteam;

import android.app.Application;

import com.fbu.fbuteam.Models.NewsArticle;
import com.parse.Parse;
import com.parse.ParseObject;

import com.fbu.fbuteam.models.Node;

public class ParseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Node.class);
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
}

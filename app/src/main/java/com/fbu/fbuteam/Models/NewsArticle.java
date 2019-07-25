package com.fbu.fbuteam.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.w3c.dom.Node;

@ParseClassName("NewsArticle")
public class NewsArticle extends ParseObject {

    private static final String KEY_NAME = "Name";

    public String getName() {
        return getString(KEY_NAME);
    }

    public void setName(String name) {
        put(KEY_NAME, name);
    }

}


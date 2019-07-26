package com.fbu.fbuteam.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.sql.Date;


@ParseClassName("NewsArticle")
public class NewsArticle extends ParseObject {

    public static final String KEY_NAME = "Name";
    public static final String KEY_CREATED_AT = "createdAt";
    public static final String KEY_BODY_SNIPPET = "BodySnippet";


    public String getName() {
        return getString(KEY_NAME);
    }

    public void setName(String name) {
        put(KEY_NAME, name);
    }

    public java.util.Date getCreatedAt() {
        return getDate(KEY_CREATED_AT);
    }

    public void setCreatedAt(Date createdAt) {
        put(KEY_CREATED_AT, createdAt);
    }

    public String getBodySnippet() {
        return getString(KEY_BODY_SNIPPET);
    }

    public void setKeyBodySnippet(String bodySnippet) {
        put(KEY_BODY_SNIPPET, bodySnippet);
    }
}


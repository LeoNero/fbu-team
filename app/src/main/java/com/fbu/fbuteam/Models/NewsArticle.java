package com.fbu.fbuteam.models;

import android.nfc.Tag;

import com.fbu.fbuteam.models.Node;
import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


@ParseClassName("NewsArticle")
public class NewsArticle extends ParseObject {

    public static final String KEY_NAME = "Name";
    public static final String KEY_CREATED_AT = "createdAt";
    public static final String KEY_BODY_SNIPPET = "BodySnippet";
    public static final String KEY_AUTHOR = "Author";
    public static final String KEY_BODY = "Body";
    public static final String KEY_SOURCE = "Source";
    public static final String KEY_IMAGE = "Image";
    public static final String KEY_TAGS = "Tags";

    public String getName() {
        return getString(KEY_NAME);
    }

    public void setName(String name) {
        put(KEY_NAME, name);
    }

    public String getBodySnippet() {
        return getString(KEY_BODY_SNIPPET);
    }

    public void setKeyBodySnippet(String bodySnippet) {
        put(KEY_BODY_SNIPPET, bodySnippet);
    }

    public String getAuthor() { return getString(KEY_AUTHOR); }

    public void setAuthor(String author) { put(KEY_AUTHOR, author); }

    public String getBody() { return getString(KEY_BODY); }

    public void setBody(String body) {put(KEY_BODY, body); }

    public String getSource() { return getString(KEY_SOURCE); }

    public void setSource(String source) {put(KEY_SOURCE, source); }

    public String getImage() {
        return getString(KEY_IMAGE);
    }

    public void setImage(String image) {
        put(KEY_IMAGE, image);
    }

    public List<ParseObject> getTags() {
        Object tags = get(KEY_TAGS);

        if (tags != null) {
            return (List<ParseObject>) tags;
        }

        return new ArrayList<>();
    }

    public static class Query extends ParseQuery<NewsArticle> {
        public Query() {
            super(NewsArticle.class);
        }

        public Query withRelations() {
            include(KEY_TAGS);
            return this;
        }
    }
}


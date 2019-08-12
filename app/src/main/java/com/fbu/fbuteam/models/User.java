package com.fbu.fbuteam.models;

import com.parse.ParseClassName;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

@ParseClassName("_User")
public class User extends ParseUser {
    public static final String KEY_BIG_IDEA_TAGS = "bigIdeaTags";
    public static final String KEY_DETAIL_TAGS = "detailTags";

    public static User getCurrentUser() {
        return (User) ParseUser.getCurrentUser();
    }

    public List<Node> getBigIdeaTags() {
        Object bigIdeaTags = get(KEY_BIG_IDEA_TAGS);

        if (bigIdeaTags != null) {
            return (List<Node>) bigIdeaTags;
        }

        return new ArrayList<>();
    }

    public List<Node> getDetailTags() {
        Object detailTags = get(KEY_DETAIL_TAGS);

        if (detailTags != null) {
            return (List<Node>) detailTags;
        }

        return new ArrayList<>();
    }
}
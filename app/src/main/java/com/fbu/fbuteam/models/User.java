package com.fbu.fbuteam.models;

import com.parse.ParseClassName;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

@ParseClassName("_User")
public class User extends ParseUser {
    public static final String KEY_NAME = "name";
    public static final String KEY_FOLLOWERS = "followers";
    public static final String KEY_FOLLOWING = "following";
    public static final String KEY_BIG_IDEA_TAGS = "bigIdeaTags";
    public static final String KEY_DETAIL_TAGS = "detailTags";

    public void setName(String name) {
        put(KEY_NAME, name);
    }

    public String getName() {
        return getString(KEY_NAME);
    }

    public int getFollowers() {
        return getInt(KEY_FOLLOWERS);
    }

    public int getFollowing() {
        return getInt(KEY_FOLLOWING);
    }

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

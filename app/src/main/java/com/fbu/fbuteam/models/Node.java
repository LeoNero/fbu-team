package com.fbu.fbuteam.models;

import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

@ParseClassName("Node")
public class Node extends ParseObject implements Parcelable {
    private static final String KEY_NAME = "name";
    private static final String KEY_TYPE = "type";
    private static final String KEY_LEVEL = "level";
    private static final String KEY_PARENT = "parent";
    private static final String KEY_CHILDREN = "children";

    public int getLevel(){
        return getInt(KEY_LEVEL);
    }

    public void setLevel(int level) {
        put(KEY_LEVEL, level);
    }

    public String getName() {
        return getString(KEY_NAME);
    }

    public void setName(String name) {
        put(KEY_NAME, name);
    }

    public String getType() {
        return getString(KEY_TYPE);
    }

    public void setType(String type) {
        put(KEY_TYPE, type);
    }

    public Node getParent() {
        ParseObject parent = getParseObject(KEY_PARENT);

        if (parent != null) {
            return (Node) parent;
        }

        return null;
    }

    public void setParent(Node parent) {
        if (getLevel() > parent.getLevel()) {
            add(KEY_PARENT, parent);
        }
    }

    public List<Node> getChildren() {
        Object children = get("children");

        if (children != null) {
            return (List<Node>) children;
        }

        return new ArrayList<>();
    }

    public void setChildren(List<Node> children) {
        for (Node child : children) {
            addChild(child);
        }
    }

    public void addChild(Node child) {
        if (getLevel() < child.getLevel()) {
            addUnique(KEY_CHILDREN, child);
        }
    }

    public void removeChildren(List<Node> children) {
        removeAll(KEY_CHILDREN, children);
    }

    public static Node createNode(String name, int level, String type, Node parent, List<Node> children){
        Node node = createDefaultNode(name, level, type);

        if (children != null && children.size() != 0) {
            node.setChildren(children);
        }

        if (parent != null) {
            node.setParent(parent);
        }

        return node;
    }

    private static Node createDefaultNode(String name, int level, String type) {
        Node node = new Node();
        node.setName(name);
        node.setLevel(level);
        node.setType(type);

        return node;
    }

    public static class Query extends ParseQuery<Node> {
        public Query() {
            super(Node.class);
        }

        public Query withRelations() {
            include(KEY_PARENT);
            include(KEY_CHILDREN);
            return this;
        }

        public Query fromType(String type) {
            whereEqualTo("type", type);
            return this;
        }

        public Query fromTagsId(List<String> tagsId) {
            whereContainedIn("Tags", tagsId);
            return this;
        }
    }
}

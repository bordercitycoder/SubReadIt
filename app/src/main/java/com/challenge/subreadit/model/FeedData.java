package com.challenge.subreadit.model;

import com.challenge.subreadit.model.children.PostList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class FeedData {

    @SerializedName("children")
    @Expose
    private ArrayList<PostList> children;

    public ArrayList<PostList> getListOfPosts() {
        return children;
    }

}

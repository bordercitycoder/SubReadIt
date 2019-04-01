package com.challenge.subreadit.model.children;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostList {
    @SerializedName("data")
    @Expose
    private PostData postData;

    @SerializedName("kind")
    @Expose
    private String kind;

    public PostData getPostData() {
        return postData;
    }

    public String getKind() {
        return kind;
    }




}


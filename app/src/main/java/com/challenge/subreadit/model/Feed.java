package com.challenge.subreadit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Feed {

    @SerializedName("data")
    @Expose
    private FeedData feedData;


    public FeedData getFeedData() {
        return feedData;
    }


}

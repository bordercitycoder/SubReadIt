package com.challenge.subreadit.model.children;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PostData implements Parcelable {

    @SerializedName("subreddit")
    @Expose
    private String subreddit;

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("selftext")
    @Expose
    private String selftext;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    @SerializedName("post_hint")
    @Expose
    private String post_hint;


    public String getTitle() {
        return title;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public String getSelftext() {
        return selftext;
    }

    public String getAuthor() {
        return author;
    }


    protected PostData(Parcel in) {
        subreddit = in.readString();
        author = in.readString();
        title = in.readString();
        selftext = in.readString();
        url = in.readString();
        thumbnail = in.readString();
        post_hint = in.readString();
    }

    public String getThumbnail() {
        return thumbnail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(subreddit);
        dest.writeString(author);
        dest.writeString(title);
        dest.writeString(selftext);
        dest.writeString(url);
        dest.writeString(thumbnail);
        dest.writeString(post_hint);
    }

    public String getPost_hint() {
        return post_hint;
    }


    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PostData> CREATOR = new Parcelable.Creator<PostData>() {
        @Override
        public PostData createFromParcel(Parcel in) {
            return new PostData(in);
        }

        @Override
        public PostData[] newArray(int size) {
            return new PostData[size];
        }
    };
}
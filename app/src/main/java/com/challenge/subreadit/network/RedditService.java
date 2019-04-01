package com.challenge.subreadit.network;

import com.challenge.subreadit.model.Feed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Url;

public interface RedditService {

    @Headers("Content-Type: application/json")
    @GET
    Call<Feed> fetchData(@Url String url);
}

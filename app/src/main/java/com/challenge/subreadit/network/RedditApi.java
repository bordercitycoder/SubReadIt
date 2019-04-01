package com.challenge.subreadit.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RedditApi {

    private static final String BASE_URL = "https://www.reddit.com/";

    private static RedditService redditService;

    private static Retrofit retrofit;

    public static RedditService getInstance() {
        if (redditService != null) {
            return redditService;
        }
        if (retrofit == null) {
            initializeRetrofit();
        }
        redditService = retrofit.create(RedditService.class);
        return redditService;
    }

    private static void initializeRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}

package com.challenge.subreadit.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.challenge.subreadit.model.children.PostList;
import com.challenge.subreadit.network.RedditApi;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private final String TAG = this.getClass().getSimpleName();

    private static Repository repository;
    private ArrayList<PostList> listOfPosts;
    private FeedData feedData;


    public ArrayList<PostList> getListOfPosts() {
        return listOfPosts;
    }

    private Repository() {
    }

    public static Repository Instance() {
        if (repository == null) {
            repository = new Repository();
        }
        return repository;
    }

    public void register(Object object) {
        if (!EventBus.getDefault().isRegistered(object)) {
            EventBus.getDefault().register(object);
        }
    }

    public void unRegister(Object object) {
        if (EventBus.getDefault().isRegistered(object)) {
            EventBus.getDefault().unregister(object);
        }
    }

    public void updateData(String searchString) {

        String search = buildSearchStringForUrl(searchString);
        Call<Feed> serviceCall = RedditApi.getInstance().fetchData(search);

        serviceCall.enqueue(new Callback<Feed>() {

            @Override
            public void onResponse(@NonNull Call<Feed> call, @NonNull Response<Feed> response) {
                if (response.body() == null) {
                    Log.e(TAG, "response.body() == null");
                    EventBus.getDefault().post(new AppErrorEvent("No Data"));
                    return;
                }
                feedData = response.body().getFeedData();
                listOfPosts = feedData.getListOfPosts();
                EventBus.getDefault().post(new ModelUpdateEvent());
            }

            @Override
            public void onFailure(@NonNull Call<Feed> call, @NonNull Throwable throwable) {
                Log.e(TAG, "onFailure: Something went wrong" + throwable.getMessage());
                EventBus.getDefault().post(new AppErrorEvent(throwable.getMessage()));
            }
        });


    }

    private String buildSearchStringForUrl(String searchText) {
        if (searchText == null || searchText.isEmpty()) {
            searchText = "popular";
        }
        return "r/" + searchText + ".json";
    }


}

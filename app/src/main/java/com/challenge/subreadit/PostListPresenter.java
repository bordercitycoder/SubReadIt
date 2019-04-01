package com.challenge.subreadit;

import com.challenge.subreadit.model.AppErrorEvent;
import com.challenge.subreadit.model.Feed;
import com.challenge.subreadit.model.ModelUpdateEvent;
import com.challenge.subreadit.model.Repository;
import com.challenge.subreadit.model.children.PostList;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import retrofit2.Call;

public class PostListPresenter {

    private final String TAG = this.getClass().getSimpleName();
    private PostListPresenter.View view;


    public void onStart() {
        Repository.Instance().register(this);
    }

    public void onStop() {
        view.hideProgressIndicator();
        Repository.Instance().unRegister(this);
    }

    public void setView(PostListPresenter.View view) {
        this.view = view;
        updateData("popular");
    }

    @Subscribe
    public void onModelUpdateEvent(ModelUpdateEvent event) {
        view.hideProgressIndicator();
        view.setupRecyclerView(Repository.Instance().getListOfPosts());
    }

    @Subscribe
    public void onAppErrorEvent(AppErrorEvent event) {
        view.hideProgressIndicator();
        view.displayAlert(event.getErrorMessage());
    }

    public void updateData(String searchString) {
        view.displayProgressIndicator();
        if(searchString != null){
            Repository.Instance().updateData(searchString.trim());
        }
    }


    public interface View {

        void setupRecyclerView(ArrayList<PostList> list);

        void displayAlert(String message);

        void displayProgressIndicator();

        void hideProgressIndicator();
    }


}

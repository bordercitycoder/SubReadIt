package com.challenge.subreadit;

import com.challenge.subreadit.model.children.PostData;

public class PostDetailPresenter {

    private final PostData postData;
    private PostDetailPresenter.View view;


    public PostDetailPresenter(PostData postData) {
        this.postData = postData;
    }

    public void setView(PostDetailPresenter.View view) {
        this.view = view;
        view.displayProgressIndicator();
        refreshView();
    }

    private void refreshView() {

        view.hideDetailsImageView();
        view.hideWebView();

        if (postData != null) {
            view.displayPostDetailsText(postData.getTitle()
                    + "\n"
                    + postData.getSelftext());
            updateMedia();
        } else {
            showErrorMessage();
        }

        view.hideProgressIndicator();
    }

    private void showErrorMessage() {
        view.hideProgressIndicator();
        view.displayAlert("There is a problem with the post's data.");
    }

    private void updateMedia() {
        if (postData != null && postData.getPost_hint() != null) {

            if (postData.getPost_hint().equals("image")) {
                view.displayDetailImage(postData.getUrl());
            }

            if (postData.getPost_hint().equals("link")
                    || postData.getPost_hint().equals("rich:video")) {
                view.displayWebViewUrl(postData.getUrl());
            }
        }
    }

    public interface View {

        void hideWebView();

        void hideDetailsImageView();

        void displayPostDetailsText(String text);

        void displayDetailImage(String url);

        void displayWebViewUrl(String url);

        void displayAlert(String message);

        void displayProgressIndicator();

        void hideProgressIndicator();
    }
}

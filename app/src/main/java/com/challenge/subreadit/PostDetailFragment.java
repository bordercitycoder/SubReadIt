package com.challenge.subreadit;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.challenge.subreadit.model.children.PostData;
import com.squareup.picasso.Picasso;

public class PostDetailFragment extends Fragment implements PostDetailPresenter.View {

    public static final String ARG_ITEM_ID = "title";
    private PostData postData;
    private PostDetailPresenter presenter;
    private TextView postDetailTextView;
    private WebView webView;
    private ImageView detailImageView;
    private ProgressBar progressBar;

    public PostDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(ARG_ITEM_ID)) {
            postData = getArguments().getParcelable(ARG_ITEM_ID);
            setupToolbar();
        }

        presenter = new PostDetailPresenter(postData);
    }

    private void setupToolbar() {
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("r/" + postData.getSubreddit());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        findViews(rootView);

        return rootView;
    }

    private void findViews(View rootView) {
        postDetailTextView = rootView.findViewById(R.id.postDetailTextView);
        webView = rootView.findViewById(R.id.webview);
        detailImageView = rootView.findViewById(R.id.detailImageView);
        progressBar = rootView.findViewById(R.id.progressBar);
    }

    @Override
    public void hideWebView() {
        webView.setVisibility(View.GONE);
    }

    @Override
    public void hideDetailsImageView() {
        detailImageView.setVisibility(View.GONE);
    }

    @Override
    public void displayPostDetailsText(String text) {
        postDetailTextView.setText(text);
    }

    @Override
    public void displayDetailImage(String url) {
        detailImageView.setVisibility(View.VISIBLE);
        Picasso.with(getContext()).load(url).into(detailImageView);
    }

    @Override
    public void displayWebViewUrl(String url) {
        webView.setVisibility(View.VISIBLE);
        webView.setWebViewClient(new FauxBrowser());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.setView(this);
    }

    @Override
    public void displayAlert(String message) {
        new AlertDialog.Builder(getContext())
                .setTitle("SubReadIt Problem Detected")
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    @Override
    public void displayProgressIndicator() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndicator() {
        progressBar.setVisibility(View.GONE);
    }

    private class FauxBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}

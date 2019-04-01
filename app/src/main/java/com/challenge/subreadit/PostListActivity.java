package com.challenge.subreadit;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.challenge.subreadit.model.children.PostList;

import java.util.ArrayList;

public class PostListActivity extends AppCompatActivity implements PostListPresenter.View {

    private PostListPresenter presenter;
    private EditText editText;
    private ProgressBar progressBar;
    private ImageButton button;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        findViews();
        setupToolbar();
        setupSearchButton();

        presenter = new PostListPresenter();
        presenter.setView(this);
    }

    private void setupSearchButton() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                presenter.updateData(editText.getText().toString());
            }
        });
    }

    private void findViews() {
        toolbar = findViewById(R.id.toolbar);
        progressBar = findViewById(R.id.progressBar);
        button = findViewById(R.id.imageButton);
        editText = findViewById(R.id.editText);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void displayAlert(String message) {
        new AlertDialog.Builder(this)
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
    public void setupRecyclerView(ArrayList<PostList> list) {
        RecyclerView recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;

        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.setAdapter(new PostListRecyclerViewAdapter(list));
        recyclerView.scheduleLayoutAnimation();
    }

    @Override
    public void displayProgressIndicator() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndicator() {
        progressBar.setVisibility(View.GONE);
    }

}

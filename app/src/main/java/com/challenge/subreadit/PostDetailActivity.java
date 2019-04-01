package com.challenge.subreadit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class PostDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        if (savedInstanceState == null) {

            Bundle arguments = new Bundle();
            arguments.putParcelable(PostDetailFragment.ARG_ITEM_ID,
                    getIntent().getParcelableExtra(PostDetailFragment.ARG_ITEM_ID));

            PostDetailFragment fragment = new PostDetailFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

}

package com.challenge.subreadit;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.challenge.subreadit.model.children.PostData;
import com.challenge.subreadit.model.children.PostList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostListRecyclerViewAdapter extends RecyclerView.Adapter<PostListRecyclerViewAdapter.PostViewHolder> {


    private ArrayList<PostList> mValues;


    PostListRecyclerViewAdapter(ArrayList<PostList> items) {
        mValues = items;
    }


    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            PostData item = (PostData) view.getTag();
            Context context = view.getContext();
            Intent intent = new Intent(context, PostDetailActivity.class);
            intent.putExtra(PostDetailFragment.ARG_ITEM_ID, item);
            context.startActivity(intent);
        }
    };


    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_content, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.titleTextView.setText("Title: " + mValues.get(position).getPostData().getTitle());
        holder.authorTextView.setText("By: " + mValues.get(position).getPostData().getAuthor());
        holder.subredditTextView.setText("Subreddit: " + mValues.get(position).getPostData().getSubreddit());

        String imageUrl = mValues.get(position).getPostData().getThumbnail();
        if(imageUrl != null && !imageUrl.isEmpty()){
            Picasso.with(holder.thumbnailImageView.getContext()).load(imageUrl).into(holder.thumbnailImageView);
        }


        holder.itemView.setTag(mValues.get(position).getPostData());
        holder.itemView.setOnClickListener(mOnClickListener);
    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }

    /**
     * View Holder
     */
    class PostViewHolder extends RecyclerView.ViewHolder {
        final TextView subredditTextView;
        final TextView authorTextView;
        final TextView titleTextView;
        final ImageView thumbnailImageView;
        final CardView cv;

        PostViewHolder(View view) {
            super(view);
            cv = view.findViewById(R.id.cv);
            subredditTextView =  view.findViewById(R.id.subredditTextView);
            authorTextView = view.findViewById(R.id.authorTextView);
            titleTextView = view.findViewById(R.id.titleTextView);
            thumbnailImageView = view.findViewById(R.id.thumbnailImage);
        }
    }
}

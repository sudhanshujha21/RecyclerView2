package com.example.himmanshoojha.recyclerview2;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;



public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

     ArrayList<ImageGallery> imageGalleries;
    Context cnt;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public ViewHolder(View v) {
            super(v);

            imageView= (ImageView) v.findViewById(R.id.imgvh);
        }
    }



    public MyAdapter(ArrayList<ImageGallery> imageGalleries,Context cnt) {
        this.cnt = cnt;

        this.imageGalleries = imageGalleries;

    }
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ImageGallery ig=imageGalleries.get(position);
        Picasso.with(cnt).load(ig.getUri()).placeholder(R.drawable.image_placeholder).into(holder.imageView);


    }


    @Override
    public int getItemCount() {
        return imageGalleries.size();
    }

}
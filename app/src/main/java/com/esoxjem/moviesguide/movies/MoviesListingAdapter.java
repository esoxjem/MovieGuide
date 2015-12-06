package com.esoxjem.moviesguide.movies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.esoxjem.moviesguide.R;

/**
 * @author arun
 */
public class MoviesListingAdapter extends android.support.v7.widget.RecyclerView.Adapter
{
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView mMovieName;
        public ImageView mMoviePoster;

        public ViewHolder(View root)
        {
            super(root);
            mMovieName = (TextView) root.findViewById(R.id.movie_name);
            mMoviePoster = (ImageView) root.findViewById(R.id.movie_poster);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_grid_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(rootView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {

    }

    @Override
    public int getItemCount()
    {
        return 8;
    }
}

package com.esoxjem.moviesguide.movies;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.esoxjem.moviesguide.R;

import java.util.List;

/**
 * @author arun
 */
public class MoviesListingAdapter extends android.support.v7.widget.RecyclerView.Adapter<MoviesListingAdapter.ViewHolder>
{
    private List<Movie> mMovies;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView mMovieName;
        public ImageView mMoviePoster;
        public View mTitleBackground;

        public ViewHolder(View root)
        {
            super(root);
            mMovieName = (TextView) root.findViewById(R.id.movie_name);
            mMoviePoster = (ImageView) root.findViewById(R.id.movie_poster);
            mTitleBackground = root.findViewById(R.id.title_background);
        }
    }

    public MoviesListingAdapter(List<Movie> movies)
    {
        mMovies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        mContext = parent.getContext();
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.movie_grid_item, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        holder.mMovieName.setText(mMovies.get(position).getTitle());
        Glide.with(mContext).load(mMovies.get(position)
                .getPosterPath()).asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(new BitmapImageViewTarget(holder.mMoviePoster)
    {
        @Override
        public void onResourceReady(Bitmap bitmap, GlideAnimation anim)
        {
            super.onResourceReady(bitmap, anim);

            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener()
            {
                @Override
                public void onGenerated(Palette palette)
                {
                    holder.mTitleBackground.setBackgroundColor(palette.getVibrantColor(Color.BLACK));
                }
            });
        }
    });
    }


    @Override
    public int getItemCount()
    {
        return mMovies.size();
    }
}

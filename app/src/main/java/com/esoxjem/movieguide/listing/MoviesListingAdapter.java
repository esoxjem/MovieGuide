package com.esoxjem.movieguide.listing;

import android.content.Context;
import android.graphics.Bitmap;
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
import com.esoxjem.movieguide.Movie;
import com.esoxjem.movieguide.R;

import java.util.List;

/**
 * @author arun
 */
public class MoviesListingAdapter extends RecyclerView.Adapter<MoviesListingAdapter.ViewHolder>
{
    private List<Movie> movies;
    private Context context;
    private IMoviesListingView view;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView name;
        public ImageView poster;
        public View titleBackground;
        public Movie movie;

        public ViewHolder(View root)
        {
            super(root);
            name = (TextView) root.findViewById(R.id.movie_name);
            poster = (ImageView) root.findViewById(R.id.movie_poster);
            titleBackground = root.findViewById(R.id.title_background);
        }

        @Override
        public void onClick(View view)
        {
            MoviesListingAdapter.this.view.onMovieClicked(movie);
        }
    }

    public MoviesListingAdapter(List<Movie> movies, IMoviesListingView moviesView)
    {
        this.movies = movies;
        view = moviesView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        context = parent.getContext();
        View rootView = LayoutInflater.from(context).inflate(R.layout.movie_grid_item, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        holder.itemView.setOnClickListener(holder);
        holder.movie = movies.get(position);
        holder.name.setText(holder.movie.getTitle());
        Glide.with(context).load(holder.movie
                .getPosterPath()).asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(new BitmapImageViewTarget(holder.poster)
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
                                holder.titleBackground.setBackgroundColor(palette.getVibrantColor(context
                                        .getResources().getColor(R.color.black_translucent_60)));
                            }
                        });
                    }
                });
    }

    @Override
    public int getItemCount()
    {
        return movies.size();
    }
}

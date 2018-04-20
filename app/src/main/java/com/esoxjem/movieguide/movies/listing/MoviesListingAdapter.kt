package com.esoxjem.movieguide.movies.listing

import android.content.Context
import android.graphics.Bitmap
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.esoxjem.movieguide.network.Api
import com.esoxjem.movieguide.movies.entities.Movie
import com.esoxjem.movieguide.R

import butterknife.BindView
import butterknife.ButterKnife

/**
 * @author arunsasidharan
 */
class MoviesListingAdapter(private val movies: List<Movie>, private val view: MoviesListingView) : RecyclerView.Adapter<MoviesListingAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(root: View) : RecyclerView.ViewHolder(root), View.OnClickListener {
        @BindView(R.id.poster)
        lateinit var poster: ImageView
        @BindView(R.id.title_background)
        lateinit var titleBackground: View
        @BindView(R.id.title)
        lateinit var name: TextView
        lateinit var movie: Movie

        init {
            ButterKnife.bind(this, root)
        }

        override fun onClick(view: View) {
            this@MoviesListingAdapter.view.onMovieClicked(movie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val rootView = LayoutInflater.from(context).inflate(R.layout.movie_grid_item, parent, false)

        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener(holder)
        holder.movie = movies[position]
        holder.name.text = holder.movie.title

        val options = RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH)

        Glide.with(context)
                .asBitmap()
                .load(Api.getPosterPath(holder.movie.posterPath))
                .apply(options)
                .into(object : BitmapImageViewTarget(holder.poster) {
                    override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
                        super.onResourceReady(bitmap, transition)
                        Palette.from(bitmap).generate { palette -> setBackgroundColor(palette, holder) }
                    }
                })
    }

    private fun setBackgroundColor(palette: Palette, holder: ViewHolder) {
        holder.titleBackground
                .setBackgroundColor(
                        palette.getVibrantColor(
                                ContextCompat.getColor(context, R.color.black_translucent_60)))
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}

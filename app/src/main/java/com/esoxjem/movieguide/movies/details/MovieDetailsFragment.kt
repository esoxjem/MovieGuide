package com.esoxjem.movieguide.movies.details


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.OnClick
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.esoxjem.movieguide.BaseApplication
import com.esoxjem.movieguide.R
import com.esoxjem.movieguide.movies.Constants
import com.esoxjem.movieguide.movies.entities.Movie
import com.esoxjem.movieguide.movies.entities.Review
import com.esoxjem.movieguide.movies.entities.Video
import com.esoxjem.movieguide.network.Api
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.review.*
import kotlinx.android.synthetic.main.trailers_and_reviews.*
import javax.inject.Inject

class MovieDetailsFragment : Fragment(), MovieDetailsView, View.OnClickListener {

    companion object {
        fun getInstance(movie: Movie): MovieDetailsFragment {
            val args = Bundle()
            args.putParcelable(Constants.MOVIE, movie)
            val movieDetailsFragment = MovieDetailsFragment()
            movieDetailsFragment.arguments = args
            return movieDetailsFragment
        }
    }

    @Inject
    lateinit var movieDetailsPresenter: MovieDetailsPresenter

    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        injectFragment()
    }

    private fun injectFragment() {
        (activity?.application as BaseApplication)
                .createDetailsComponent()
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        val movie = arguments?.get(Constants.MOVIE) as Movie
        this.movie = movie

        movieDetailsPresenter.apply {
            setView(this@MovieDetailsFragment)
            showDetails(movie)
            showFavoriteButton(movie)
        }
    }

    private fun setToolbar() {
        collapsing_toolbar.apply {
            setContentScrimColor(ContextCompat.getColor(context, R.color.colorPrimary))
            title = getString(R.string.movie_details)
            setCollapsedTitleTextAppearance(R.style.CollapsedToolbar)
            setExpandedTitleTextAppearance(R.style.ExpandedToolbar)
            isTitleEnabled = true
        }

        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun showDetails(movie: Movie) {
        Glide.with(context!!).load(Api.getBackdropPath(movie.backdropPath)).into(poster)
        title.text = movie.title
        release_date.text = String.format(getString(R.string.release_date), movie.releaseDate)
        rating.text = String.format(getString(R.string.rating), movie.voteAverage.toString())
        overview.text = movie.overview

        movieDetailsPresenter.showTrailers(movie)
        movieDetailsPresenter.showReviews(movie)
    }

    override fun showTrailers(trailers: List<Video>) {
        if (trailers.isEmpty()) {
            trailers_label.visibility = View.GONE
            this.trailers.visibility = View.GONE
            trailers_container.visibility = View.GONE

        } else {
            trailers_label.visibility = View.VISIBLE
            this.trailers.visibility = View.VISIBLE
            trailers_container.visibility = View.VISIBLE

            this.trailers.removeAllViews()
            val inflater = activity?.layoutInflater
            val options = RequestOptions()
                    .placeholder(R.color.colorPrimary)
                    .centerCrop()
                    .override(150, 150)

            for (trailer in trailers) {
                val thumbContainer = inflater!!.inflate(R.layout.video, this.trailers, false)
                val thumbView = thumbContainer.findViewById<ImageView>(R.id.video_thumb)

                thumbView.setTag(R.id.glide_tag, Video.getUrl(trailer))
                thumbView.requestLayout()
                thumbView.setOnClickListener(this)
                Glide.with(context!!)
                        .load(Video.getThumbnailUrl(trailer))
                        .apply(options)
                        .into(thumbView)
                this.trailers.addView(thumbContainer)
            }
        }
    }

    override fun showReviews(reviews: List<Review>) {
        if (reviews.isEmpty()) {
            this.reviews_label.visibility = View.GONE
            this.reviews.visibility = View.GONE
        } else {
            this.reviews_label.visibility = View.VISIBLE
            this.reviews.visibility = View.VISIBLE

            this.reviews.removeAllViews()
            val inflater = activity?.layoutInflater
            for (review in reviews) {
                val reviewContainer = inflater?.inflate(R.layout.review, this.reviews, false) as ViewGroup

//                val reviewAuthor = reviewContainer.findViewById<TextView>(R.id.review_author)
//                val reviewContent = reviewContainer.findViewById<TextView>(R.id.review_content)

                review_author.text = review.author
                review_content.text = review.content
                review_content.setOnClickListener(this)

                this.reviews.addView(reviewContainer)
            }
        }
    }

    override fun showFavorited() {
        favorite.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_favorite_white_24dp))
    }

    override fun showUnfavorited() {
        favorite.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_favorite_border_white_24dp))
    }

    @OnClick(R.id.favorite)
    override fun onClick(view: View) {
        when (view.id) {
            R.id.video_thumb -> onThumbnailClick(view)
            R.id.review_content -> onReviewClick(view as TextView)
            R.id.favorite -> onFavoriteClick()
        }
    }

    private fun onReviewClick(view: TextView) {
        if (view.maxLines == 5) {
            view.maxLines = 500
        } else {
            view.maxLines = 5
        }
    }

    private fun onThumbnailClick(view: View) {
        val videoUrl = view.getTag(R.id.glide_tag) as String
        val playVideoIntent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
        startActivity(playVideoIntent)
    }

    private fun onFavoriteClick() {
        movieDetailsPresenter.onFavoriteClick(movie)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        movieDetailsPresenter.destroy()
    }
}

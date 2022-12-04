package com.juacodev.fullmovies.ui.activities.fragments.homefragment.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.juacodev.fullmovies.data.network.ServerUrls.IMAGE_PATH
import com.juacodev.fullmovies.databinding.ItemMovieLayoutBinding
import com.juacodev.fullmovies.domain.models.DMovie
import com.juacodev.fullmovies.ui.activities.fragments.listeners.MovieSelected

class BasicMoviesAdapter (
    private val onMovieSelected: MovieSelected
        ) :RecyclerView.Adapter<BasicMoviesAdapter.BasicMoviesViewHolder>() {

    private val movies:MutableList<DMovie> = mutableListOf()

    fun setList(list:List<DMovie>){
        movies.clear()
        movies.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasicMoviesViewHolder {
        return BasicMoviesViewHolder(ItemMovieLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: BasicMoviesViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount()=movies.size

    inner class BasicMoviesViewHolder(
        private var itemBinding:ItemMovieLayoutBinding
    ) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(movie: DMovie){
            val imageUrl="$IMAGE_PATH${movie.poster_path}"
            Glide.with(itemBinding.root.context)
                .load(imageUrl)
                .into(itemBinding.movieImage)

            itemBinding.movieImage.setOnClickListener {
                onMovieSelected.onMovieSelected(movie)
            }

        }

    }
}
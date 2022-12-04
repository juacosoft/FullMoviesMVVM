package com.juacodev.fullmovies.ui.activities.fragments.detailsmovefragment

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.juacodev.fullmovies.R
import com.juacodev.fullmovies.data.network.ServerUrls
import com.juacodev.fullmovies.databinding.FragmentMovieDetailBinding
import com.juacodev.fullmovies.domain.models.DMovie
import com.juacodev.fullmovies.ui.activities.fragments.BaseFragment
import kotlin.math.floor

class DetailsMovieFragment :BaseFragment<FragmentMovieDetailBinding>(
    FragmentMovieDetailBinding::inflate
){
    private val arguments:DetailsMovieFragmentArgs by navArgs()
    private var movie: DMovie?=null
    override fun onViews() {
        binding.ivDetailsmovieBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnDetailmovieTrailer.setOnClickListener {
            movie?.id?.let {
                findNavController().navigate(DetailsMovieFragmentDirections.actionDetailsMovieFragmentToViewTrailerFragment(it))
            }

        }
        movie=arguments.movie
        movie?.let {mMovie->
            val imageUrl="${ServerUrls.IMAGE_PATH}${mMovie.backdrop_path}"
            with(binding){
                tvDetailmovieTitle.text=mMovie.title
                tvDetailmovieYear.text=mMovie.original_language
                tvDetailmovieLanguage.text=mMovie.release_date.split("-").firstOrNull()?:"0000"
                tvDetailmovieRate.text = String.format(resources.getString(R.string.rate_template),floor(mMovie.vote_average).toString())
                tvDetailmovieMovieplot.text=mMovie.overview

                Glide.with(this@DetailsMovieFragment)
                    .load(imageUrl)
                    .into(ivDetailsmoviePoster)
            }
        }
    }

    override fun onViewModels() {

    }
}
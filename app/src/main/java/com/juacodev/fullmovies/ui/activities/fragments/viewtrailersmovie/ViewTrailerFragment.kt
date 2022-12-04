package com.juacodev.fullmovies.ui.activities.fragments.viewtrailersmovie

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.juacodev.fullmovies.databinding.FragmentViewTrailerBinding
import com.juacodev.fullmovies.ui.activities.fragments.BaseFragment
import com.juacodev.fullmovies.utils.BasicResourceData
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewTrailerFragment : BaseFragment<FragmentViewTrailerBinding>(
    FragmentViewTrailerBinding::inflate
) {
    private val args: ViewTrailerFragmentArgs by navArgs()
    private val viewModel: TrailerViewModel by viewModels()
    override fun onViews() {
        binding.ivDetailsmovieBack.setOnClickListener {
            findNavController().popBackStack()
        }
        args.movieId?.let {id->
            viewModel.getTrailer(id)
            lifecycle.addObserver(binding.youtubePlayerView)
        }
    }

    override fun onViewModels() {
        viewModel.resourceTrailer.observe(viewLifecycleOwner){
            when(it){
                is BasicResourceData.Error -> Unit
                is BasicResourceData.Loading -> Unit
                is BasicResourceData.LocalData -> Unit
                is BasicResourceData.Success -> {
                    val data=it.data?.firstOrNull()
                    data?.key?.let {videoKey->
                        binding.youtubePlayerView.addYouTubePlayerListener(
                            object : AbstractYouTubePlayerListener() {
                                override fun onReady(youTubePlayer: YouTubePlayer) {
                                    youTubePlayer.loadVideo(videoKey, 0f)
                                    youTubePlayer.play()
                                }
                            })
                    }
                }
            }
        }
    }

}
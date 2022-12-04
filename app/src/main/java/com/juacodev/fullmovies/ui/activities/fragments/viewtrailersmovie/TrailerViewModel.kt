package com.juacodev.fullmovies.ui.activities.fragments.viewtrailersmovie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juacodev.fullmovies.data.network.Failure
import com.juacodev.fullmovies.data.network.data.VideoModel
import com.juacodev.fullmovies.data.network.data.VideosResponse
import com.juacodev.fullmovies.domain.usecases.GetTrailersUseCase
import com.juacodev.fullmovies.utils.BasicResourceData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class TrailerViewModel @Inject constructor(
    private val trailersUseCase: GetTrailersUseCase
) :ViewModel() {
    private val job= Job()
    private val _resourceTrailer:MutableLiveData<BasicResourceData<List<VideoModel>>> = MutableLiveData()
    val resourceTrailer:MutableLiveData<BasicResourceData<List<VideoModel>>> get() = _resourceTrailer

    fun getTrailer(movieId:Int){
        _resourceTrailer.value=BasicResourceData.Loading()
        trailersUseCase(job,movieId){
            it.fold(::handleFailure,::handleSuccess)
        }
    }
    private fun handleSuccess(videosResponse: VideosResponse){
        val result=videosResponse.results
        _resourceTrailer.value=BasicResourceData.Success(result)
    }
    private fun handleFailure(failure: Failure) {
        _resourceTrailer.value=BasicResourceData.Error(Exception(failure.toString()))
    }

}
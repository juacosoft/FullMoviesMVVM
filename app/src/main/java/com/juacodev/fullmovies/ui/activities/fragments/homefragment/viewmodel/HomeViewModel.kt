package com.juacodev.fullmovies.ui.activities.fragments.homefragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juacodev.fullmovies.data.database.ClasificateMovies
import com.juacodev.fullmovies.data.network.Failure
import com.juacodev.fullmovies.data.network.response.MoviesResponse
import com.juacodev.fullmovies.domain.LocalRepository
import com.juacodev.fullmovies.domain.models.DMovie
import com.juacodev.fullmovies.domain.usecases.GetRecomendationsMoviesUseCase
import com.juacodev.fullmovies.domain.usecases.GetTopRatedUseCase
import com.juacodev.fullmovies.domain.usecases.GetUpCommingMoviesUseCase
import com.juacodev.fullmovies.utils.BasicResourceData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTopRatedUseCase: GetTopRatedUseCase,
    private val getUpCommingMoviesUseCase: GetUpCommingMoviesUseCase,
    private val getRecomendationsMoviesUseCase: GetRecomendationsMoviesUseCase,
    private val localRepository: LocalRepository
) :ViewModel(){
    private val job = Job()

    //Pendiente Manejar paginado por tipo de consulta
    private var page:Int=0
    private var ofSet:Int=20
    private val _toRatedMovies:MutableLiveData<BasicResourceData<List<DMovie>>> = MutableLiveData()
    val toRatedMovies:MutableLiveData<BasicResourceData<List<DMovie>>> get() = _toRatedMovies

    private val _upcomingMovies:MutableLiveData<BasicResourceData<List<DMovie>>> = MutableLiveData()
    val upComingMovies:MutableLiveData<BasicResourceData<List<DMovie>>> get() = _upcomingMovies


    init {
        page++
        syncGetTopRate()
        syncUpComingMovies()

    }
    fun syncUpComingMovies(){
        _upcomingMovies.value=BasicResourceData.Loading()
        getUpCommingMoviesUseCase(job,page){
            it.fold(::handleUpcomingFailure,::handleUpComingMovies)
        }

    }
    fun syncGetTopRate(){
        _toRatedMovies.value= BasicResourceData.Loading()
        getTopRatedUseCase(job,page){
            it.fold(::handleTopRatedFailure,::handleTopRatedMovies)
        }
    }
    private fun handleTopRatedMovies(movies: MoviesResponse) {
        val data = movies.results.map { it.toDomain() }
        _toRatedMovies.value= BasicResourceData.Success(data)
        viewModelScope.launch (Dispatchers.IO) {
            localRepository.insertAllMovies(data,ClasificateMovies.TOP_RATED)
        }

    }
    private fun handleUpComingMovies(movies: MoviesResponse) {
        val data = movies.results.map { it.toDomain() }
        _upcomingMovies.value= BasicResourceData.Success(data)
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.insertAllMovies(data,ClasificateMovies.UPCOMING)
        }
    }



    //=========failures================
    private fun handleUpcomingFailure(failure: Failure) {
        viewModelScope.launch (Dispatchers.IO){
            val data=localRepository.getMoviesByClasificate(clasificateMovies = ClasificateMovies.UPCOMING,offset = page)
            if(data.isNotEmpty()){
                _upcomingMovies.postValue(BasicResourceData.LocalData(data.map { it.toDomain() }))
            }else{
                _upcomingMovies.postValue(BasicResourceData.Error(Exception(failure.toString())))
            }
        }
    }

    private fun handleTopRatedFailure(failure: Failure) {
        viewModelScope.launch (Dispatchers.IO){
            val data=localRepository.getMoviesByClasificate(clasificateMovies = ClasificateMovies.TOP_RATED,offset = page)
            if(data.isNotEmpty()){
                _toRatedMovies.postValue(BasicResourceData.LocalData(data.map { it.toDomain() }))
            }else{
                _toRatedMovies.postValue(BasicResourceData.Error(Exception(failure.toString())))
            }
        }
    }
}
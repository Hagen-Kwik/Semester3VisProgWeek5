package com.uc.week4_retrofit_visprog.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uc.week4_retrofit_visprog.model.*
import com.uc.week4_retrofit_visprog.repository.NowPlayingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: NowPlayingRepository): ViewModel(){
    val _nowPlaying: MutableLiveData<ArrayList<Result>> by lazy {
        MutableLiveData<ArrayList<Result>>()
    }

    val nowPlaying: LiveData<ArrayList<Result>>
    get() = _nowPlaying

    fun getNowPlaying(apiKey: String, language:String, page:Int) =
        viewModelScope.launch{
            repository.getNowPlayingResults(apiKey, language, page).let {
                response ->
                if (response.isSuccessful){
                    _nowPlaying.postValue(response.body()?.results as ArrayList<Result>?)
                } else {
                    Log.e("Get Data", "Failed")
                }
            }
        }

    //movie details
    val _movieDetails: MutableLiveData<MovieDetails> by lazy {
        MutableLiveData<MovieDetails>()
    }

    val MovieDetails: LiveData<MovieDetails>
        get() = _movieDetails

    fun getMovieDetaials(apiKey: String, movieid:Int) =
        viewModelScope.launch{
            repository.getMovieDetailsResults(apiKey, movieid).let {
                    response ->
                if (response.isSuccessful){
                    _movieDetails.postValue(response.body() as MovieDetails)
                } else {
                    Log.e("Get Data Movie Detail", "Failed")
                }
            }
        }
}
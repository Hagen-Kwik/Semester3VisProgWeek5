package com.uc.week4_retrofit_visprog.repository

import com.uc.week4_retrofit_visprog.retrofit.EndPointApi
import javax.inject.Inject

class NowPlayingRepository @Inject constructor(private val api: EndPointApi){



    suspend fun getNowPlayingResults(apiKey: String, language:String, page:Int) = api.getNowPlaying(apiKey, language, page)

    suspend fun getMovieDetailsResults(apiKey: String, id: Int) = api.getMovieDetails(id, apiKey)
}
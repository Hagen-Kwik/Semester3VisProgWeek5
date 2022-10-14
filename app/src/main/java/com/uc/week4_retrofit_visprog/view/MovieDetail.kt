package com.uc.week4_retrofit_visprog.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.remove
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.uc.week4_retrofit_visprog.R
import com.uc.week4_retrofit_visprog.adapter.CompanyAdapter
import com.uc.week4_retrofit_visprog.adapter.GenreAdapter
import com.uc.week4_retrofit_visprog.adapter.LanguageAdapter
import com.uc.week4_retrofit_visprog.adapter.NowPlayingAdapter
import com.uc.week4_retrofit_visprog.helper.Const
import com.uc.week4_retrofit_visprog.model.Genre
import com.uc.week4_retrofit_visprog.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_movie_detail.*
import java.util.EnumSet.range

@AndroidEntryPoint
class MovieDetail : AppCompatActivity() {

    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var adapter: LanguageAdapter
    private lateinit var GenreAdapter: GenreAdapter
    private lateinit var Companyadapter: CompanyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)


        val movieId = intent.getIntExtra("movie_id", 0)
        Toast.makeText(applicationContext, "Movie ID: ${movieId}", Toast.LENGTH_SHORT).show()

        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        moviesViewModel.getMovieDetaials(Const.API_KEY,movieId)
        moviesViewModel.MovieDetails.observe(this, Observer { response ->
            tv_title_movie_detail.text = response.title
            tv_overview.text = response.overview

            Glide.with(applicationContext)
                .load(Const.IMG_URL + response.backdrop_path)
                .into(img_poster_movie_detail)

            //
            rv_language.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
          adapter = LanguageAdapter(response.spoken_languages)
            Log.d("here", response.spoken_languages.toString())
            rv_language.adapter = adapter

            //
            rv_company.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
          Companyadapter = CompanyAdapter(response.production_companies)
            rv_company.adapter = Companyadapter

            //
            rv_genre.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            GenreAdapter = GenreAdapter(response.genres)
            rv_genre.adapter = GenreAdapter


            loading_progress.visibility = View.GONE

        })





    }

}
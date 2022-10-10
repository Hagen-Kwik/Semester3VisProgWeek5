package com.uc.week4_retrofit_visprog.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.uc.week4_retrofit_visprog.R
import com.uc.week4_retrofit_visprog.adapter.NowPlayingAdapter
import com.uc.week4_retrofit_visprog.helper.Const
import com.uc.week4_retrofit_visprog.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var adapter: NowPlayingAdapter
    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
        viewModel.getNowPlaying(Const.API_KEY,"en-US",1)
        viewModel.nowPlaying.observe(this, Observer { response->
            rv_main.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
            adapter = NowPlayingAdapter(response)
            rv_main.adapter = adapter
        })
    }


}
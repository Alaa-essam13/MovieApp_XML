package com.example.movieapp_xml.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movieapp_xml.R
import com.example.movieapp_xml.data.MovieApi
import com.example.movieapp_xml.data.remote.MovieDetails
import com.example.movieapp_xml.databinding.ActivityDetailBinding
import com.example.movieapp_xml.databinding.ActivityMainBinding
import com.example.movieapp_xml.ui.adapters.ActorsListAdapter
import com.example.movieapp_xml.ui.adapters.CategoryEachFilmListAdapter
import com.example.movieapp_xml.ui.adapters.SlideAdapter
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.properties.Delegates

class DetailActivity : AppCompatActivity() {
    lateinit var FDetail:MovieDetails
    lateinit var binding: ActivityDetailBinding
    private lateinit var actorsListAdapter: ActorsListAdapter
    private lateinit var categoryListAdapter: CategoryEachFilmListAdapter
    var filmId :Int = 0
    val retrofit = Retrofit.Builder()
        .baseUrl(MovieApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieApi::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_detail)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_detail)
        binding.lifecycleOwner=this
        filmId=intent.getIntExtra("id",0)
        initView()

            getFilmDetails()

    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getFilmDetails() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val reqDataDeferred = async(Dispatchers.IO) {
                    retrofit.getMovieDetails(filmId)
                }
                FDetail = reqDataDeferred.await()

                binding.apply {
                    progressBar4.isVisible = false
                    Glide.with(root.context).load(FDetail.poster).into(picDetail)
                    movieTitle.text = FDetail.title
                    movieRate.text = FDetail.imdb_rating
                    movieTime.text = FDetail.runtime
                    moviesummary.text = FDetail.plot
                    movieActionInfo.text = FDetail.actors
                    categoryListAdapter = CategoryEachFilmListAdapter(FDetail.genres)
                    generyView.adapter = categoryListAdapter


                    actorsListAdapter = ActorsListAdapter(FDetail.images)
                    imgesRecycler.adapter = actorsListAdapter
                    imgesRecycler.setPadding(0,0,0,0)

                }
            } catch (e: HttpException) {
                Log.e("DetailActivity", "HTTP error: ${e.code()} - ${e.message()}")
                binding.progressBar4.isVisible = false
            } catch (e: Exception) {
                Log.e("DetailActivity", "Unexpected error: ${e.localizedMessage}")
                binding.progressBar4.isVisible = false
            }
        }
    }

    private fun initView() {
        binding.apply {
            generyView.layoutManager=LinearLayoutManager(root.context, LinearLayoutManager.HORIZONTAL,false)
            imgesRecycler.layoutManager=LinearLayoutManager(root.context, LinearLayoutManager.HORIZONTAL,false)
            backimg.setOnLongClickListener {
                finish()
                true
            }
            progressBar4.isVisible=true
        }

    }
}
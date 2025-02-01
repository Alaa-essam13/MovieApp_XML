package com.example.movieapp_xml.ui.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.movieapp_xml.R
import com.example.movieapp_xml.data.MovieApi
import com.example.movieapp_xml.data.remote.Data
import com.example.movieapp_xml.databinding.ActivityMainBinding
import com.example.movieapp_xml.ui.Domain.SliderItem
import com.example.movieapp_xml.ui.adapters.CategoryListAdapter
import com.example.movieapp_xml.ui.adapters.FilmListAdapter
import com.example.movieapp_xml.ui.adapters.SlideAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var slideAdapter: SlideAdapter
    private lateinit var filmAdapter1: FilmListAdapter
    private lateinit var filmAdapter2: FilmListAdapter
    private lateinit var filmAdapter3: CategoryListAdapter
    val retrofit = Retrofit.Builder()
    .baseUrl(MovieApi.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(MovieApi::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.lifecycleOwner=this
        banner()
        view1()
        view2()
        view3()
        requestData()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun requestData() {

        GlobalScope.launch(Dispatchers.Main) {
            val reqDataDeferred = async(Dispatchers.IO) {
                retrofit.getMovies(1).data
            }
            val reqUpComDeferred = async(Dispatchers.IO) {
                retrofit.getMovies(2).data
            }
            val categoryDeff = async(Dispatchers.IO) {
                retrofit.getGenres()
            }

            val reqData1 = reqDataDeferred.await()
            filmAdapter1 = FilmListAdapter(reqData1)
            binding.view1.adapter = filmAdapter1
            binding.progressBar1.isVisible=false

            val reqData2 = reqUpComDeferred.await()
            filmAdapter2 = FilmListAdapter(reqData2)
            binding.view3.adapter = filmAdapter2
            binding.progressBar3.isVisible=false

            val categoryList = categoryDeff.await()
            filmAdapter3 = CategoryListAdapter(categoryList)
            binding.view2.adapter = filmAdapter3
            binding.progressBar2.isVisible=false
        }

    }

    private fun view1() {
        binding.view1.apply {
            layoutManager= LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        }
    }
    private fun view2() {
        binding.view2.apply {
            layoutManager= LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        }
    }
    private fun view3() {
        binding.view3.apply {
            layoutManager= LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        }
    }

    private fun banner() {
        val sliderItems=listOf<SliderItem>(
            SliderItem(R.drawable.wide1),
            SliderItem(R.drawable.wide),
            SliderItem(R.drawable.wide3)
        )
        slideAdapter = SlideAdapter(sliderItems, binding.viewPagerSlider)

        binding.viewPagerSlider.apply {
            adapter = slideAdapter
            clipToPadding = false
            clipChildren=false
            offscreenPageLimit=3
            setPageTransformer(MarginPageTransformer(30))
            setPadding(100,0,100,0)
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            getChildAt(0).overScrollMode= RecyclerView.OVER_SCROLL_NEVER
        }



       autoScrollBanner()
    }

    private fun autoScrollBanner() {
        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                val currentItem = binding.viewPagerSlider.currentItem
                val totalItems = slideAdapter.itemCount
                binding.viewPagerSlider.setCurrentItem((currentItem + 1) % totalItems, true)
                handler.postDelayed(this, 3000)
            }
        }
        handler.postDelayed(runnable, 3000)
    }


}
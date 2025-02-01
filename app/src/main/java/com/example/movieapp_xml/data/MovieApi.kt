package com.example.movieapp_xml.data


import com.example.movieapp_xml.data.remote.Genres
import com.example.movieapp_xml.data.remote.MoviesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movies")
    suspend fun getMovies(
        @Query("page") page:Int
    ): MoviesDto

    @GET("genres")
    suspend fun getGenres(): Genres

    companion object {
        const val BASE_URL = "https://moviesapi.ir/api/v1/"
    }
}
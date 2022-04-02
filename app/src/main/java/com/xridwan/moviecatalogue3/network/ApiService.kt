package com.xridwan.moviecatalogue3.network

import com.xridwan.moviecatalogue3.data.source.remote.response.Movie
import com.xridwan.moviecatalogue3.data.source.remote.response.MovieResponse
import com.xridwan.moviecatalogue3.data.source.remote.response.TvShow
import com.xridwan.moviecatalogue3.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getMovies(
        @Query("api_key") apiKey: String
    ): Call<MovieResponse>

    @GET("movie/{id}")
    fun getMovie(
        @Path("id") id: String,
        @Query("api_key") apiKey: String
    ): Call<Movie>

    @GET("tv/popular")
    fun getTvShows(
        @Query("api_key") apiKey: String
    ): Call<TvShowResponse>

    @GET("tv/{id}")
    fun getTvShow(
        @Path("id") id: String,
        @Query("api_key") apiKey: String
    ): Call<TvShow>
}
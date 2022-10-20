package com.xridwan.moviecatalogue3.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xridwan.moviecatalogue3.BuildConfig.API_KEY
import com.xridwan.moviecatalogue3.data.source.remote.response.Movie
import com.xridwan.moviecatalogue3.data.source.remote.response.MovieResponse
import com.xridwan.moviecatalogue3.data.source.remote.response.TvShow
import com.xridwan.moviecatalogue3.data.source.remote.response.TvShowResponse
import com.xridwan.moviecatalogue3.network.ApiConfig
import com.xridwan.moviecatalogue3.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
//
class RemoteDataSource {

    fun getMovieList(): LiveData<ApiResponse<List<Movie>>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<Movie>>>()
        val client = ApiConfig.getApiService().getMovies(API_KEY)
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                resultMovies.value = ApiResponse.success(response.body()?.results as List<Movie>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultMovies
    }

    fun getMovieDetail(movieId: String): LiveData<ApiResponse<Movie>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<Movie>>()
        val client = ApiConfig.getApiService().getMovie(movieId, API_KEY)
        client.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                resultMovie.value = ApiResponse.success(response.body() as Movie)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.e("RemoteDataSource", "onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultMovie
    }

    fun getTvShowList(): LiveData<ApiResponse<List<TvShow>>> {
        EspressoIdlingResource.increment()
        val resultTvShows = MutableLiveData<ApiResponse<List<TvShow>>>()
        val client = ApiConfig.getApiService().getTvShows(API_KEY)
        client.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                resultTvShows.value = ApiResponse.success(response.body()?.results as List<TvShow>)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultTvShows
    }

    fun getTvShowDetail(tvShowId: String): LiveData<ApiResponse<TvShow>> {
        EspressoIdlingResource.increment()
        val resultTvShow = MutableLiveData<ApiResponse<TvShow>>()
        val client = ApiConfig.getApiService().getTvShow(tvShowId, API_KEY)
        client.enqueue(object : Callback<TvShow> {
            override fun onResponse(call: Call<TvShow>, response: Response<TvShow>) {
                resultTvShow.value = ApiResponse.success(response.body() as TvShow)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShow>, t: Throwable) {
                Log.e("RemoteDataSource", "onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultTvShow
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }
}
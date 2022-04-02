package com.xridwan.moviecatalogue3.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xridwan.moviecatalogue3.data.MovieCatalogueRepository
import com.xridwan.moviecatalogue3.di.Injection
import com.xridwan.moviecatalogue3.ui.detail.DetailViewModel
import com.xridwan.moviecatalogue3.ui.favorite.movie.MovieFavoriteViewModel
import com.xridwan.moviecatalogue3.ui.favorite.tvshow.TvShowFavoriteViewModel
import com.xridwan.moviecatalogue3.ui.movie.MovieViewModel
import com.xridwan.moviecatalogue3.ui.tvshow.TvShowViewModel

class ViewModelFactory private constructor(
    private val movieCatalogueRepository: MovieCatalogueRepository
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) ->
                MovieViewModel(movieCatalogueRepository) as T

            modelClass.isAssignableFrom(TvShowViewModel::class.java) ->
                TvShowViewModel(movieCatalogueRepository) as T

            modelClass.isAssignableFrom(MovieFavoriteViewModel::class.java) ->
                MovieFavoriteViewModel(movieCatalogueRepository) as T

            modelClass.isAssignableFrom(TvShowFavoriteViewModel::class.java) ->
                TvShowFavoriteViewModel(movieCatalogueRepository) as T

            modelClass.isAssignableFrom(DetailViewModel::class.java) ->
                DetailViewModel(movieCatalogueRepository) as T

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }
}
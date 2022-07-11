package com.xridwan.moviecatalogue3.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.xridwan.moviecatalogue3.data.source.local.LocalDataSource
import com.xridwan.moviecatalogue3.data.source.local.entity.MovieEntity
import com.xridwan.moviecatalogue3.data.source.local.entity.TvShowEntity
import com.xridwan.moviecatalogue3.data.source.remote.ApiResponse
import com.xridwan.moviecatalogue3.data.source.remote.RemoteDataSource
import com.xridwan.moviecatalogue3.data.source.remote.response.Movie
import com.xridwan.moviecatalogue3.data.source.remote.response.TvShow
import com.xridwan.moviecatalogue3.utils.AppExecutors
import com.xridwan.moviecatalogue3.vo.Resource

class MovieCatalogueRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieCatalogueDataSource {

    override fun getMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<Movie>>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<Movie>>> {
                return remoteDataSource.getMovieList()
            }

            override fun saveCallResult(data: List<Movie>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        id = response.id,
                        backdropPath = response.backdropPath,
                        overview = response.overview,
                        popularity = response.popularity,
                        posterPath = response.posterPath,
                        releaseDate = response.releaseDate,
                        title = response.title,
                        voteCount = response.voteCount,
                        isFavorite = false
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getTvShows(sort: String): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvShow>>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvShows(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean {
                return data == null || data.isEmpty()
            }
//
            override fun createCall(): LiveData<ApiResponse<List<TvShow>>> {
                return remoteDataSource.getTvShowList()
            }

            override fun saveCallResult(data: List<TvShow>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                        id = response.id,
                        backdropPath = response.backdropPath,
                        overview = response.overview,
                        popularity = response.popularity,
                        posterPath = response.posterPath,
                        firstAirDate = response.firstAirDate,
                        name = response.name,
                        voteCount = response.voteCount,
                        isFavorite = false
                    )
                    tvShowList.add(tvShow)
                }
                localDataSource.insertTvShows(tvShowList)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShows(), config).build()
    }

    override fun getMovie(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, Movie>(appExecutors) {
            override fun loadFromDb(): LiveData<MovieEntity> {
                return localDataSource.getDetailMovie(movieId)
            }

            override fun shouldFetch(data: MovieEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<Movie>> {
                return remoteDataSource.getMovieDetail(movieId.toString())
            }

            override fun saveCallResult(data: Movie) {
                val movie = MovieEntity(
                    id = data.id,
                    backdropPath = data.backdropPath,
                    overview = data.overview,
                    popularity = data.popularity,
                    posterPath = data.posterPath,
                    releaseDate = data.releaseDate,
                    title = data.title,
                    voteCount = data.voteCount,
                    isFavorite = false
                )
                localDataSource.updateMovie(movie, false)
            }
        }.asLiveData()
    }

    override fun getTvShow(tvShowId: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvShow>(appExecutors) {
            override fun loadFromDb(): LiveData<TvShowEntity> {
                return localDataSource.getDetailTvShow(tvShowId)
            }

            override fun shouldFetch(data: TvShowEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<TvShow>> {
                return remoteDataSource.getTvShowDetail(tvShowId.toString())
            }

            override fun saveCallResult(data: TvShow) {
                val tvShow = TvShowEntity(
                    id = data.id,
                    backdropPath = data.backdropPath,
                    overview = data.overview,
                    popularity = data.popularity,
                    posterPath = data.posterPath,
                    firstAirDate = data.firstAirDate,
                    name = data.name,
                    voteCount = data.voteCount,
                    isFavorite = false
                )
                localDataSource.updateTvShow(tvShow, false)
            }
        }.asLiveData()
    }

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(movie, state)
        }
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteTvShow(tvShow, state)
        }
    }

    companion object {
        @Volatile
        private var instance: MovieCatalogueRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieCatalogueRepository =
            instance ?: synchronized(this) {
                instance ?: MovieCatalogueRepository(
                    remoteDataSource,
                    localDataSource,
                    appExecutors
                )
            }
    }
}
package com.xridwan.moviecatalogue3.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.xridwan.moviecatalogue3.data.source.local.LocalDataSource
import com.xridwan.moviecatalogue3.data.source.local.entity.MovieEntity
import com.xridwan.moviecatalogue3.data.source.local.entity.TvShowEntity
import com.xridwan.moviecatalogue3.data.source.remote.RemoteDataSource
import com.xridwan.moviecatalogue3.utils.AppExecutors
import com.xridwan.moviecatalogue3.utils.DataDummy
import com.xridwan.moviecatalogue3.utils.LiveDataTestUtil
import com.xridwan.moviecatalogue3.utils.PagedListUtil
import com.xridwan.moviecatalogue3.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class MovieCatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val fakeMovieCatalogueRepository =
        FakeMovieCatalogueRepository(remote, local, appExecutors)

    private val movieResponse = DataDummy.generateRemoteMovie()
    private val movieId = movieResponse[0].id
    private val movie = DataDummy.generateRemoteMovie()[0]

    private val tvShowResponse = DataDummy.generateRemoteTvShow()
    private val tvShowId = tvShowResponse[0].id
    private val tvShow = DataDummy.generateRemoteTvShow()[0]

    @Test
    fun getMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovies("HIGHEST")).thenReturn(dataSourceFactory)
        fakeMovieCatalogueRepository.getMovies("HIGHEST")

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovie()))
        verify(local).getAllMovies("HIGHEST")
        assertNotNull(movieEntities)
        assertEquals(movieResponse.size, movieEntities.data?.size)
    }

    @Test
    fun getMovie() {
        val dummyDetail = MutableLiveData<MovieEntity>()
        dummyDetail.value = DataDummy.generateDummyMovie()[0]
        `when`(local.getDetailMovie(movieId)).thenReturn(dummyDetail)

        val movieDetailEntity =
            LiveDataTestUtil.getValue(fakeMovieCatalogueRepository.getMovie(movieId))
        verify(local).getDetailMovie(movieId)
        assertNotNull(movieDetailEntity)
        assertEquals(movie.id, movieDetailEntity.data?.id)
    }

    @Test
    fun getFavoriteMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        fakeMovieCatalogueRepository.getFavoriteMovie()

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovie()))
        verify(local).getFavoriteMovies()
        assertNotNull(movieEntities)
        assertEquals(movieResponse.size, movieEntities.data?.size)
    }

    @Test
    fun getTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getAllTvShows("HIGHEST")).thenReturn(dataSourceFactory)
        fakeMovieCatalogueRepository.getTvShows("HIGHEST")

        val tvShowEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShow()))
        verify(local).getAllTvShows("HIGHEST")
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size, tvShowEntities.data?.size)
    }

    @Test
    fun getTvShow() {
        val dummyDetail = MutableLiveData<TvShowEntity>()
        dummyDetail.value = DataDummy.generateDummyTvShow()[0]
        `when`(local.getDetailTvShow(tvShowId)).thenReturn(dummyDetail)

        val tvShowDetailEntity =
            LiveDataTestUtil.getValue(fakeMovieCatalogueRepository.getTvShow(tvShowId))
        verify(local).getDetailTvShow(tvShowId)
        assertNotNull(tvShowDetailEntity)
        assertEquals(tvShow.id, tvShowDetailEntity.data?.id)
    }

    @Test
    fun getFavoriteTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavoriteTvShows()).thenReturn(dataSourceFactory)
        fakeMovieCatalogueRepository.getFavoriteTvShow()

        val tvShowEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShow()))
        verify(local).getFavoriteTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size, tvShowEntities.data?.size)
    }
}
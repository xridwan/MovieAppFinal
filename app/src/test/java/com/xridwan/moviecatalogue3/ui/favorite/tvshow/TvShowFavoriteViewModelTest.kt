package com.xridwan.moviecatalogue3.ui.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.xridwan.moviecatalogue3.data.MovieCatalogueRepository
import com.xridwan.moviecatalogue3.data.source.local.entity.TvShowEntity
import com.xridwan.moviecatalogue3.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowFavoriteViewModelTest {

    private lateinit var viewModel: TvShowFavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = TvShowFavoriteViewModel(movieCatalogueRepository)
    }

    @Test
    fun getFavTvShows() {
        val dummyTvShow = pagedList
        `when`(dummyTvShow.size).thenReturn(3)
        val tvShows = MutableLiveData<PagedList<TvShowEntity>>()
        tvShows.value = dummyTvShow

        `when`(movieCatalogueRepository.getFavoriteTvShow()).thenReturn(tvShows)
        val tvShow = viewModel.getFavoriteTvShows().value
        verify(movieCatalogueRepository).getFavoriteTvShow()
        assertNotNull(tvShow)
        assertEquals(3, tvShow?.size)

        viewModel.getFavoriteTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }

    @Test
    fun setFavMovie() {
        viewModel.setFavoriteTvShow(DataDummy.generateDummyTvShow()[0])
        verify(movieCatalogueRepository).setFavoriteTvShow(DataDummy.generateDummyTvShow()[0], true)
        verifyNoMoreInteractions(movieCatalogueRepository)
    }
}
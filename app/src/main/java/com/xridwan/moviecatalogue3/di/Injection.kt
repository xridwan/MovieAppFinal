package com.xridwan.moviecatalogue3.di

import android.content.Context
import com.xridwan.moviecatalogue3.data.MovieCatalogueRepository
import com.xridwan.moviecatalogue3.data.source.local.LocalDataSource
import com.xridwan.moviecatalogue3.data.source.local.room.ContentDatabase
import com.xridwan.moviecatalogue3.data.source.remote.RemoteDataSource
import com.xridwan.moviecatalogue3.utils.AppExecutors
//
object Injection {
    fun provideRepository(context: Context): MovieCatalogueRepository {
        val database = ContentDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.contentDao())
        val appExecutors = AppExecutors()

        return MovieCatalogueRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}
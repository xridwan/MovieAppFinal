package com.xridwan.moviecatalogue3.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @field:SerializedName("results")
    val results: List<Movie>,
)

data class Movie(

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("popularity")
    val popularity: Double? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("vote_count")
    val voteCount: Int? = null
)

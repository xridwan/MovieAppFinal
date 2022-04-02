package com.xridwan.moviecatalogue3.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(

    @field:SerializedName("results")
    val results: List<TvShow>,
)

data class TvShow(

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

    @field:SerializedName("first_air_date")
    val firstAirDate: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("vote_count")
    val voteCount: Int? = null
)

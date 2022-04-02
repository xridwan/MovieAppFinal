package com.xridwan.moviecatalogue3.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val HIGHEST = "Highest"
    const val LOWEST = "Lowest"
    const val MOVIE_ENTITIES = "movie_entities"
    const val TV_SHOW_ENTITIES = "tv_show_entities"

    fun getSortedQuery(filter: String, table_name: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM $table_name ")
        when (filter) {
            HIGHEST -> simpleQuery.append("ORDER BY voteCount DESC")
            LOWEST -> simpleQuery.append("ORDER BY voteCount ASC")
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}
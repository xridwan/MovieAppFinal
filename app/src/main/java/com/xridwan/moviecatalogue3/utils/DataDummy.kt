package com.xridwan.moviecatalogue3.utils

import com.xridwan.moviecatalogue3.data.source.local.entity.MovieEntity
import com.xridwan.moviecatalogue3.data.source.local.entity.TvShowEntity
import com.xridwan.moviecatalogue3.data.source.remote.response.Movie
import com.xridwan.moviecatalogue3.data.source.remote.response.TvShow

object DataDummy {

    fun generateDummyMovie(): List<MovieEntity> {
        return listOf(
            MovieEntity(
                id = 1,
                title = "Spider-Man: No Way Home",
                posterPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                backdropPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                releaseDate = "2021-12-15",
                popularity = 6990.201,
                voteCount = 8415,
                isFavorite = false
            ),
            MovieEntity(
                id = 2,
                title = "Encanto",
                posterPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4j0PNHkMr5ax3IA8tjtxcmPU3QT.jpg",
                backdropPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4j0PNHkMr5ax3IA8tjtxcmPU3QT.jpg",
                overview = "The tale of an extraordinary family, the Madrigals, who live hidden in the mountains of Colombia, in a magical house, in a vibrant town, in a wondrous, charmed place called an Encanto. The magic of the Encanto has blessed every child in the family with a unique gift from super strength to the power to heal—every child except one, Mirabel. But when she discovers that the magic surrounding the Encanto is in danger, Mirabel decides that she, the only ordinary Madrigal, might just be her exceptional family's last hope.",
                releaseDate = "2021-11-24",
                popularity = 3561.945,
                voteCount = 4723,
                isFavorite = false
            ),
            MovieEntity(
                id = 3,
                title = "The King's Man",
                posterPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/nj5HmHRZsrYQEYYXyAusFv35erP.jpg",
                backdropPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/nj5HmHRZsrYQEYYXyAusFv35erP.jpg",
                overview = "As a collection of history's worst tyrants and criminal masterminds gather to plot a war to wipe out millions, one man must race against time to stop them.",
                releaseDate = "2021-12-22",
                popularity = 3095.477,
                voteCount = 1442,
                isFavorite = false
            )
        )
    }

    fun generateDummyTvShow(): List<TvShowEntity> {
        return listOf(
            TvShowEntity(
                id = 1,
                name = "Pasión de gavilanes",
                posterPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lWlsZIsrGVWHtBeoOeLxIKDd9uy.jpg",
                backdropPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lWlsZIsrGVWHtBeoOeLxIKDd9uy.jpg",
                overview = "The Reyes-Elizondo's idyllic lives are shattered by a murder charge against Eric and León.",
                firstAirDate = "2003-10-21",
                popularity = 2941.888,
                voteCount = 1703,
                isFavorite = false
            ),
            TvShowEntity(
                id = 2,
                name = "Euphoria",
                posterPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/288q0JefZUuTfqUG2qzPTCZYuAL.jpg",
                backdropPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/288q0JefZUuTfqUG2qzPTCZYuAL.jpg",
                overview = "A group of high school students navigate love and friendships in a world of drugs, sex, trauma, and social media.",
                firstAirDate = "2019-06-16",
                popularity = 5339.495,
                voteCount = 7012,
                isFavorite = false
            ),
            TvShowEntity(
                id = 3,
                name = "All of Us Are Dead",
                posterPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/s1ZL8Jwc72QUTjdvniBmmf0VBdL.jpg",
                backdropPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/s1ZL8Jwc72QUTjdvniBmmf0VBdL.jpg",
                overview = "A high school becomes ground zero for a zombie virus outbreak. Trapped students must fight their way out — or turn into one of the rabid infected.",
                firstAirDate = "2022-01-28",
                popularity = 2474.443,
                voteCount = 1464,
                isFavorite = false
            )
        )
    }

    fun generateRemoteMovie(): List<Movie> {
        return listOf(
            Movie(
                id = 1,
                title = "Spider-Man: No Way Home",
                posterPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                backdropPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                releaseDate = "2021-12-15",
                popularity = 6990.201,
                voteCount = 8415
            ),
            Movie(
                id = 2,
                title = "Encanto",
                posterPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4j0PNHkMr5ax3IA8tjtxcmPU3QT.jpg",
                backdropPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4j0PNHkMr5ax3IA8tjtxcmPU3QT.jpg",
                overview = "The tale of an extraordinary family, the Madrigals, who live hidden in the mountains of Colombia, in a magical house, in a vibrant town, in a wondrous, charmed place called an Encanto. The magic of the Encanto has blessed every child in the family with a unique gift from super strength to the power to heal—every child except one, Mirabel. But when she discovers that the magic surrounding the Encanto is in danger, Mirabel decides that she, the only ordinary Madrigal, might just be her exceptional family's last hope.",
                releaseDate = "2021-11-24",
                popularity = 3561.945,
                voteCount = 4723
            ),
            Movie(
                id = 3,
                title = "The King's Man",
                posterPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/nj5HmHRZsrYQEYYXyAusFv35erP.jpg",
                backdropPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/nj5HmHRZsrYQEYYXyAusFv35erP.jpg",
                overview = "As a collection of history's worst tyrants and criminal masterminds gather to plot a war to wipe out millions, one man must race against time to stop them.",
                releaseDate = "2021-12-22",
                popularity = 3095.477,
                voteCount = 1442
            )
        )
    }

    fun generateRemoteTvShow(): List<TvShow> {
        return listOf(
            TvShow(
                id = 1,
                name = "Euphoria",
                posterPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/288q0JefZUuTfqUG2qzPTCZYuAL.jpg",
                backdropPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/288q0JefZUuTfqUG2qzPTCZYuAL.jpg",
                overview = "A group of high school students navigate love and friendships in a world of drugs, sex, trauma, and social media.",
                firstAirDate = "2019-06-16",
                popularity = 5339.495,
                voteCount = 7012
            ),
            TvShow(
                id = 2,
                name = "Pasión de gavilanes",
                posterPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lWlsZIsrGVWHtBeoOeLxIKDd9uy.jpg",
                backdropPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lWlsZIsrGVWHtBeoOeLxIKDd9uy.jpg",
                overview = "The Reyes-Elizondo's idyllic lives are shattered by a murder charge against Eric and León.",
                firstAirDate = "2003-10-21",
                popularity = 2941.888,
                voteCount = 1703
            ),
            TvShow(
                id = 3,
                name = "All of Us Are Dead",
                posterPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/s1ZL8Jwc72QUTjdvniBmmf0VBdL.jpg",
                backdropPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/s1ZL8Jwc72QUTjdvniBmmf0VBdL.jpg",
                overview = "A high school becomes ground zero for a zombie virus outbreak. Trapped students must fight their way out — or turn into one of the rabid infected.",
                firstAirDate = "2022-01-28",
                popularity = 2474.443,
                voteCount = 1464
            )
        )
    }
}
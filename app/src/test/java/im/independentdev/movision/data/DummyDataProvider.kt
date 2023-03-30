package im.independentdev.movision.data

import im.independentdev.movision.data.model.api.CreditsResponse
import im.independentdev.movision.data.model.api.MovieDetailResponse
import im.independentdev.movision.data.model.api.MovieListResponse
import im.independentdev.movision.data.model.base.Cast
import im.independentdev.movision.data.model.base.Movie
import im.independentdev.movision.data.model.ui.CastViewItem
import im.independentdev.movision.data.model.ui.MovieDetailViewItem
import im.independentdev.movision.data.model.ui.MovieListViewItem
import im.independentdev.movision.data.model.ui.MovieViewItem
import im.independentdev.movision.domain.DomainConstants

object DummyDataProvider {
	
	fun getEmptyMovieListViewItem(type: String = ""): MovieListViewItem {
		return MovieListViewItem(
			page = 0, totalPage = 0, movies = arrayListOf(
				MovieViewItem(
					0, "imagePath", type
				)
			)
		)
	}
	
	fun getEmptyMovieDetailViewItem(movieId: Int): MovieDetailViewItem {
		return MovieDetailViewItem(
			id = movieId,
			backdropPath = "test backdropPath",
			genres = emptyList(),
			title = "test title",
			overview = "test overview",
			popularity = 0.toDouble(),
			imagePath = "test imagePath",
			releaseDate = "test release data",
			runtime = "test runtime",
			status = "test status",
			voteAverage = 0.toFloat()
		)
	}
	
	fun getMockCreditList(): List<CastViewItem> {
		return arrayListOf(
			CastViewItem("name1 lastname1", "test character 1", "${DomainConstants.PROFILE_URL_BASE}profile_path_1"),
			CastViewItem("name2 lastname2", "test character 2", "${DomainConstants.PROFILE_URL_BASE}profile_path_2")
		)
	}
	
	fun getPopularMovieList() = MovieListViewItem(page = 0, totalPage = 0, movies = listOf(MovieViewItem(1, "popularPath", "popularTitle")))
	fun getUpcomingMovieList() = MovieListViewItem(page = 0, totalPage = 0, movies = listOf(MovieViewItem(1, "upcomingPath", "upcomingTitle")))
	fun getNowPlayingMovieList() = MovieListViewItem(page = 0, totalPage = 0, movies = listOf(MovieViewItem(1, "nowPlayingPath", "nowPlayingTitle")))
	
	
	/*
	Response Data
	 */
	
	fun getMockedMovieResponse(title: String = "", page: Int = 1): MovieListResponse {
		return MovieListResponse(
			page, 100, arrayListOf(
				Movie(
					popularity = 0.toDouble(),
					vote_count = 0.toDouble(),
					video = false,
					posterPath = "",
					id = 10,
					adult = false,
					backdropPath = "",
					originalLanguage = "",
					originalTitle = "",
					genreIds = ArrayList<Int>(),
					title = title,
					voteAverage = 0.toDouble(),
					overview = "$title overview",
					releaseDate = ""
				)
			)
		)
	}
	
	fun getMockedCreditsResponse(): CreditsResponse {
		
		return CreditsResponse(
			arrayListOf(
				Cast(
					castId = 0,
					character = "test character 1",
					gender = 0,
					name = "name1 lastname1",
					creditId = "1",
					profilePath = "profile_path_1",
					id = 1,
					order = 1
				),
				Cast(
					castId = 0,
					character = "test character 2",
					gender = 0,
					name = "name2 lastname2",
					creditId = "2",
					profilePath = "profile_path_2",
					id = 2,
					order = 2
				)
			), 0
		)
	}
	
	fun getMockedMovieDetailResponse(title: String = ""): MovieDetailResponse {
		return MovieDetailResponse(
			originalLanguage = "testOriginalLanguage",
			imdbId = "testImdbId",
			video = false,
			title = title,
			backdropPath = "testBackdropPath",
			revenue = 0,
			genres = emptyList(),
			popularity = 0.0,
			id = 0,
			voteCount = 0,
			originalTitle = "testOriginalTitle",
			overview = "testOverView",
			runtime = 0,
			posterPath = "testPosterPath",
			releaseDate = "testReleaseDate",
			voteAverage = 0.0,
			status = "testStatus"
		)
	}
}

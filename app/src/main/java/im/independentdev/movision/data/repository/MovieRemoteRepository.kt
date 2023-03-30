package im.independentdev.movision.data.repository

import im.independentdev.movision.data.api.MovieAPI
import im.independentdev.movision.data.model.api.CreditsResponse
import im.independentdev.movision.data.model.api.MovieDetailResponse
import im.independentdev.movision.data.model.api.MovieListResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRemoteRepository @Inject constructor(private val api: MovieAPI) : IMovieRepository {
	
	override fun getPopularMovies(page: Int): Observable<MovieListResponse> {
		return api.getPopular(page)
	}
	
	override fun getUpcomingMovies(page: Int): Observable<MovieListResponse> {
		return api.getUpcoming(page)
	}
	
	override fun getNowPlayingMovies(page: Int): Observable<MovieListResponse> {
		return api.getNowPlayingMovies(page)
	}
	
	override fun getMovieDetail(movieId: Int): Observable<MovieDetailResponse> {
		return api.getMovieDetail(movieId)
	}
	
	override fun getMovieCredits(movieId: Int): Observable<CreditsResponse> {
		return api.getMovieCredits(movieId)
	}
	
}
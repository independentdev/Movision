package im.independentdev.movision.data.repository

import im.independentdev.movision.data.api.MovieAPI
import im.independentdev.movision.data.model.api.CreditsResponse
import im.independentdev.movision.data.model.api.MovieDetailResponse
import im.independentdev.movision.data.model.api.MovieListResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Implementation for [IMovieRepository]
 *
 * @param api Retrofit interface [MovieAPI]
 */
@Singleton
class MovieRemoteRepository @Inject constructor(private val api: MovieAPI) : IMovieRepository {
	
	/**
	 * Fetches Popular Movies from remote API
	 *
	 * @param page page number
	 * @return Observable<MovieListResponse> response as [Observable]
	 */
	override fun getPopularMovies(page: Int): Observable<MovieListResponse> {
		return api.getPopular(page)
	}
	
	/**
	 * Fetches Upcoming Movies from remote API
	 *
	 * @param page page number
	 * @return Observable<MovieListResponse> response as [Observable]
	 */
	override fun getUpcomingMovies(page: Int): Observable<MovieListResponse> {
		return api.getUpcoming(page)
	}
	
	/**
	 * Fetches Now Playing Movies from remote API
	 *
	 * @param page page number
	 * @return Observable<MovieListResponse> response as [Observable]
	 */
	override fun getNowPlayingMovies(page: Int): Observable<MovieListResponse> {
		return api.getNowPlayingMovies(page)
	}
	
	/**
	 * Fetches Movie Detail for given movie id from remote API
	 *
	 * @param movieId page number
	 * @return Observable<MovieDetailResponse> response as [Observable]
	 */
	override fun getMovieDetail(movieId: Int): Observable<MovieDetailResponse> {
		return api.getMovieDetail(movieId)
	}
	
	/**
	 * Fetches Movie Credits for given movie id from remote API
	 *
	 * @param movieId page number
	 * @return Observable<CreditsResponse> response as [Observable]
	 */
	override fun getMovieCredits(movieId: Int): Observable<CreditsResponse> {
		return api.getMovieCredits(movieId)
	}
	
}
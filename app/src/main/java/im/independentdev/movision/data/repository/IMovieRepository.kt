package im.independentdev.movision.data.repository

import im.independentdev.movision.data.model.api.CreditsResponse
import im.independentdev.movision.data.model.api.MovieDetailResponse
import im.independentdev.movision.data.model.api.MovieListResponse
import io.reactivex.rxjava3.core.Observable

/**
 * A contract for Movie data repository. It holds list of all available function which a class needs to implement.
 */
interface IMovieRepository {
	
	fun getPopularMovies(page: Int): Observable<MovieListResponse>
	
	fun getUpcomingMovies(page: Int): Observable<MovieListResponse>
	
	fun getNowPlayingMovies(page: Int): Observable<MovieListResponse>
	
	fun getMovieDetail(movieId: Int): Observable<MovieDetailResponse>
	
	fun getMovieCredits(movieId: Int): Observable<CreditsResponse>
}
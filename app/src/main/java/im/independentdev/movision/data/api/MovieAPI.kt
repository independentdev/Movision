package im.independentdev.movision.data.api

import im.independentdev.movision.data.model.api.CreditsResponse
import im.independentdev.movision.data.model.api.MovieDetailResponse
import im.independentdev.movision.data.model.api.MovieListResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Retrofit interface
 */
interface MovieAPI {
	
	@GET("movie/popular")
	fun getPopular(@Query("page") page: Int): Observable<MovieListResponse>
	
	@GET("movie/upcoming")
	fun getUpcoming(@Query("page") page: Int): Observable<MovieListResponse>
	
	@GET("movie/now_playing")
	fun getNowPlayingMovies(@Query("page") page: Int): Observable<MovieListResponse>
	
	@GET("movie/{movie_id}")
	fun getMovieDetail(@Path("movie_id") movieId: Int): Observable<MovieDetailResponse>
	
	@GET("movie/{movie_id}/credits")
	fun getMovieCredits(@Path("movie_id") movieId: Int): Observable<CreditsResponse>
}
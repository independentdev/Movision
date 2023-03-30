package im.independentdev.movision.domain.usecase

import im.independentdev.movision.data.model.base.MovieListType
import im.independentdev.movision.data.model.base.MovieListType.*
import im.independentdev.movision.data.model.ui.MovieListViewItem
import im.independentdev.movision.data.repository.MovieRemoteRepository
import im.independentdev.movision.domain.mapper.MovieDataMapper
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
	private val repository: MovieRemoteRepository,
	private val itemMapper: MovieDataMapper
) {
	fun execute(movieType: MovieListType, page: Int = 1): Observable<MovieListViewItem> {
		return when (movieType) {
			NOW_PLAYING -> repository.getNowPlayingMovies(page).map { itemMapper.mapFrom(it) }
			POPULAR -> repository.getPopularMovies(page).map { itemMapper.mapFrom(it) }
			UPCOMING -> repository.getUpcomingMovies(page).map { itemMapper.mapFrom(it) }
		}
	}
}
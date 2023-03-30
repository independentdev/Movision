package im.independentdev.movision.domain.usecase

import im.independentdev.movision.data.model.ui.CastViewItem
import im.independentdev.movision.data.model.ui.MovieDetailViewItem
import im.independentdev.movision.data.repository.MovieRemoteRepository
import im.independentdev.movision.domain.mapper.CastItemMapper
import im.independentdev.movision.domain.mapper.MovieDetailMapper
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDetailUseCase @Inject constructor(
	private val repository: MovieRemoteRepository,
	private val itemMapper: MovieDetailMapper,
	private val castMapper: CastItemMapper
	) {
	
	fun getMovieDetail(movieId: Int): Observable<MovieDetailViewItem> {
		return repository.getMovieDetail(movieId = movieId).map { itemMapper.mapFrom(it) }
	}
	
	fun getMovieCredits(movieId: Int): Observable<List<CastViewItem>> {
		return repository.getMovieCredits(movieId = movieId).map { castMapper.mapFrom(it).orEmpty() }
	}
	
}

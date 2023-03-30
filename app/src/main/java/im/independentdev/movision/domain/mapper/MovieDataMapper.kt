package im.independentdev.movision.domain.mapper

import im.independentdev.movision.core.orZero
import im.independentdev.movision.data.model.api.MovieListResponse
import im.independentdev.movision.data.model.ui.MovieListViewItem
import im.independentdev.movision.data.model.ui.MovieViewItem
import im.independentdev.movision.domain.DomainConstants
import javax.inject.Inject
import javax.inject.Singleton


/**
 * A mapper to convert API model of movie list [MovieListResponse] to its UI model [MovieListViewItem]
 */
@Singleton
class MovieDataMapper @Inject constructor() : Mapper<MovieListResponse, MovieListViewItem?> {
	override fun mapFrom(item: MovieListResponse): MovieListViewItem {
		return MovieListViewItem(
			page = item.page.orZero(),
			totalPage = item.totalPages.orZero(),
			movies = item.results?.map { movie ->
				MovieViewItem(
					id = movie.id.orZero(),
					imagePath = getPosterPath(movie.posterPath),
					title = movie.title.orEmpty()
				)
			}.orEmpty()
		)
	}
	
	/**
	 * Add base url to make full address
	 */
	private fun getPosterPath(path: String?) : String {
		return if (path.orEmpty().isNotEmpty()) "${DomainConstants.POSTER_URL_BASE}$path" else ""
	}
}
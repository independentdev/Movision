package im.independentdev.movision.domain.mapper

import im.independentdev.movision.core.orZero
import im.independentdev.movision.core.simpleDateConvert
import im.independentdev.movision.data.model.api.MovieDetailResponse
import im.independentdev.movision.data.model.ui.MovieDetailViewItem
import im.independentdev.movision.domain.DomainConstants
import javax.inject.Inject
import javax.inject.Singleton


/**
 * A mapper to convert API model of movie detail [MovieDetailResponse] to its UI model [MovieDetailViewItem]
 */
@Singleton
class MovieDetailMapper @Inject constructor() : Mapper<MovieDetailResponse, MovieDetailViewItem> {
	override fun mapFrom(item: MovieDetailResponse): MovieDetailViewItem {
		return MovieDetailViewItem(
			id = item.id.orZero(),
			backdropPath = getBackdropPath(item.backdropPath),
			genres = item.genres.orEmpty(),
			title = item.title.orEmpty(),
			overview = item.overview.orEmpty(),
			popularity = item.popularity.orZero(),
			imagePath = getImagePath(item.posterPath),
			releaseDate = item.releaseDate.simpleDateConvert("dd MMM yyyy"),
			runtime = "${item.runtime} min",
			status = item.status.orEmpty(),
			voteAverage = item.voteAverage?.toFloat().orZero() / 2
		)
	}
	
	/**
	 * Add base url to make full address
	 */
	private fun getImagePath(path: String?) : String {
		return if (path.orEmpty().isNotEmpty()) "${DomainConstants.POSTER_URL_BASE}$path" else ""
	}
	
	/**
	 * Add base url to make full address
	 */
	private fun getBackdropPath(path: String?) : String {
		return if (path.orEmpty().isNotEmpty()) "${DomainConstants.BACKDROP_URL_BASE}$path" else ""
	}
}
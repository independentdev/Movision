package im.independentdev.movision.data.model.ui

import im.independentdev.movision.data.model.base.Genre

data class MovieDetailViewItem(
	val id: Int,
	val backdropPath: String,
	val genres: List<Genre>,
	val title: String,
	val overview: String,
	val popularity: Double,
	val imagePath: String,
	val releaseDate: String,
	val runtime: String,
	val status: String,
	val voteAverage: Float
) {
	fun getSubTitle() = "$releaseDate • $runtime"
}
package im.independentdev.movision.data.model.api

import com.google.gson.annotations.SerializedName
import im.independentdev.movision.data.model.base.Genre

data class MovieDetailResponse(
	@SerializedName("original_language") val originalLanguage: String?,
	@SerializedName("imdb_id") val imdbId: String?,
	@SerializedName("video") val video: Boolean?,
	@SerializedName("title") val title: String?,
	@SerializedName("backdrop_path") val backdropPath: String?,
	@SerializedName("revenue") val revenue: Int?,
	@SerializedName("genres") val genres: List<Genre>?,
	@SerializedName("popularity") val popularity: Double?,
	@SerializedName("id") val id: Int?,
	@SerializedName("vote_count") val voteCount: Int?,
	@SerializedName("overview") val overview: String?,
	@SerializedName("original_title") val originalTitle: String?,
	@SerializedName("runtime") val runtime: Int?,
	@SerializedName("poster_path") val posterPath: String?,
	@SerializedName("release_date") val releaseDate: String?,
	@SerializedName("vote_average") val voteAverage: Double?,
	@SerializedName("status") val status: String?
)
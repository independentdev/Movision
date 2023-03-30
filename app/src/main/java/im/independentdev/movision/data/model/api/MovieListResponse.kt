package im.independentdev.movision.data.model.api

import com.google.gson.annotations.SerializedName
import im.independentdev.movision.data.model.base.Movie

data class MovieListResponse(
	@SerializedName("page") val page: Int?,
	@SerializedName("total_pages") val totalPages: Int?,
	@SerializedName("results") val results: ArrayList<Movie>?
)
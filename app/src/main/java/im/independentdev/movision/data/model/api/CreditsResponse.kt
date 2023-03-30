package im.independentdev.movision.data.model.api

import com.google.gson.annotations.SerializedName
import im.independentdev.movision.data.model.base.Cast

data class CreditsResponse(
	@SerializedName("cast") val cast: List<Cast>?,
	@SerializedName("id") val id: Int?
)
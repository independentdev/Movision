package im.independentdev.movision.data.model.base

import com.google.gson.annotations.SerializedName

data class Genre(
	@SerializedName("name") val name: String?,
	@SerializedName("id") val id: Int?
)
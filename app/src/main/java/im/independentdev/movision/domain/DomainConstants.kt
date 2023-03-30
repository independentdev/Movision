package im.independentdev.movision.domain

class DomainConstants {
	companion object {
		private const val IMAGE_URL_BASE = "https://image.tmdb.org/t/p/"
		const val POSTER_URL_BASE = "${IMAGE_URL_BASE}w600_and_h900_bestv2"
		const val BACKDROP_URL_BASE = "${IMAGE_URL_BASE}w533_and_h300_bestv2"
		const val PROFILE_URL_BASE = "${IMAGE_URL_BASE}w600_and_h900_bestv2"
	}
}
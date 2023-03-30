package im.independentdev.movision.domain.mapper

import im.independentdev.movision.data.model.api.CreditsResponse
import im.independentdev.movision.data.model.ui.CastViewItem
import im.independentdev.movision.domain.DomainConstants
import org.jetbrains.annotations.TestOnly
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CastItemMapper @Inject constructor() : Mapper<CreditsResponse, List<CastViewItem>?> {
	override fun mapFrom(item: CreditsResponse): List<CastViewItem>? {
		return item.cast?.map { cast ->
			CastViewItem(
				name = cast.name.orEmpty(),
				character = getCharacter(cast.character?:""),
				profilePath = getProfilePath(cast.profilePath)
			)
		}
	}
	
	// Add parentheses around character name
	private fun getCharacter(character: String) : String {
		return if (character.isNotEmpty()) "(${character})" else "-"
	}
	
	// Add base url to make full address
	private fun getProfilePath(path: String?) : String {
		return if (path.orEmpty().isNotEmpty()) "${DomainConstants.PROFILE_URL_BASE}$path" else ""
	}
}
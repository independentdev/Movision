package im.independentdev.movision.data.model.ui

import org.jetbrains.annotations.TestOnly

data class CastViewItem(
	val name: String,
	val character: String,
	val profilePath: String
) {
	
	override fun equals(other: Any?): Boolean {
		var isEqual = false
		(other as CastViewItem?)?.let {
			isEqual = this.name == it.name && this.character.clearParentheses() == it.character.clearParentheses() && this.profilePath == it.profilePath
		}
		
		return isEqual
	}
	
	// clear parentheses if added by mapper
	@TestOnly
	private fun String.clearParentheses() : String = this.replace("(", "").replace(")", "").trim()
}
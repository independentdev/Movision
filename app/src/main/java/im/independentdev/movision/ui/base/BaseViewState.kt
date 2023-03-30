package im.independentdev.movision.ui.base

data class BaseViewState(
	val showLoading: Boolean = false,
	val showContent: Boolean = false,
	val showError: Boolean = false,
	val showEmpty: Boolean = false,
	
	val throwable: Throwable? = null
)

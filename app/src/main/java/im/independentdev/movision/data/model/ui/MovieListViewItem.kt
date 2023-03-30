package im.independentdev.movision.data.model.ui

data class MovieListViewItem(
	val page: Int,
	val totalPage: Int,
	val movies: List<MovieViewItem>
) {
	override fun equals(other: Any?): Boolean {
		var isEqual = false
		(other as MovieListViewItem?)?.let {
			 isEqual = this.page == it.page && this.totalPage == it.totalPage && this.movies.size == it.movies.size
		}
		
		return isEqual
	}
}
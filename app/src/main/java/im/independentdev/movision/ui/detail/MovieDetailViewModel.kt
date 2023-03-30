package im.independentdev.movision.ui.detail

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import im.independentdev.movision.data.model.ui.CastViewItem
import im.independentdev.movision.data.model.ui.MovieDetailViewItem
import im.independentdev.movision.domain.usecase.MovieDetailUseCase
import im.independentdev.movision.ui.base.BaseViewModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val movieDetailUseCase: MovieDetailUseCase) : BaseViewModel() {
	
	/** LiveData for ViewState **/
	private val _liveDataViewState = MutableLiveData<MovieDetailFragmentViewState>()
	val liveDataViewState: LiveData<MovieDetailFragmentViewState> = _liveDataViewState
	
	private var movieId = 0
	
	override fun handleIntent(extras: Bundle) {
		val args = MovieDetailFragmentArgs.fromBundle(extras)
		this.movieId = args.movieId
	}
	
	fun getMovieData() {
		Observable.zip(
			movieDetailUseCase.getMovieDetail(movieId = movieId),
			movieDetailUseCase.getMovieCredits(movieId = movieId)
		) { movieDetail, credits ->
			MovieDetailFragmentViewState(movieDetailViewItem = movieDetail,
				castViewItems = credits)
		}.sendRequest {
			_liveDataViewState.value = it
		}
	}
	
	data class MovieDetailFragmentViewState(
		private val movieDetailViewItem: MovieDetailViewItem,
		private val castViewItems: List<CastViewItem>
	) {
		fun getMovieDetailItems(): MovieDetailViewItem = movieDetailViewItem
		fun getCredits(): List<CastViewItem> = castViewItems
		
	}
}
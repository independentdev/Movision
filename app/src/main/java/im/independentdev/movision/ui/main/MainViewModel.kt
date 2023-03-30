package im.independentdev.movision.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import im.independentdev.movision.data.model.base.MovieListType
import im.independentdev.movision.data.model.ui.MovieListViewItem
import im.independentdev.movision.domain.usecase.GetMoviesUseCase
import im.independentdev.movision.ui.base.BaseViewModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
	private val getMoviesUseCase: GetMoviesUseCase
) : BaseViewModel() {
	
	/** LiveData for ViewState **/
	private val _liveMoviesViewState = MutableLiveData<MoviesViewState>()
	val liveMoviesViewState: LiveData<MoviesViewState> = _liveMoviesViewState
	
	fun getMovies() {
		Observable.zip(
			getMoviesUseCase.execute(MovieListType.POPULAR),
			getMoviesUseCase.execute(MovieListType.NOW_PLAYING),
			getMoviesUseCase.execute(MovieListType.UPCOMING)
		) { popular, nowPlaying, upcoming ->
			MoviesViewState(popularMovies = popular,
				nowPlayingMovies = nowPlaying,
				upcomingMovies = upcoming)
		}.sendRequest {
			_liveMoviesViewState.value = it
		}
	}
	
	data class MoviesViewState(
		val popularMovies: MovieListViewItem,
		val nowPlayingMovies: MovieListViewItem,
		val upcomingMovies: MovieListViewItem
	)
	
}
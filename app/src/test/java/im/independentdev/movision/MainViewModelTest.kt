package im.independentdev.movision

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import im.independentdev.movision.data.model.base.MovieListType
import im.independentdev.movision.domain.usecase.GetMoviesUseCase
import im.independentdev.movision.core.RxImmediateSchedulerRule
import im.independentdev.movision.data.DummyDataProvider
import im.independentdev.movision.ui.main.MainViewModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.rxjava3.core.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {
	
	@Rule
	@JvmField
	val instantExecutorRule = InstantTaskExecutorRule()
	
	@MockK
	lateinit var getMoviesUseCase: GetMoviesUseCase
	
	@Rule
	@JvmField
	var testSchedulerRule = RxImmediateSchedulerRule()
	
	private lateinit var mainViewModel: MainViewModel
	
	@Before
	fun setUp() {
		MockKAnnotations.init(this)
		mainViewModel = MainViewModel(getMoviesUseCase)
	}
	
	@Test
	fun `given successful fetch, when getMovies called, then viewState updates successfully `() {
		
		// Given
		val viewStateObserver: Observer<MainViewModel.MoviesViewState> = mockk(relaxUnitFun = true)
		mainViewModel.liveMoviesViewState.observeForever(viewStateObserver)
		
		every { getMoviesUseCase.execute(MovieListType.POPULAR) } returns
				Observable.just(DummyDataProvider.getPopularMovieList())
		
		every { getMoviesUseCase.execute(MovieListType.NOW_PLAYING) } returns
				Observable.just(DummyDataProvider.getNowPlayingMovieList())
		
		every { getMoviesUseCase.execute(MovieListType.UPCOMING) } returns
				Observable.just(DummyDataProvider.getUpcomingMovieList())
		
		// When
		mainViewModel.getMovies()
		
		// Then
		val slot = slot<MainViewModel.MoviesViewState>()
		verify { viewStateObserver.onChanged(capture(slot)) }
		
		assertEquals(slot.captured.popularMovies.movies.size, DummyDataProvider.getPopularMovieList().movies.size)
		assertEquals(slot.captured.popularMovies, DummyDataProvider.getPopularMovieList())
		
		verify { getMoviesUseCase.execute(any()) }
		assertEquals(1, 1)
	}
	
}
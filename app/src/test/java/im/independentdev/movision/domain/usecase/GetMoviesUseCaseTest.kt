package im.independentdev.movision.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import im.independentdev.movision.data.model.base.MovieListType
import im.independentdev.movision.data.model.ui.MovieListViewItem
import im.independentdev.movision.data.repository.MovieRemoteRepository
import im.independentdev.movision.domain.mapper.MovieDataMapper
import im.independentdev.movision.domain.usecase.GetMoviesUseCase
import im.independentdev.movision.core.RxImmediateSchedulerRule
import im.independentdev.movision.data.DummyDataProvider
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observers.TestObserver
import org.junit.*
import java.io.IOException

class GetMoviesUseCaseTest {
	
	@Rule
	@JvmField
	val instantExecutorRule = InstantTaskExecutorRule()
	
	@Rule
	@JvmField
	var testSchedulerRule = RxImmediateSchedulerRule()
	
	@MockK
	lateinit var remoteRepository: MovieRemoteRepository
	
	@InjectMockKs
	lateinit var movieDataMapper: MovieDataMapper
	
	private lateinit var useCase: GetMoviesUseCase
	
	@Before
	fun setUp() {
		MockKAnnotations.init(this)
		useCase = GetMoviesUseCase(remoteRepository, movieDataMapper)
	}
	
	@Test
	fun `given success state when useCase executed for popular movies then returns expected data`() {
		// GIVEN
		val mockedCastResponse = DummyDataProvider.getMockedMovieResponse()
		every { remoteRepository.getPopularMovies(1) } returns Observable.just(mockedCastResponse)
		
		val testObserver = TestObserver<MovieListViewItem>()
		
		// WHEN
		val result = useCase.execute(MovieListType.POPULAR)
		result.subscribe(testObserver)
		val listResult = testObserver.values().first()
		
		// THEN
		verify { remoteRepository.getPopularMovies(1) }
		testObserver.assertComplete()
		testObserver.assertNoErrors()
		testObserver.assertValueCount(1)
		
		Assert.assertEquals(listResult, movieDataMapper.mapFrom(mockedCastResponse))
	}
	
	@Test
	fun `given success state when useCase executed for upcoming movies then returns expected data`() {
		// GIVEN
		val mockedCastResponse = DummyDataProvider.getMockedMovieResponse()
		every { remoteRepository.getUpcomingMovies(1) } returns Observable.just(mockedCastResponse)
		
		val testObserver = TestObserver<MovieListViewItem>()
		
		// WHEN
		val result = useCase.execute(MovieListType.UPCOMING)
		result.subscribe(testObserver)
		val listResult = testObserver.values().first()
		
		// THEN
		verify { remoteRepository.getUpcomingMovies(1) }
		testObserver.assertComplete()
		testObserver.assertNoErrors()
		testObserver.assertValueCount(1)
		
		Assert.assertEquals(listResult, movieDataMapper.mapFrom(mockedCastResponse))
	}
	
	@Test
	fun `given success state when useCase executed for now playing movies movies then returns expected data`() {
		// GIVEN
		val mockedCastResponse = DummyDataProvider.getMockedMovieResponse()
		every { remoteRepository.getNowPlayingMovies(1) } returns Observable.just(mockedCastResponse)
		
		val testObserver = TestObserver<MovieListViewItem>()
		
		// WHEN
		val result = useCase.execute(MovieListType.NOW_PLAYING)
		result.subscribe(testObserver)
		val listResult = testObserver.values().first()
		
		// THEN
		verify { remoteRepository.getNowPlayingMovies(1) }
		testObserver.assertComplete()
		testObserver.assertNoErrors()
		testObserver.assertValueCount(1)
		
		Assert.assertEquals(listResult, movieDataMapper.mapFrom(mockedCastResponse))
	}
	
	@Test
	fun `given error state when useCase executed for now playing movies movies then returns exception`() {
		// GIVEN
		val t = IOException("")
		every { remoteRepository.getNowPlayingMovies(1) } returns Observable.error(t)
		
		val testObserver = TestObserver<MovieListViewItem>()
		
		// WHEN
		val result = useCase.execute(MovieListType.NOW_PLAYING)
		result.subscribe(testObserver)
		
		// THEN
		verify { remoteRepository.getNowPlayingMovies(1) }
		testObserver.assertError(t)
	}
	
	
	
}
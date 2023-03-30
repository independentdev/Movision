package im.independentdev.movision.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import im.independentdev.movision.data.api.MovieAPI
import im.independentdev.movision.data.model.api.CreditsResponse
import im.independentdev.movision.data.model.api.MovieDetailResponse
import im.independentdev.movision.data.model.api.MovieListResponse
import im.independentdev.movision.data.repository.MovieRemoteRepository
import im.independentdev.movision.data.DummyDataProvider
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observers.TestObserver
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class MovieRemoteRepositoryTest {
	
	
	@Rule
	@JvmField
	val instantExecutorRule = InstantTaskExecutorRule()
	
	@MockK
	lateinit var movieAPI: MovieAPI
	
	private lateinit var movieRemoteRepository: MovieRemoteRepository
	
	@Before
	fun setUp() {
		MockKAnnotations.init(this)
		movieRemoteRepository = MovieRemoteRepository(movieAPI)
	}
	
	@Test
	fun `given success state when repo called for popular movies then returns expected data`() {
		// GIVEN
		val mockedResponse = DummyDataProvider.getMockedMovieResponse()
		every { movieRemoteRepository.getPopularMovies(1) } returns Observable.just(mockedResponse)
		
		val testObserver = TestObserver<MovieListResponse>()
		
		// WHEN
		val result = movieRemoteRepository.getPopularMovies(1)
		result.subscribe(testObserver)
		val observedValue = testObserver.values().first()
		
		// THEN
		verify { movieRemoteRepository.getPopularMovies(1) }
		testObserver.assertComplete()
		testObserver.assertNoErrors()
		testObserver.assertValueCount(1)
		
		assertEquals(observedValue, mockedResponse)
	}
	
	@Test
	fun `given error state when repo called for popular movies then returns expected data`() {
		// GIVEN
		val t = IOException("")
		every { movieRemoteRepository.getPopularMovies(1) } returns Observable.error(t)
		
		val testObserver = TestObserver<MovieListResponse>()
		
		// WHEN
		val result = movieRemoteRepository.getPopularMovies(1)
		result.subscribe(testObserver)
		
		// THEN
		verify { movieRemoteRepository.getPopularMovies(1) }
		testObserver.assertError(t)
	}
	
	@Test
	fun `given success state when repo called for upcoming movies then returns expected data`() {
		// GIVEN
		val mockedResponse = DummyDataProvider.getMockedMovieResponse()
		every { movieRemoteRepository.getUpcomingMovies(1) } returns Observable.just(mockedResponse)
		
		val testObserver = TestObserver<MovieListResponse>()
		
		// WHEN
		val result = movieRemoteRepository.getUpcomingMovies(1)
		result.subscribe(testObserver)
		val observedValue = testObserver.values().first()
		
		// THEN
		verify { movieRemoteRepository.getUpcomingMovies(1) }
		testObserver.assertComplete()
		testObserver.assertNoErrors()
		testObserver.assertValueCount(1)
		
		assertEquals(observedValue, mockedResponse)
	}
	
	@Test
	fun `given error state when repo called for upcoming movies then returns expected data`() {
		// GIVEN
		val t = IOException("")
		every { movieRemoteRepository.getUpcomingMovies(1) } returns Observable.error(t)
		
		val testObserver = TestObserver<MovieListResponse>()
		
		// WHEN
		val result = movieRemoteRepository.getUpcomingMovies(1)
		result.subscribe(testObserver)
		
		// THEN
		verify { movieRemoteRepository.getUpcomingMovies(1) }
		testObserver.assertError(t)
	}
	
	@Test
	fun `given success state when repo called for now playing movies then returns expected data`() {
		// GIVEN
		val mockedResponse = DummyDataProvider.getMockedMovieResponse()
		every { movieRemoteRepository.getNowPlayingMovies(1) } returns Observable.just(mockedResponse)
		
		val testObserver = TestObserver<MovieListResponse>()
		
		// WHEN
		val result = movieRemoteRepository.getNowPlayingMovies(1)
		result.subscribe(testObserver)
		val observedValue = testObserver.values().first()
		
		// THEN
		verify { movieRemoteRepository.getNowPlayingMovies(1) }
		testObserver.assertComplete()
		testObserver.assertNoErrors()
		testObserver.assertValueCount(1)
		
		assertEquals(observedValue, mockedResponse)
	}
	
	@Test
	fun `given error state when repo called for now playing movies then returns expected data`() {
		// GIVEN
		val t = IOException("")
		every { movieRemoteRepository.getNowPlayingMovies(1) } returns Observable.error(t)
		
		val testObserver = TestObserver<MovieListResponse>()
		
		// WHEN
		val result = movieRemoteRepository.getNowPlayingMovies(1)
		result.subscribe(testObserver)
		
		// THEN
		verify { movieRemoteRepository.getNowPlayingMovies(1) }
		testObserver.assertError(t)
	}
	
	@Test
	fun `given success state when repo called for get movie detail then returns expected data`() {
		// GIVEN
		val mockedResponse = DummyDataProvider.getMockedMovieDetailResponse()
		every { movieRemoteRepository.getMovieDetail(1) } returns Observable.just(mockedResponse)
		
		val testObserver = TestObserver<MovieDetailResponse>()
		
		// WHEN
		val result = movieRemoteRepository.getMovieDetail(1)
		result.subscribe(testObserver)
		val observedValue = testObserver.values().first()
		
		// THEN
		verify { movieRemoteRepository.getMovieDetail(1) }
		testObserver.assertComplete()
		testObserver.assertNoErrors()
		testObserver.assertValueCount(1)
		
		assertEquals(observedValue, mockedResponse)
	}
	
	@Test
	fun `given error state when repo called for get movie detail then returns expected data`() {
		// GIVEN
		val t = IOException("")
		every { movieRemoteRepository.getMovieDetail(1) } returns Observable.error(t)
		
		val testObserver = TestObserver<MovieDetailResponse>()
		
		// WHEN
		val result = movieRemoteRepository.getMovieDetail(1)
		result.subscribe(testObserver)
		
		// THEN
		verify { movieRemoteRepository.getMovieDetail(1) }
		testObserver.assertError(t)
	}
	
	@Test
	fun `given success state when repo called for movie credits then returns expected data`() {
		// GIVEN
		val mockedResponse = DummyDataProvider.getMockedCreditsResponse()
		every { movieRemoteRepository.getMovieCredits(1) } returns Observable.just(mockedResponse)
		
		val testObserver = TestObserver<CreditsResponse>()
		
		// WHEN
		val result = movieRemoteRepository.getMovieCredits(1)
		result.subscribe(testObserver)
		val observedValue = testObserver.values().first()
		
		// THEN
		verify { movieRemoteRepository.getMovieCredits(1) }
		testObserver.assertComplete()
		testObserver.assertNoErrors()
		testObserver.assertValueCount(1)
		
		assertEquals(observedValue, mockedResponse)
	}
	
	@Test
	fun `given error state when repo called for movie credits then returns expected data`() {
		// GIVEN
		val t = IOException("")
		every { movieRemoteRepository.getMovieCredits(1) } returns Observable.error(t)
		
		val testObserver = TestObserver<CreditsResponse>()
		
		// WHEN
		val result = movieRemoteRepository.getMovieCredits(1)
		result.subscribe(testObserver)
		
		// THEN
		verify { movieRemoteRepository.getMovieCredits(1) }
		testObserver.assertError(t)
	}
}
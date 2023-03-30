package im.independentdev.movision.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import im.independentdev.movision.data.model.ui.CastViewItem
import im.independentdev.movision.data.repository.MovieRemoteRepository
import im.independentdev.movision.domain.DomainConstants
import im.independentdev.movision.domain.mapper.CastItemMapper
import im.independentdev.movision.domain.mapper.MovieDetailMapper
import im.independentdev.movision.domain.usecase.MovieDetailUseCase
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

class MovieDetailUseCaseTest {
	
	@Rule
	@JvmField
	val instantExecutorRule = InstantTaskExecutorRule()
	
	@Rule
	@JvmField
	var testSchedulerRule = RxImmediateSchedulerRule()
	
	@MockK
	lateinit var remoteRepository: MovieRemoteRepository
	
	@InjectMockKs
	lateinit var movieDetailMapper: MovieDetailMapper
	
	@InjectMockKs
	lateinit var castItemMapper: CastItemMapper
	
	private lateinit var useCase: MovieDetailUseCase
	
	@Before
	fun setUp() {
		MockKAnnotations.init(this)
		useCase = MovieDetailUseCase(remoteRepository, movieDetailMapper, castItemMapper)
	}
	
	@Test
	fun `given success state when getMovieCredits called then return observable data`() {
		// GIVEN
		val mockedCastResponse = DummyDataProvider.getMockedCreditsResponse()
		every { remoteRepository.getMovieCredits(1) } returns Observable.just(mockedCastResponse)
		
		val testObserver = TestObserver<List<CastViewItem>>()
		
		// WHEN
		val result = useCase.getMovieCredits(1)
		result.subscribe(testObserver)
		val listResult = testObserver.values().first()
		
		// THEN
		verify { remoteRepository.getMovieCredits(1) }
		testObserver.assertComplete()
		testObserver.assertNoErrors()
		testObserver.assertValueCount(1)
		Assert.assertEquals(listResult.first().character, "(${mockedCastResponse.cast?.first()?.character})")
		Assert.assertEquals(listResult[1].character, "(${mockedCastResponse.cast?.get(1)?.character})")
		
		Assert.assertEquals(listResult.first().name, mockedCastResponse.cast?.first()?.name)
		Assert.assertEquals(listResult[1].name, mockedCastResponse.cast?.get(1)?.name)
		
		Assert.assertEquals(listResult.first().profilePath, "${DomainConstants.PROFILE_URL_BASE}${mockedCastResponse.cast?.first()?.profilePath}")
		Assert.assertEquals(listResult[1].profilePath, "${DomainConstants.PROFILE_URL_BASE}${mockedCastResponse.cast?.get(1)?.profilePath}")
	}
	
	@Test
	fun `given error state when getMovieCredits called then returns exception`() {
		// GIVEN
		val t = IOException("")
		every { remoteRepository.getMovieCredits(1) } returns Observable.error(t)
		
		val testObserver = TestObserver<List<CastViewItem>>()
		
		// WHEN
		val result = useCase.getMovieCredits(1)
		result.subscribe(testObserver)
		
		// THEN
		verify { remoteRepository.getMovieCredits(1) }
		testObserver.assertError(t)
	}
}
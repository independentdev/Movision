package im.independentdev.movision.domain.mapper

import im.independentdev.movision.domain.mapper.CastItemMapper
import im.independentdev.movision.domain.mapper.MovieDataMapper
import im.independentdev.movision.domain.usecase.GetMoviesUseCase
import im.independentdev.movision.data.DummyDataProvider
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class CastItemMapperTest {
	
	@InjectMockKs
	lateinit var castItemMapper: CastItemMapper
	
	@Before
	fun setUp() {
		MockKAnnotations.init(this)
	}
	
	@Test
	fun mapFrom() {
		val mappedObject = castItemMapper.mapFrom(DummyDataProvider.getMockedCreditsResponse())
		assertEquals(mappedObject, DummyDataProvider.getMockCreditList())
	}
}
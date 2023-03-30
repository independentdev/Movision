package im.independentdev.movision.di

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import im.independentdev.movision.BuildConfig
import im.independentdev.movision.data.api.ApiKeyInterceptor
import im.independentdev.movision.data.api.MovieAPI
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
	
	@Provides
	@Singleton
	fun provideLoggingInterceptor(): HttpLoggingInterceptor =
		HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
	
	@Provides
	@Singleton
	fun provideOkHttpClient(
		application: Application,
		loggingInterceptor: HttpLoggingInterceptor,
		keyInterceptor: ApiKeyInterceptor
	): OkHttpClient =
		with(OkHttpClient.Builder()) {
			if (BuildConfig.DEBUG) {
				addInterceptor(loggingInterceptor)
				addInterceptor(ChuckerInterceptor.Builder(application).build())
			}
			addInterceptor(keyInterceptor)
			connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
			readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
			writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
			build()
		}
	
	@Provides
	@Singleton
	fun provideRetrofit(client: OkHttpClient): Retrofit =
		Retrofit.Builder()
			.baseUrl(BuildConfig.API_URL)
			.client(client)
			.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
			.addConverterFactory(nullOnEmptyConverterFactory)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
	
	@Provides
	@Singleton
	fun provideMovieAPI(retrofit: Retrofit): MovieAPI = retrofit.create(MovieAPI::class.java)
	
	
	private val nullOnEmptyConverterFactory = object : Converter.Factory() {
		fun converterFactory() = this
		override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit) =
			object : Converter<ResponseBody, Any?> {
				val nextResponseBodyConverter = retrofit.nextResponseBodyConverter<Any?>(converterFactory(), type, annotations)
				
				override fun convert(value: ResponseBody) =
					if (value.contentLength() != 0L) nextResponseBodyConverter.convert(value) else null
			}
	}
	
	companion object {
		private const val DEFAULT_TIMEOUT = 60L
	}
}
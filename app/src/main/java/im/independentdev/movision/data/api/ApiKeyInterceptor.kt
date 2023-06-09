package im.independentdev.movision.data.api

import im.independentdev.movision.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 *  API requires key parameter in the requests
 *  This class as interceptor intercepts the request and adds required parameter
 */
class ApiKeyInterceptor @Inject constructor() : Interceptor {
	
	override fun intercept(chain: Interceptor.Chain): Response {
		val original = chain.request()
		val originalHttpUrl = original.url
		val requestBuilder = original.newBuilder().url(originalHttpUrl.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build())
		return chain.proceed(requestBuilder.build())
	}
}
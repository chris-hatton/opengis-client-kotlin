package opengis.test

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import opengis.model.request.OpenGisRequest
import org.junit.Before
import opengis.process.okhttp.newCall

/**
 * Created by Chris on 19/09/2017.
 */
abstract class OpenGisTests {

    protected val httpClient = OkHttpClient.Builder().build()

    companion object {
        val baseUrl : HttpUrl = HttpUrl.parse("http://10.0.1.68:8080/")!!
    }

    protected inline fun <reified Result> getSync( openGisRequest: OpenGisRequest<Result> ) : Result {
        val call = httpClient.newCall(baseUrl = baseUrl, openGisRequest = openGisRequest)
        val response = call.execute()
        response.body()!!.bytes()

    }

    @Before
    fun setUp() {

    }
}
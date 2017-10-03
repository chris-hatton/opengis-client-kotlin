package opengis.process.okhttp

import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import opengis.model.app.request.OpenGisRequest

/**
 * Created by Chris on 16/09/2017.
 */
fun <Result> OkHttpClient.newCall(baseUrl: HttpUrl, openGisRequest: OpenGisRequest<Result> ) : Call {
    val call = OpenGisRequestAdapter.urlRequest( baseUrl, openGisRequest )
    return newCall( call )
}


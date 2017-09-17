package opengis.process.okhttp

import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import opengis.model.request.OpenGisRequest

/**
 * Created by Chris on 16/09/2017.
 */
fun OkHttpClient.newCall( baseUrl: HttpUrl, openGisRequest: OpenGisRequest ) : Call {
    val call = OpenGisRequestAdapter.urlRequest( baseUrl, openGisRequest )
    return newCall( call ).
}

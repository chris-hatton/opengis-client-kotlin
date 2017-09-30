package opengis.process.okhttp

import okhttp3.HttpUrl
import okhttp3.Request
import opengis.model.request.OpenGisRequest

object OpenGisRequestAdapter {

    fun <Result> urlRequest( baseUrl: HttpUrl, openGisRequest: OpenGisRequest<Result> ) : Request = with(Request.Builder()) {
        method("GET",null)
        val url : HttpUrl = this@OpenGisRequestAdapter.url( baseUrl, openGisRequest )
        println("OpenGIS URL: '$url'")
        url( url )
        build()
    }

    fun <Result> url( baseUrl: HttpUrl, openGisRequest: OpenGisRequest<Result> ) : HttpUrl {
        val parameters = openGisRequest.getParameters()
        return with( baseUrl.newBuilder() ) {
            parameters.forEach { (name,value) -> addQueryParameter(name,value) }
            build()
        }
    }
}

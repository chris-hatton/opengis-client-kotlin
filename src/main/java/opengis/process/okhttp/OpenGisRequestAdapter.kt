package opengis.process.okhttp

import okhttp3.HttpUrl
import okhttp3.Request
import opengis.model.request.OpenGisRequest

object OpenGisRequestAdapter {

    fun <Result> urlRequest( baseUrl: HttpUrl, openGisRequest: OpenGisRequest<Result> ) : Request = with(Request.Builder()) {
        method("GET",null)
        url( this@OpenGisRequestAdapter.url( baseUrl, openGisRequest ) )
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

package opengis.process

import opengis.model.app.OpenGisHttpServer
import opengis.model.app.OpenGisService
import opengis.model.app.request.OpenGisRequest
import java.io.InputStream
import java.net.URL

/**
 * Created by Chris on 27/11/2017.
 */
abstract class OpenGisHttpClient(val server: OpenGisHttpServer) : DeserializingOpenGisRequestProcessor {

    sealed class Exception : kotlin.Exception() {
        object ServiceUnsupportedByServerException : Exception()
    }

    override fun <Result : Any> getBytes(request: OpenGisRequest<Result>, callback: Callback<InputStream>) {
        val url = server.url( request )
        return getBytes(url = url, request = request, callback = callback)
    }

    abstract fun <Result : Any> getBytes(url: URL, request: OpenGisRequest<Result>, callback: Callback<InputStream>)
}
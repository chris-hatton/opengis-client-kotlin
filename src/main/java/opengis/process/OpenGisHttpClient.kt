package opengis.process

import opengis.model.app.OpenGisServer
import opengis.model.app.OpenGisService
import opengis.model.app.request.OpenGisRequest
import java.io.InputStream
import java.net.URL

/**
 * Created by Chris on 27/11/2017.
 */
abstract class OpenGisHttpClient(val server: OpenGisServer) : DeserializingOpenGisRequestProcessor {

    sealed class Exception : kotlin.Exception() {
        object ServiceUnsupportedByServerException : Exception()
    }

    override fun <Result : Any> getBytes(request: OpenGisRequest<Result>, callback: Callback<InputStream>) {
        val service : OpenGisService<*> = server.service( request = request ) ?: throw Exception.ServiceUnsupportedByServerException
        return getBytes(url = service.url, request = request, callback = callback)
    }

    abstract fun <Result : Any> getBytes(url: URL, request: OpenGisRequest<Result>, callback: Callback<InputStream>)
}
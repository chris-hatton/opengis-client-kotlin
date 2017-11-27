package opengis.process

import opengis.model.app.OpenGisServer
import opengis.model.app.OpenGisService
import opengis.model.app.request.OpenGisRequest
import java.net.URL
import kotlin.reflect.KClass


/**
 * Created by Chris on 16/09/2017.
 */
interface OpenGisRequestProcessor {

    interface Callback<in Result> {
        fun success( result: Result )
        fun error( error: Throwable )
    }

    fun <Result:Any> execute(
            request    : OpenGisRequest<Result>,
            resultType : KClass<Result>,
            callback   : Callback<Result>
        )

    sealed class Exception : kotlin.Exception() {
        object UnhandledRequestType : Exception()
        data class ServerError( val xmlString: String ) : Exception()
    }
}

inline fun <reified Result:Any> OpenGisRequestProcessor.execute(
    request    : OpenGisRequest<Result>,
    callback   : OpenGisRequestProcessor.Callback<Result>
) = this.execute(
        request    = request,
        resultType = Result::class,
        callback   = callback
)
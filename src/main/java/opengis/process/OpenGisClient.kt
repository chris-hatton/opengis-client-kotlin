package opengis.process

import opengis.model.app.request.OpenGisRequest
import kotlin.reflect.KClass


/**
 * Created by Chris on 16/09/2017.
 */
interface OpenGisClient {

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

inline fun <reified Result:Any> OpenGisClient.execute(
                 request    : OpenGisRequest<Result>,
                 callback   : OpenGisClient.Callback<Result>
) = this.execute(
        request    = request,
        resultType = Result::class,
        callback   = callback
)
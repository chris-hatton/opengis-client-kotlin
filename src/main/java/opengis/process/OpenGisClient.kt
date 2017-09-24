package opengis.process

import opengis.model.request.OpenGisRequest
import kotlin.reflect.KClass

typealias Callback<Result> = (Result)->Unit

/**
 * Created by Chris on 16/09/2017.
 */
interface OpenGisClient {

    fun <Result:Any> execute(
            request    : OpenGisRequest<Result>,
            resultType : KClass<Result>,
            callback   : Callback<Result>
        )

    sealed class Exception : kotlin.Exception() {
        object UnhandledRequestType : Exception()
    }
}

inline fun <reified Result:Any> OpenGisClient.execute(
                 request    : OpenGisRequest<Result>,
        noinline callback   : Callback<Result>
) = this.execute(
        request    = request,
        resultType = Result::class,
        callback   = callback
)
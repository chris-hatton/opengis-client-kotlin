package opengis.process

import opengis.model.request.OpenGisRequest
import kotlin.reflect.KClass

/**
 * Created by Chris on 24/09/2017.
 */
interface OpenGisResponseDeserializer {

    sealed class Exception : kotlin.Exception() {
        object UnhandledType : Exception()
    }

    fun <Result:Any> deserializeResult(
            bytes       : ByteArray,
            request     : OpenGisRequest<Result>,
            resultClass : KClass<Result>
        ) : Result

    /**
     * Combine two response deserializers by order of precedence
     */
    fun then( next: OpenGisResponseDeserializer ) = object : OpenGisResponseDeserializer {
        override fun <Result : Any> deserializeResult(bytes: ByteArray, request: OpenGisRequest<Result>, resultClass: KClass<Result>): Result {
            return try {
                this@OpenGisResponseDeserializer.deserializeResult(bytes,request,resultClass)
            } catch( _: Exception.UnhandledType ) {
                next.deserializeResult(bytes,request,resultClass)
            }
        }
    }
}
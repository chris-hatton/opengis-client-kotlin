package opengis.process

import opengis.model.app.request.OpenGisRequest
import opengis.process.deserialize.OpenGisResponseDeserializer
import java.io.InputStream
import kotlin.reflect.KClass

/**
 * Created by Chris on 23/09/2017.
 */
interface DeserializingOpenGisRequestProcessor : OpenGisRequestProcessor, OpenGisResponseDeserializer {

    override fun <Result:Any> execute(
            request    : OpenGisRequest<Result>,
            resultType : KClass<Result>,
            callback   : OpenGisRequestProcessor.Callback<Result>
        ) {

        val bytesCallback = object : OpenGisRequestProcessor.Callback<InputStream> {
            override fun success(result: InputStream) {
                try {
                    val deserializedResult = deserializeResult( result, request, resultType )
                    callback.success( deserializedResult )
                } catch( error: Throwable ) {
                    callback.error( error )
                }
            }

            override fun error(error: Throwable) = callback.error(error)
        }

        getBytes( request, bytesCallback )
    }

    fun <Result:Any> getBytes( request: OpenGisRequest<Result>, callback: OpenGisRequestProcessor.Callback<InputStream>)
}
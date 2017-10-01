package opengis.process

import opengis.model.request.OpenGisRequest
import opengis.process.deserialize.OpenGisResponseDeserializer
import java.io.InputStream
import kotlin.reflect.KClass

/**
 * Created by Chris on 23/09/2017.
 */
interface DeserializingOpenGisClient : OpenGisClient, OpenGisResponseDeserializer {

    override fun <Result:Any> execute(
            request    : OpenGisRequest<Result>,
            resultType : KClass<Result>,
            callback   : OpenGisClient.Callback<Result>
        ) {

        val bytesCallback = object : OpenGisClient.Callback<InputStream> {
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

    fun <Result:Any> getBytes( request: OpenGisRequest<Result>, callback: OpenGisClient.Callback<InputStream>)
}
package opengis.process

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.ASCIIUtility.getBytes
import opengis.model.request.OpenGisRequest
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

        val bytesCallback = object : OpenGisClient.Callback<ByteArray> {
            override fun success(result: ByteArray) {
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

    fun <Result:Any> getBytes( request: OpenGisRequest<Result>, callback: OpenGisClient.Callback<ByteArray>)
}
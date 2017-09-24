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
            callback   : Callback<Result>
        ) {
        getBytes( request ) { bytes ->
            val result = deserializeResult( bytes, request, resultType )
            callback( result )
        }
    }

    fun <Result:Any> getBytes( request: OpenGisRequest<Result>, callback: Callback<ByteArray>)
}
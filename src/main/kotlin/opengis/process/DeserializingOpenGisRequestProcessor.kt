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
            callback   : Callback<Result>
        ) {

        val bytesCallback : Callback<InputStream> = { outcome ->
            when(outcome) {
                is Outcome.Success -> {
                    val deserializedResult = deserializeResult( outcome.result, request, resultType )
                    callback(Outcome.Success(deserializedResult))
                }
                is Outcome.Error -> {
                    callback(Outcome.Error(outcome.error))
                }
            }
        }

        getBytes( request, bytesCallback )
    }

    fun <Result:Any> getBytes( request: OpenGisRequest<Result>, callback: Callback<InputStream>)
}
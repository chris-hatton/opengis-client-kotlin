package opengis.process

import opengis.model.request.OpenGisRequest
import kotlin.reflect.KClass

/**
 * Created by Chris on 23/09/2017.
 */
interface DeserializingOpenGisClient : OpenGisClient {

    override fun <Result:Any> execute(
            request    : OpenGisRequest<Result>,
            resultType : KClass<Result>,
            callback   : Callback<Result>
        ) {
        getBytesAsync( request ) { bytes ->
            val result = deserializeResult<Result>( bytes )
            callback( result )
        }
    }

    fun <Result> getBytesSync( request: OpenGisRequest<Result>) : ByteArray

    fun <Result> getBytesAsync( request: OpenGisRequest<Result>, callback: Callback<ByteArray>)

    fun <Result> deserializeResult( bytes: ByteArray ) : Result
}
package opengis.process.deserialize.impl

import opengis.model.request.OpenGisRequest
import opengis.process.deserialize.OpenGisResponseDeserializer
import java.io.InputStream
import kotlin.reflect.KClass

class ByteArrayDeserializer : OpenGisResponseDeserializer {

    override fun <Result : Any> deserializeResult(
            bytes       : InputStream,
            request     : OpenGisRequest<Result>,
            resultClass : KClass<Result>
    ): Result = when(resultClass) {
        ByteArray::class -> bytes.readBytes() as Result
        else -> throw OpenGisResponseDeserializer.Exception.UnhandledType
    }
}


package opengis.process.deserialize

import opengis.model.app.request.OpenGisRequest
import opengis.process.deserialize.impl.ByteArrayDeserializer
import opengis.process.deserialize.impl.OpenGisXmlResponseDeserializer
import opengis.process.deserialize.impl.OpenGisJsonResponsesDeserializer
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream
import kotlin.reflect.KClass

/**
 * Created by Chris on 24/09/2017.
 */
interface OpenGisResponseDeserializer {

    sealed class Exception : kotlin.Exception() {
        object UnhandledType : Exception()
        object MalformedData : Exception()
    }

    fun <Result:Any> deserializeResult(
            bytes       : InputStream,
            request     : OpenGisRequest<Result>,
            resultClass : KClass<Result>
        ) : Result

    /**
     * Combine two response deserializers by order of precedence
     */
    fun then( next: OpenGisResponseDeserializer) = object : OpenGisResponseDeserializer {
        override fun <Result : Any> deserializeResult(bytes: InputStream, request: OpenGisRequest<Result>, resultClass: KClass<Result>): Result {
            return try {
                this@OpenGisResponseDeserializer.deserializeResult(bytes,request,resultClass)
            } catch( _: Exception.UnhandledType) {
                next.deserializeResult(bytes,request,resultClass)
            }
        }
    }

    companion object {
        fun createDefault( xmlPullParserFactory: XmlPullParserFactory = XmlPullParserFactory.newInstance() ) : OpenGisResponseDeserializer {
            return ByteArrayDeserializer()
                .then(OpenGisXmlResponseDeserializer(xmlPullParserFactory))
                .then(OpenGisJsonResponsesDeserializer())
        }
    }
}
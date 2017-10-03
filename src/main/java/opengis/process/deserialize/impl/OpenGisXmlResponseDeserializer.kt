package opengis.process.deserialize.impl

import opengis.model.request.OpenGisRequest
import opengis.model.xml.wfs.WfsCapabilities
import opengis.process.deserialize.OpenGisResponseDeserializer
import org.simpleframework.xml.core.Persister
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream
import kotlin.reflect.KClass

/**
 * Created by Chris on 01/10/2017.
 */
class OpenGisXmlResponseDeserializer(val pullParserFactory: XmlPullParserFactory) : OpenGisResponseDeserializer {

    override fun <Result : Any> deserializeResult(
        bytes       : InputStream,
        request     : OpenGisRequest<Result>,
        resultClass : KClass<Result>
    ): Result {
        val serializer = Persister()
        return when( resultClass ) {
            WfsCapabilities::class -> serializer.read(WfsCapabilities::class.java, bytes) as Result
            else -> throw OpenGisResponseDeserializer.Exception.UnhandledType
        }
    }
}
package opengis.process.deserialize.impl

import opengis.model.app.request.OpenGisRequest
import opengis.model.xml.wfs.WfsCapabilities
import opengis.process.deserialize.OpenGisResponseDeserializer
import org.simpleframework.xml.core.Persister
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream
import kotlin.reflect.KClass

/**
 * Response deserializer which uses SimpleXML and a suitably annotated model class to
 * unmarshal XML data.
 *
 * Several key geo-server services such as WmsCapabilities, WfsCapabilities, only support XML
 * responses.
 */
class OpenGisXmlResponseDeserializer(val pullParserFactory: XmlPullParserFactory) : OpenGisResponseDeserializer {

    override fun <Result : Any> deserializeResult(
        bytes       : InputStream,
        request     : OpenGisRequest<Result>,
        resultClass : KClass<Result>
    ): Result {
        val serializer = Persister()
        @Suppress("UNCHECKED_CAST")
        return when( resultClass ) {
            WfsCapabilities::class -> serializer.read(WfsCapabilities::class.java, bytes) as Result
            else -> throw OpenGisResponseDeserializer.Exception.UnhandledType
        }
    }
}
package opengis.process.deserialize.impl

import opengis.model.request.OpenGisRequest
import opengis.model.xml.wfs.WfsCapabilities
import opengis.process.deserialize.OpenGisResponseDeserializer
import opengis.process.deserialize.unmarshal.impl.WfsCapabilitiesUnmarshaller
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream
import kotlin.reflect.KClass
import org.xmlpull.v1.XmlPullParser
import java.io.InputStreamReader

/**
 * Created by Chris on 01/10/2017.
 */
class OpenGisXmlResponseDeserializer(val pullParserFactory: XmlPullParserFactory) : OpenGisResponseDeserializer {

    override fun <Result : Any> deserializeResult(
        bytes       : InputStream,
        request     : OpenGisRequest<Result>,
        resultClass : KClass<Result>
    ): Result {
        val parser: XmlPullParser = pullParserFactory.newPullParser().apply {
            setInput(InputStreamReader(bytes))
            setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        }
        return when( resultClass ) {
            WfsCapabilities::class -> WfsCapabilitiesUnmarshaller.unmarshal(parser) as Result
            else -> throw OpenGisResponseDeserializer.Exception.UnhandledType
        }
    }
}
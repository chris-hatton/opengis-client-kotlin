package opengis.process.deserialize.impl

import opengis.model.request.OpenGisRequest
import opengis.process.deserialize.OpenGisResponseDeserializer
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream
import javax.xml.bind.JAXBContext
import kotlin.reflect.KClass

/**
 * Created by Chris on 01/10/2017.
 */
class CapabilitiesDeserializer(val pullParserFactory: XmlPullParserFactory) : OpenGisResponseDeserializer {

    override fun <Result : Any> deserializeResult(
            bytes       : InputStream,
            request     : OpenGisRequest<Result>,
            resultClass : KClass<Result>
    ): Result = when(resultClass) {
        opengis.model.response.wms.ServiceMetaData::class,
        opengis.model.response.wmts.ServiceMetaData::class,
        opengis.model.response.wfs.ServiceMetaData::class -> {

            val parser = pullParserFactory.newPullParser()

            val jaxbContext       = JAXBContext.newInstance(resultClass::class.java)
            val jaxbUnmarshaller  = jaxbContext.createUnmarshaller()
            @Suppress("UNCHECKED_CAST")
            jaxbUnmarshaller.unmarshal(bytes) as Result
        }
        else -> throw OpenGisResponseDeserializer.Exception.UnhandledType
    }
}
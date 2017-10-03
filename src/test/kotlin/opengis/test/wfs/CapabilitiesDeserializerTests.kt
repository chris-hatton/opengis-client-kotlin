package opengis.test.wfs

import opengis.model.request.wfs.GetCapabilities
import opengis.model.xml.wfs.WfsCapabilities
import opengis.process.deserialize.impl.OpenGisXmlResponseDeserializer
import org.junit.Assert
import org.junit.Test
import org.kxml2.io.KXmlParser
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import org.simpleframework.xml.core.Persister



/**
 * Created by Chris on 01/10/2017.
 */
class CapabilitiesDeserializerTests {

//    @Test
//    fun testDeserializeWfsCapabilities() {
//        val input = this.javaClass.getResourceAsStream("/wfs-GetCapabilities.xml")
//        val parserFactory = object : XmlPullParserFactory() {
//            override fun newPullParser(): XmlPullParser = KXmlParser()
//        }
//        val deserializer = OpenGisXmlResponseDeserializer(parserFactory)
//        val mockRequest = GetCapabilities()
//        val serviceMetaData = deserializer.deserializeResult(input,mockRequest, WfsCapabilities::class)
//
//        Assert.assertEquals( "Hello", serviceMetaData.serviceProvider.providerName )
//    }

    @Test
    fun testDeserializeWfsCapabilities() {
        val input = this.javaClass.getResourceAsStream("/wfs-GetCapabilities.xml")
        val serializer = Persister()
        val example = serializer.read(WfsCapabilities::class.java, input)
        println(example)

        //Assert.assertEquals( "Hello", serviceMetaData.serviceProvider.providerName )
    }
}
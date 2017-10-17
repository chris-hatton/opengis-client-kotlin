package opengis.test

import opengis.model.xml.wms.WmsCapabilities
import org.junit.Assert
import org.simpleframework.xml.core.Persister
import java.io.InputStream

/**
 * Created by Chris on 15/10/2017.
 */
abstract class AbstractStreamWmsTests : AbstractWmsTests() {

    abstract fun testWmsCapabilities( capabilities: WmsCapabilities )

    abstract val getCapabilitiesInputStream : InputStream

    final override fun testGetCapabilities() = test(getCapabilitiesInputStream,this::testWmsCapabilities)

    private inline fun <reified T> test(inputStream: InputStream, testFunction: (T)->Unit ) {
        val serializer = Persister()
        try {
            val response : T = serializer.read(T::class.java, inputStream)
            testFunction( response )
        } catch(e: Throwable) {
            e.printStackTrace()
            Assert.fail(e.localizedMessage)
        }
    }
}
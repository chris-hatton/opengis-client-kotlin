package opengis.test.impl

import opengis.model.xml.wms.WmsCapabilities
import opengis.test.AbstractStreamWmsTests
import org.junit.Assert
import java.io.InputStream

/**
 * Created by Chris on 15/10/2017.
 */
class ReferenceWmsTests : AbstractStreamWmsTests() {

    override val getCapabilitiesInputStream: InputStream
        get() = this.javaClass.getResourceAsStream("/data-vic-gov-au/wms-GetCapabilities-1.3.0.xml")

    override fun testWmsCapabilities(capabilities: WmsCapabilities) {
        Assert.assertEquals("","")
    }
}
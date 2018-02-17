package opengis.test.impl

import opengis.model.transport.xml.wms.WmsCapabilities
import opengis.test.WmsTests
import opengis.test.util.XmlDataTests.test
import org.junit.Assert
import org.junit.Ignore
import org.junit.Test

class ReferenceWmsTests : WmsTests {

    @Test
    override fun testGetCapabilities() = test( resource = "/opengis-reference/wms-GetCapabilities-1.3.0.xml") {
        capabilities: WmsCapabilities ->
        Assert.assertTrue( capabilities.service?.abstract?.isNotBlank() ?: false )
    }

    @Ignore
    override fun testGetFeatureInfo() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Ignore
    override fun testGetMap() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
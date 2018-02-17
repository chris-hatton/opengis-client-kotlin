package opengis.test.impl

import opengis.model.transport.xml.wfs.WfsCapabilities
import opengis.test.WfsTests
import opengis.test.util.XmlDataTests
import org.junit.Ignore
import org.junit.Test

/**
 * Test for successful un-marsharshalling of various examples of OpenGIS standard responses from
 * the Victorian State of Australia's Public geo-server.
 *
 * Created by Chris on 30/10/2017.
 */
class VictoriaWfsTests : WfsTests {

    @Ignore
    override fun testDescribeFeatureType() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Test
    override fun testGetCapabilities() = XmlDataTests.test(resource = "/data-vic-gov-au/wfs-GetCapabilities-1.1.0.xml") {
        capabilities: WfsCapabilities ->
        capabilities.featureTypeList
    }

    @Ignore
    override fun testGetFeature() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Ignore
    override fun testGetFeatureWithLock() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Ignore
    override fun testGetPropertyValue() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Ignore
    override fun testLockFeature() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Ignore
    override fun testTransaction() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
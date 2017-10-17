package opengis.test

import opengis.model.xml.wms.WmsCapabilities
import opengis.test.OpenGisTests
import org.junit.Ignore
import org.junit.Test
import org.simpleframework.xml.core.Persister

/**
 * Created by Chris on 19/09/2017.
 */
abstract class AbstractWmsTests {

    @Test
    abstract fun testGetCapabilities()

    @Ignore("Not yet implemented")
    @Test
    fun testGetFeatureInfo() {

    }

    @Ignore("Not yet implemented")
    @Test
    fun testGetMap() {

    }
}
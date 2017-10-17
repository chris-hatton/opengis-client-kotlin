package opengis.test

import opengis.model.xml.wfs.WfsCapabilities
import opengis.test.OpenGisTests
import org.junit.Ignore
import org.junit.Test
import org.simpleframework.xml.core.Persister

/**
 * Created by Chris on 19/09/2017.
 */
abstract class AbstractWfsTests : OpenGisTests() {

    @Ignore("Not yet implemented")
    @Test
    fun testDescribeFeatureType() {

    }

    @Test
    abstract fun testGetCapabilities()

    @Ignore("Not yet implemented")
    @Test
    fun testGetFeature() {
    }

    @Ignore("Not yet implemented")
    @Test
    fun testGetFeatureWithLock() {

    }

    @Ignore("Not yet implemented")
    @Test
    fun testGetPropertyValue() {

    }

    @Ignore("Not yet implemented")
    @Test
    fun testLockFeature() {

    }

    @Ignore("Not yet implemented")
    @Test
    fun testTransaction() {

    }
}
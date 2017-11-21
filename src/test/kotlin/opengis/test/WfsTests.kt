package opengis.test

import org.junit.Test

interface WfsTests {
    @Test fun testDescribeFeatureType()
    @Test fun testGetCapabilities()
    @Test fun testGetFeature()
    @Test fun testGetFeatureWithLock()
    @Test fun testGetPropertyValue()
    @Test fun testLockFeature()
    @Test fun testTransaction()
}
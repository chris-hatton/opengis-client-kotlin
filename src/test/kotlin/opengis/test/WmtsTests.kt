package opengis.test

import org.junit.Test

interface WmtsTests {
    @Test fun testGetCapabilities()
    @Test fun testGetFeatureInfo()
    @Test fun testGetTile()
}
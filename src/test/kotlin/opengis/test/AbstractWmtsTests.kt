package opengis.test

import opengis.model.app.MimeType
import opengis.model.app.request.wmts.GetCapabilities
import opengis.test.OpenGisTests
import org.junit.Ignore
import org.junit.Test

/**
 * Created by Chris on 19/09/2017.
 */
abstract class AbstractWmtsTests : OpenGisTests() {
    @Ignore("Not yet implemented")
    @Test
    fun testGetCapabilities() {
        val request = GetCapabilities( format = MimeType.JSON, updateSequence = null )
        TODO()
        //httpClient.newCall("")
    }

    @Ignore("Not yet implemented")
    @Test
    fun testGetFeatureInfo() {

    }

    @Ignore("Not yet implemented")
    @Test
    fun testGetTile() {

    }
}
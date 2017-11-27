package opengis.test.process

import opengis.model.app.OpenGisServer
import opengis.process.ServerListLoader
import org.junit.Assert
import org.junit.Test
import java.net.URL

/**
 * Created by Chris on 25/11/2017.
 */
class TestServerListLoader {

    @Test
    fun testServerListLoader() {
        val serverListUrl = URL("http://gis.chrishatton.org/serverList.json")
        val serverList : List<OpenGisServer> = ServerListLoader.load(serverListUrl)

        Assert.assertEquals( 1, serverList.count() )

        val server = serverList[0]

        Assert.assertEquals( 1, server.services.count() )
    }

}
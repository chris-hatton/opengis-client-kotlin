package opengis.test.process

import opengis.model.app.OpenGisServer
import opengis.model.app.request.wms.GetCapabilities
import opengis.model.xml.wms.WmsCapabilities
import opengis.process.Callback
import opengis.process.Outcome
import opengis.process.ServerListLoader
import opengis.process.execute
import opengis.process.okhttp.OkHttpOpenGisClient
import org.junit.Assert
import org.junit.Test

/**
 * Created by Chris on 25/11/2017.
 */
class TestServerListLoader {

    @Test
    fun testServerListLoader() {

        val serverList : Set<OpenGisServer> = ServerListLoader.load(resourcePath = "/serverList.json")

        Assert.assertEquals( 1, serverList.count() )

        val server = serverList.first()

        Assert.assertEquals( 4, server.services.count() )

        val client = OkHttpOpenGisClient(server)

        val callback : Callback<WmsCapabilities> = { outcome ->
            when(outcome) {
                is Outcome.Success -> {
                    Assert.assertEquals( "Yo!", outcome.result.service?.name )
                }
                is Outcome.Error -> {
                    Assert.fail( outcome.error.localizedMessage )
                }
            }
        }

        client.execute( request = GetCapabilities(), callback = callback )
    }

}
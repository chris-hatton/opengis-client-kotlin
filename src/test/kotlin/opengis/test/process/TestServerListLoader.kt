package opengis.test.process

import opengis.model.app.OpenGisHttpServer
import opengis.model.app.request.wms.GetCapabilities
import opengis.model.transport.xml.wms.WmsCapabilities
import opengis.process.Callback
import opengis.process.Outcome
import opengis.process.ServerListLoader
import opengis.process.execute
import opengis.process.okhttp.OkHttpOpenGisClient
import org.junit.Assert
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Created by Chris on 25/11/2017.
 */
class TestServerListLoader {

    @Test
    fun testServerListLoader() {

        val serverList : Set<OpenGisHttpServer> = ServerListLoader.load(resourcePath = "/serverList.json")

        Assert.assertEquals( 2, serverList.count() )

        val server = serverList.first()

        Assert.assertEquals( 4, server.services.count() )

        val client = OkHttpOpenGisClient(server)

        val latch = CountDownLatch(1)

        val callback : Callback<WmsCapabilities> = { outcome ->
            when(outcome) {
                is Outcome.Success -> {
                    Assert.assertEquals( "Yo!", outcome.result.service?.name )
                    latch.countDown()
                }
                is Outcome.Error -> {
                    Assert.fail( outcome.error.localizedMessage )
                    latch.countDown()
                }
            }
        }

        client.execute( request = GetCapabilities(), callback = callback )

        latch.await(10,TimeUnit.SECONDS)
    }

}
package opengis.test.util

import org.junit.Assert
import org.simpleframework.xml.core.Persister
import java.io.InputStream

/**
 * Created by Chris on 30/10/2017.
 */
object XmlDataTests {

    inline fun <reified T> test( resource: String, testFunction: (T)->Unit ) =
            test(this.javaClass.getResourceAsStream(resource), testFunction)

    inline fun <reified T> test(stream: InputStream, testFunction: (T)->Unit ) {
        val serializer = Persister()
        try {
            val response : T = serializer.read(T::class.java, stream)
            testFunction( response )
        } catch(e: Throwable) {
            e.printStackTrace()
            Assert.fail(e.localizedMessage)
        }
    }
}
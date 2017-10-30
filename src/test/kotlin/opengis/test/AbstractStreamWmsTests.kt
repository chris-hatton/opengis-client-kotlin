package opengis.test

import org.junit.Assert
import org.simpleframework.xml.core.Persister
import java.io.InputStream

abstract class AbstractStreamTests<T> {

    abstract val inputStream: InputStream

    protected inline fun <reified T> test(inputStream: InputStream, testFunction: (T)->Unit ) {
        val serializer = Persister()
        try {
            val response : T = serializer.read(T::class.java, inputStream)
            testFunction( response )
        } catch(e: Throwable) {
            e.printStackTrace()
            Assert.fail(e.localizedMessage)
        }
    }
}
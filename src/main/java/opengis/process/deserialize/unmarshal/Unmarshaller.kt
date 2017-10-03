package opengis.process.deserialize.unmarshal

import org.xmlpull.v1.XmlPullParser

/**
 * Created by Chris on 02/10/2017.
 */
interface Unmarshaller<T> {

    sealed class Exception : kotlin.Exception() {
        data class ObjectIncomplete(val missingTag:String) : Exception()
    }

    fun unmarshal( parser: XmlPullParser ) : T
}
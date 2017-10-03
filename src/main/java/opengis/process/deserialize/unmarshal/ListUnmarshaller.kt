package opengis.process.deserialize.unmarshal

import org.xmlpull.v1.XmlPullParser

/**
 * Created by Chris on 02/10/2017.
 */
class ListUnmarshaller<T>(val elementTag: String, val elementUnmarshaller: Unmarshaller<T> ) : Unmarshaller<List<T>> {

    sealed class Exception : kotlin.Exception() {
        data class UnexpectedTag( val found: String, val expected: String) : Exception()
    }

    override fun unmarshal(parser: XmlPullParser): List<T> {

        val list = mutableListOf<T>()

        while (parser.next() != XmlPullParser.END_TAG) {
            when(parser.eventType) {
                XmlPullParser.START_TAG -> {
                    if(parser.name == elementTag) {
                        val element = elementUnmarshaller.unmarshal(parser)
                        list.add(element)
                    } else throw Exception.UnexpectedTag(found = parser.name, expected = elementTag)

                }
                else -> {}  // Ignore
            }
        }

        return list.toList()
    }
}
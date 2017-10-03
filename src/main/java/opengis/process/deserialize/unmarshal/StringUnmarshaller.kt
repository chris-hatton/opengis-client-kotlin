package opengis.process.deserialize.unmarshal

import org.xmlpull.v1.XmlPullParser

object StringUnmarshaller : Unmarshaller<String> {
    override fun unmarshal(parser: XmlPullParser): String = parser.nextText()
}

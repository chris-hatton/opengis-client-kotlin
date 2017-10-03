package opengis.process.deserialize.unmarshal.impl.ows.operation

import opengis.model.xml.ows.operation.Constraint
import opengis.process.deserialize.unmarshal.Unmarshaller
import org.xmlpull.v1.XmlPullParser

/**
 * Created by Chris on 03/10/2017.
 */
object ConstraintUnmarshaller : Unmarshaller<Constraint> {
    override fun unmarshal(parser: XmlPullParser) : Constraint {
        TODO()
    }
}
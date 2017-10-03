package opengis.process.deserialize.unmarshal.impl.ows.operation

import opengis.model.xml.ows.operation.DCP
import opengis.process.deserialize.unmarshal.ImmutableObjectUnmarshaller

/**
 * Created by Chris on 03/10/2017.
 */
object DCPUnmarshaller : ImmutableObjectUnmarshaller<DCPUnmarshaller.Ephemeral, DCP>() {

    override fun createEphemeral(): Ephemeral {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toFinal(ephemeral: Ephemeral): DCP {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    data class Ephemeral (
            val dummy: String
    )
}
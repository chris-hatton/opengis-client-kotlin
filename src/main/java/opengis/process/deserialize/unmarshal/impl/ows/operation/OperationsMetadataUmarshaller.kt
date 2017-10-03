package opengis.process.deserialize.unmarshal.impl.ows.operation

import opengis.model.xml.ows.operation.Constraint
import opengis.model.xml.ows.operation.DCP
import opengis.model.xml.ows.operation.Operation
import opengis.model.xml.ows.operation.Parameter
import opengis.process.deserialize.unmarshal.ImmutableObjectUnmarshaller
import opengis.process.deserialize.unmarshal.ListUnmarshaller
import opengis.process.deserialize.unmarshal.Unmarshaller
import opengis.model.xml.ows.OperationsMetaData

/**
 * Created by Chris on 03/10/2017.
 */
object OperationsMetadataUmarshaller : ImmutableObjectUnmarshaller<OperationsMetadataUmarshaller.Ephemeral, OperationsMetaData>(
        Field( tag = Tag.DCP.string, unmarshaller = DCPUnmarshaller, store = { ephemeral, value -> ephemeral.dcp = value } ),
        Field( tag = Tag.PARAMETERS.string, unmarshaller = ListUnmarshaller<Parameter> ( elementTag = "Parameter" , elementUnmarshaller = TODO()  ), store = { ephemeral, value -> ephemeral.parameters  = value } ),
        Field( tag = Tag.CONSTRAINTS.string, unmarshaller = ListUnmarshaller<Constraint>( elementTag = "Constraint", elementUnmarshaller = ConstraintUnmarshaller ), store = { ephemeral, value -> ephemeral.constraints = value } )
) {

    enum class Tag(val string:String) {
        DCP        ("DCP"),
        PARAMETERS ("Parameters"),
        CONSTRAINTS("Constraints")
    }

    data class Ephemeral(
            var dcp         : DCP? = null,
            var parameters  : List<Parameter>  = mutableListOf(),
            var constraints : List<Constraint> = mutableListOf()
    )

    override fun createEphemeral() = Ephemeral()

    override fun toFinal(ephemeral: Ephemeral) = TODO()
}
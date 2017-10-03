package opengis.process.deserialize.unmarshal.impl

import opengis.model.xml.wfs.WfsCapabilities
import opengis.model.xml.ows.ServiceIdentification
import opengis.model.xml.ows.ServiceProvider
import opengis.model.xml.ows.operation.Operation
import opengis.process.deserialize.unmarshal.ImmutableObjectUnmarshaller
import opengis.process.deserialize.unmarshal.Unmarshaller
import opengis.process.deserialize.unmarshal.impl.ows.ServiceIdentificationUnmarshaller
import opengis.process.deserialize.unmarshal.impl.ows.ServiceProviderUnmarshaller

/**
 * Created by Chris on 03/10/2017.
 */
object WfsCapabilitiesUnmarshaller : ImmutableObjectUnmarshaller<WfsCapabilitiesUnmarshaller.Ephemeral, WfsCapabilities>(
        Field( tag = Tag.SERVICE_IDENTIFICATION.string, unmarshaller = ServiceIdentificationUnmarshaller, store = { ephemeral, value -> ephemeral.serviceIdentification = value } ),
        Field( tag = Tag.SERVICE_PROVIDER      .string, unmarshaller = ServiceProviderUnmarshaller,       store = { ephemeral, value -> ephemeral.serviceProvider       = value } )//,
        //Field( tag = Tag.OPERATIONS_METADATA   .string, unmarshaller = ListUnmarshaller<OperationUnmarshaller>(), store = { ephemeral, value -> ephemeral.operationsMetaData = value } )
) {

    enum class Tag(val string:String) {
        SERVICE_IDENTIFICATION("ServiceIdentification"),
        SERVICE_PROVIDER      ("ServiceProvider"),
        OPERATIONS_METADATA   ("OperationsMetaData"),
        FEATURE_TYPE_LIST     ("FeatureTypeList"),
        FILTER_CAPABILITIES   ("Filter_Capabilities")
    }

    data class Ephemeral(
            var serviceIdentification : ServiceIdentification? = null,
            var serviceProvider       : ServiceProvider?       = null,
            var operationsMetaData    : List<Operation>        = emptyList()//,
       // var featureTypes          : List<FeatureType>      = emptyList()
    )

    override fun createEphemeral() = Ephemeral()

    override fun toFinal(ephemeral: Ephemeral) = WfsCapabilities(
            updateSequence = 0,
            version = "1.0.0",
            serviceIdentification = ephemeral.serviceIdentification ?: throw Unmarshaller.Exception.ObjectIncomplete(missingTag = Tag.SERVICE_IDENTIFICATION.string),
            serviceProvider = ephemeral.serviceProvider ?: throw Unmarshaller.Exception.ObjectIncomplete(missingTag = Tag.SERVICE_PROVIDER.string),
            operationsMetaData = TODO()
    )
}
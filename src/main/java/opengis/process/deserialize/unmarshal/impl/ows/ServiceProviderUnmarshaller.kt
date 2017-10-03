package opengis.process.deserialize.unmarshal.impl.ows

import opengis.model.xml.ows.ServiceContact
import opengis.model.xml.ows.ServiceProvider
import opengis.process.deserialize.unmarshal.ImmutableObjectUnmarshaller
import opengis.process.deserialize.unmarshal.StringUnmarshaller
import opengis.process.deserialize.unmarshal.Unmarshaller

object ServiceProviderUnmarshaller : ImmutableObjectUnmarshaller<ServiceProviderUnmarshaller.Ephemeral, ServiceProvider>(
        Field( tag = Tag.PROVIDER.string,        unmarshaller = StringUnmarshaller,         store = { ephemeral, value -> ephemeral.providerName   = value } ),
        Field( tag = Tag.SERVICE_CONTACT.string, unmarshaller = ServiceContactUnmarshaller, store = { ephemeral, value -> ephemeral.serviceContact = value } )
) {
    enum class Tag(val string: String) {
        PROVIDER        ("ProviderName"),
        SERVICE_CONTACT ("ServiceContact")
    }

    data class Ephemeral(
        var providerName   : String = "",
        var serviceContact : ServiceContact? = null
    )

    override fun createEphemeral() = Ephemeral()

    override fun toFinal(ephemeral: Ephemeral) = ServiceProvider(
        providerName   = ephemeral.providerName,
        serviceContact = ephemeral.serviceContact ?: throw Unmarshaller.Exception.ObjectIncomplete(missingTag = Tag.SERVICE_CONTACT.string)
    )
}

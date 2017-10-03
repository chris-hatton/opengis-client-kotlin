package opengis.process.deserialize.unmarshal.impl.ows

import opengis.model.xml.ows.ServiceIdentification
import opengis.process.deserialize.unmarshal.ImmutableObjectUnmarshaller
import opengis.process.deserialize.unmarshal.StringUnmarshaller

object ServiceIdentificationUnmarshaller : ImmutableObjectUnmarshaller<ServiceIdentificationUnmarshaller.Ephemeral,ServiceIdentification>(
    Field( tag = "Title",              unmarshaller = StringUnmarshaller, store = { ephemeral, value -> ephemeral.title              = value } ),
    Field( tag = "Abstract",           unmarshaller = StringUnmarshaller, store = { ephemeral, value -> ephemeral.abstract           = value } ),
    Field( tag = "Keyword",            unmarshaller = StringUnmarshaller, store = { ephemeral, value -> ephemeral.keywords.add(value)        } ),
    Field( tag = "ServiceType",        unmarshaller = StringUnmarshaller, store = { ephemeral, value -> ephemeral.serviceType        = value } ),
    Field( tag = "ServiceTypeVersion", unmarshaller = StringUnmarshaller, store = { ephemeral, value -> ephemeral.serviceTypeVersion = value } ),
    Field( tag = "Fees",               unmarshaller = StringUnmarshaller, store = { ephemeral, value -> ephemeral.fees               = value } ),
    Field( tag = "AccessConstraints",  unmarshaller = StringUnmarshaller, store = { ephemeral, value -> ephemeral.accessConstraints  = value } )
) {

    data class Ephemeral(
        var title              : String = "",
        var abstract           : String = "",
        var keywords           : MutableList<String> = mutableListOf(),
        var serviceType        : String = "",
        var serviceTypeVersion : String = "",
        var fees               : String = "",
        var accessConstraints  : String = ""
    )

    override fun createEphemeral() = Ephemeral()

    override fun toFinal(ephemeral: Ephemeral) = ServiceIdentification(
        title              = ephemeral.title,
        abstract           = ephemeral.abstract,
        keywords           = ephemeral.keywords.toList(),
        serviceType        = ephemeral.serviceType,
        serviceTypeVersion = ephemeral.serviceTypeVersion,
        fees               = ephemeral.fees,
        accessConstraints  = ephemeral.accessConstraints
    )
}

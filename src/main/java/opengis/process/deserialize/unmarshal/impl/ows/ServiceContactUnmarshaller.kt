package opengis.process.deserialize.unmarshal.impl.ows

import opengis.model.xml.ows.ContactInfo
import opengis.model.xml.ows.ServiceContact
import opengis.process.deserialize.unmarshal.ImmutableObjectUnmarshaller
import opengis.process.deserialize.unmarshal.StringUnmarshaller
import opengis.process.deserialize.unmarshal.Unmarshaller

/**
 * Created by Chris on 02/10/2017.
 */
/*
individualName : String,
positionName   : String,
contactInfo    : ContactInfo
 */
object ServiceContactUnmarshaller : ImmutableObjectUnmarshaller<ServiceContactUnmarshaller.Ephemeral, ServiceContact>(
        Field( tag = "IndividualName", unmarshaller = StringUnmarshaller,      store = { ephemeral, value -> ephemeral.individualName = value } ),
        Field( tag = "PositionName",   unmarshaller = StringUnmarshaller,      store = { ephemeral, value -> ephemeral.positionName   = value } ),
        Field( tag = "ContactInfo",    unmarshaller = ContactInfoUnmarshaller, store = { ephemeral, value -> ephemeral.contactInfo    = value } )
        ) {

    data class Ephemeral(
            var individualName : String = "",
            var positionName   : String = "",
            var contactInfo    : ContactInfo? = null
    )

    override fun createEphemeral() = Ephemeral()

    override fun toFinal(ephemeral: Ephemeral) = ServiceContact(
        individualName = ephemeral.individualName,
        positionName   = ephemeral.positionName,
        contactInfo    = ephemeral.contactInfo ?: throw Unmarshaller.Exception.ObjectIncomplete( missingTag = "ContactInfo")
    )
}

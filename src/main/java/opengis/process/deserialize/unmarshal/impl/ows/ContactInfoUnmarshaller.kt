package opengis.process.deserialize.unmarshal.impl.ows

import opengis.model.xml.ows.Address
import opengis.model.xml.ows.ContactInfo
import opengis.model.xml.ows.Phone
import opengis.process.deserialize.unmarshal.ImmutableObjectUnmarshaller
import opengis.process.deserialize.unmarshal.Unmarshaller

/**
 * Created by Chris on 02/10/2017.
 */
object ContactInfoUnmarshaller : ImmutableObjectUnmarshaller<ContactInfoUnmarshaller.Ephemeral,ContactInfo>(
        Field( tag = Tag.PHONE.string,   unmarshaller = PhoneUnmarshaller,   store = { ephemeral, value -> ephemeral.phone   = value } ),
        Field( tag = Tag.ADDRESS.string, unmarshaller = AddressUnmarshaller, store = { ephemeral, value -> ephemeral.address = value } )
) {
    enum class Tag(val string:String) {
        PHONE  ("Phone"),
        ADDRESS("Address")
    }

    data class Ephemeral(
        var phone   : Phone?   = null,
        var address : Address? = null
    )

    override fun createEphemeral(): Ephemeral = Ephemeral()

    override fun toFinal(ephemeral: Ephemeral) = ContactInfo(
        phone   = ephemeral.phone   ?: throw Unmarshaller.Exception.ObjectIncomplete(missingTag = Tag.PHONE.string  ),
        address = ephemeral.address ?: throw Unmarshaller.Exception.ObjectIncomplete(missingTag = Tag.ADDRESS.string)
    )
}

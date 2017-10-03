package opengis.process.deserialize.unmarshal.impl.ows

import opengis.model.xml.ows.Address
import opengis.process.deserialize.unmarshal.ImmutableObjectUnmarshaller
import opengis.process.deserialize.unmarshal.StringUnmarshaller

/**
 * Created by Chris on 03/10/2017.
 */
object AddressUnmarshaller : ImmutableObjectUnmarshaller<AddressUnmarshaller.Ephemeral,Address>(
        Field( tag = Tag.DELIVERY_POINT     .string, unmarshaller = StringUnmarshaller, store = { ephemeral, value -> ephemeral.deliveryPoint      = value } ),
        Field( tag = Tag.CITY               .string, unmarshaller = StringUnmarshaller, store = { ephemeral, value -> ephemeral.city               = value } ),
        Field( tag = Tag.ADMINISTRATIVE_AREA.string, unmarshaller = StringUnmarshaller, store = { ephemeral, value -> ephemeral.administrativeArea = value } ),
        Field( tag = Tag.POSTAL_CODE        .string, unmarshaller = StringUnmarshaller, store = { ephemeral, value -> ephemeral.postalCode         = value } ),
        Field( tag = Tag.COUNTRY            .string, unmarshaller = StringUnmarshaller, store = { ephemeral, value -> ephemeral.country            = value } ),
        Field( tag = Tag.EMAIL_ADDRESS      .string, unmarshaller = StringUnmarshaller, store = { ephemeral, value -> ephemeral.emailAddress       = value } )
) {

    enum class Tag(val string:String) {
        DELIVERY_POINT     ("DeliveryPoint"),
        CITY               ("City"),
        ADMINISTRATIVE_AREA("AdministrativeArea"),
        POSTAL_CODE        ("PostalCode"),
        COUNTRY            ("Country"),
        EMAIL_ADDRESS      ("EmailAddress")
    }

    data class Ephemeral(
        var deliveryPoint      : String = "",
        var city               : String = "",
        var administrativeArea : String = "",
        var postalCode         : String = "",
        var country            : String = "",
        var emailAddress       : String = ""
    )

    override fun createEphemeral() = Ephemeral()

    override fun toFinal(ephemeral: Ephemeral) = Address(
        deliveryPoint      = ephemeral.deliveryPoint,
        city               = ephemeral.city,
        administrativeArea = ephemeral.administrativeArea,
        postalCode         = ephemeral.postalCode,
        country            = ephemeral.country,
        emailAddress       = ephemeral.emailAddress
    )
}
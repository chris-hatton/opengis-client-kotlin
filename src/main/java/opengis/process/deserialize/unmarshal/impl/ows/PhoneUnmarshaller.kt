package opengis.process.deserialize.unmarshal.impl.ows

import opengis.model.xml.ows.Phone
import opengis.process.deserialize.unmarshal.ImmutableObjectUnmarshaller
import opengis.process.deserialize.unmarshal.StringUnmarshaller

/**
 * Created by Chris on 03/10/2017.
 */
object PhoneUnmarshaller : ImmutableObjectUnmarshaller<PhoneUnmarshaller.Ephemeral, Phone>(
        Field( tag = Tag.VOICE.string,     unmarshaller = StringUnmarshaller, store = { ephemeral, value -> ephemeral.voice     = value } ),
        Field( tag = Tag.FACSIMILE.string, unmarshaller = StringUnmarshaller, store = { ephemeral, value -> ephemeral.facsimile = value } )
) {

    enum class Tag(val string:String) {
        VOICE    ("Voice"),
        FACSIMILE("Facsimile")
    }

    data class Ephemeral(
        var voice     : String = "",
        var facsimile : String = ""
    )

    override fun createEphemeral() = Ephemeral()

    override fun toFinal(ephemeral: Ephemeral) = Phone(
        voice     = ephemeral.voice,
        facsimile = ephemeral.facsimile
    )
}
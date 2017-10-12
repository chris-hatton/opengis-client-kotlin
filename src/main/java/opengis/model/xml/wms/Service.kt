package opengis.model.xml.wms

import com.sun.corba.se.pept.transport.ContactInfo
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Path

/**
 * General service metadata.
 *
 * @param name The WMT-defined name for this type of service.
 */
data class Service(
    @Element(name="Name"              ) var name     : String,
    @Element(name="Title"             ) var title    : String,
    @Element(name="Abstract"          ) var abstract : String,
    @ElementList(name="KeywordList", empty = true, entry="Keyword") var keywords : List<String>,
    @Element(name="OnlineResource"    ) var onlineResource : String,
    @Element(name="ContactInformation") var contactInfo    : ContactInfo,
    @Element(name="Fees"              ) var fees           : String,
    @Element(name="AccessConstraints" ) var string         : String,
    @Element(name="LayerLimit"        ) var layerLimit     : Int,
    @Element(name="MaxWidth"          ) var maxWidth       : Int,
    @Element(name="MaxHeight"         ) var maxHeight      : Int
) {
    /**
     * Information about a contact person for the service.
     */
    data class ContactInfo(

        @Path("ContactPersonPrimary")
        @Element(name="ContactPerson") var name : String,
        @Path("ContactPersonPrimary")
        @Element(name="ContactOrganisation") var organisation : String,

        @Element(name="ContactPosition"             ) var position,
        @Element(name="ContactAddress"              ) var address,
        @Element(name="ContactVoiceTelephone"       ) var telephone,
        @Element(name="ContactElectronicMailAddress") var emailAddress,
    )
}
package opengis.model.xml.ows

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name="ServiceContact")
data class ServiceContact(
        @field:Element(name="IndividualName", required = false ) var individualName : String?      = null,
        @field:Element(name="PositionName",   required = false ) var positionName   : String?      = null,
        @field:Element(name="ContactInfo",    required = false ) var contactInfo    : ContactInfo? = null,
        @field:Element(name="Role",           required = false ) var role           : String?      = null // TODO: Check spec
)
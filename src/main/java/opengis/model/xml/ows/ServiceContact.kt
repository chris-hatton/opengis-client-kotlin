package opengis.model.xml.ows

import org.simpleframework.xml.Element


data class ServiceContact(
    @field:Element(name="IndividualName") var individualName : String?      = null,
    @field:Element(name="PositionName"  ) var positionName   : String?      = null,
    @field:Element(name="ContactInfo"   ) var contactInfo    : ContactInfo? = null
)
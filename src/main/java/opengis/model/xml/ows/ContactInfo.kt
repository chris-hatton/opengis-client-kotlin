package opengis.model.xml.ows

import opengis.model.xml.ows.operation.OnlineResource
import org.simpleframework.xml.Element

/**
 * @param onlineResource
 * @param hoursOfService Time period (including time zone) when individuals can contact the organization or individual.
 * @param contactInstructions Supplemental instructions on how or when to contact the individual or organization.
 */
data class ContactInfo(
        @field:Element(name="Phone",               required = false) var phone               : Phone?          = null,
        @field:Element(name="Address",             required = false) var address             : Address?        = null,
        @field:Element(name="OnlineResource",      required = false) var onlineResource      : OnlineResource? = null,
        @field:Element(name="HoursOfService",      required = false) var hoursOfService      : String?         = null,
        @field:Element(name="ContactInstructions", required = false) var contactInstructions : String?         = null
)

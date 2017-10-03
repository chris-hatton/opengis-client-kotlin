package opengis.model.xml.ows

import org.simpleframework.xml.Element

data class ContactInfo(
    @field:Element(name="Phone"  ) var phone   : Phone?   = null,
    @field:Element(name="Address") var address : Address? = null
)
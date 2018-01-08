package opengis.model.xml.ows

import org.simpleframework.xml.Element


data class Address(
    @field:Element(name="DeliveryPoint", required = false         ) var deliveryPoint      : String? = null,
    @field:Element(name="City"                                    ) var city               : String? = null,
    @field:Element(name="AdministrativeArea"                      ) var administrativeArea : String? = null,
    @field:Element(name="PostalCode"                              ) var postalCode         : String? = null,
    @field:Element(name="Country"                                 ) var country            : String? = null,
    @field:Element(name="ElectronicMailAddress", required = false ) var emailAddress       : String? = null
)
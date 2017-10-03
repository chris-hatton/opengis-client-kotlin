package opengis.model.xml.ows

import org.simpleframework.xml.Element

/**
 * Created by Chris on 02/10/2017.
 */
/*
<ows:Address>
  <ows:DeliveryPoint/>
  <ows:City>Melbourne</ows:City>
  <ows:AdministrativeArea>Vic</ows:AdministrativeArea>
  <ows:PostalCode>3000</ows:PostalCode>
  <ows:Country>Australia</ows:Country>
  <ows:ElectronicMailAddress/>
</ows:Address>
 */
data class Address(
    @field:Element(name="DeliveryPoint", required = false         ) var deliveryPoint      : String? = null,
    @field:Element(name="City"                                    ) var city               : String? = null,
    @field:Element(name="AdministrativeArea"                      ) var administrativeArea : String? = null,
    @field:Element(name="PostalCode"                              ) var postalCode         : String? = null,
    @field:Element(name="Country"                                 ) var country            : String? = null,
    @field:Element(name="ElectronicMailAddress", required = false ) var emailAddress       : String? = null
)
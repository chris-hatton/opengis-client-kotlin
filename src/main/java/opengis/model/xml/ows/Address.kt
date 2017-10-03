package opengis.model.xml.ows

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
    val deliveryPoint      : String? = null,
    val city               : String,
    val administrativeArea : String,
    val postalCode         : String,
    val country            : String,
    val emailAddress       : String
)
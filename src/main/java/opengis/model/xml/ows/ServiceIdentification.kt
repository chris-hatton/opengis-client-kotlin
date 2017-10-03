package opengis.model.xml.ows

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

/**
 * Created by Chris on 02/10/2017.
 */
/*
  <ows:ServiceIdentification>
    <ows:Title>DELWP Web Feature Service</ows:Title>
    <ows:Abstract>This is the reference implementation of WFS 1.0.0 and WFS 1.1.0</ows:Abstract>
    <ows:Keywords>
      <ows:Keyword>WFS</ows:Keyword>
      <ows:Keyword>WMS</ows:Keyword>
      <ows:Keyword>GEOSERVER</ows:Keyword>
    </ows:Keywords>
    <ows:ServiceType>WFS</ows:ServiceType>
    <ows:ServiceTypeVersion>1.1.0</ows:ServiceTypeVersion>
    <ows:Fees>NONE</ows:Fees>
    <ows:AccessConstraints>NONE</ows:AccessConstraints>
  </ows:ServiceIdentification>
 */
data class ServiceIdentification(
    @field:Element    (name="Title"             ) var title              : String?       = null,
    @field:Element    (name="Abstract"          ) var abstract           : String?       = null,
    @field:ElementList(name="Keywords"          ) var keywords           : List<String>? = null,
    @field:Element    (name="ServiceType"       ) var serviceType        : String?       = null,
    @field:Element    (name="ServiceTypeVersion") var serviceTypeVersion : String?       = null,
    @field:Element    (name="Fees"              ) var fees               : String?       = null,
    @field:Element    (name="AccessConstraints" ) var accessConstraints  : String?       = null
)
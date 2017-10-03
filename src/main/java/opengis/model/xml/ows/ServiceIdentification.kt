package opengis.model.xml.ows

import org.simpleframework.xml.Element

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
    @Element val title              : String,
    @Element val abstract           : String,
    @Element val keywords           : List<String>,
    @Element val serviceType        : String,
    @Element val serviceTypeVersion : String,
    @Element val fees               : String,
    @Element val accessConstraints  : String
)
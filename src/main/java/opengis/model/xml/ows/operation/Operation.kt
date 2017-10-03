package opengis.model.xml.ows.operation

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList

/**
 * Created by Chris on 02/10/2017.
 */
/*
<ows:Operation name="GetFeature">
      <ows:DCP>
        <ows:HTTP>
          <ows:Get xlink:href="http://services.land.vic.gov.au/catalogue/publicproxy/guest/dv_geoserver/datavic/wfs"/>
          <ows:Post xlink:href="http://services.land.vic.gov.au/catalogue/publicproxy/guest/dv_geoserver/datavic/wfs"/>
        </ows:HTTP>
      </ows:DCP>
      <ows:Parameter name="resultType">
        <ows:Value>results</ows:Value>
        <ows:Value>hits</ows:Value>
      </ows:Parameter>
      <ows:Parameter name="outputFormat">
        ...
      </ows:Parameter>
      <ows:Constraint name="LocalTraverseXLinkScope">
        <ows:Value>2</ows:Value>
      </ows:Constraint>
    </ows:Operation>
    <ows:Operation name="GetGmlObject">
      <ows:DCP>
        <ows:HTTP>
          <ows:Get xlink:href="http://services.land.vic.gov.au/catalogue/publicproxy/guest/dv_geoserver/datavic/wfs"/>
          <ows:Post xlink:href="http://services.land.vic.gov.au/catalogue/publicproxy/guest/dv_geoserver/datavic/wfs"/>
        </ows:HTTP>
      </ows:DCP>
    </ows:Operation>
 */
data class Operation(
        @field:Attribute  ( name="name", required = false) var name        : String?           = null,
        @field:Element    ( name="DCP"        )            var DCP         : DCP?              = null,
        @field:ElementList( entry="Parameter" , inline = true, required = false ) var parameters  : List<Parameter>?  = null,
        @field:ElementList( entry="Constraint", inline = true, required = false ) var constraints : List<Constraint>? = null
)

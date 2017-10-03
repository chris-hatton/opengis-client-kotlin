package opengis.model.xml.ows.operation

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
    val DCP         : DCP,
    val parameters  : List<Parameter>,
    val constraints : List<Constraint>
)
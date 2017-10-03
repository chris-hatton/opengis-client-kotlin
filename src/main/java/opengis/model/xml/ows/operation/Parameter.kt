package opengis.model.xml.ows.operation

/**
 * Created by Chris on 02/10/2017.
 */
/*
<ows:Parameter name="outputFormat">
        <ows:Value>text/xml; subtype=gml/3.1.1</ows:Value>
        <ows:Value>DXF</ows:Value>
        <ows:Value>DXF-ZIP</ows:Value>
        <ows:Value>GML2</ows:Value>
        <ows:Value>KML</ows:Value>
        <ows:Value>SHAPE-ZIP</ows:Value>
        <ows:Value>application/gml+xml; version=3.2</ows:Value>
        <ows:Value>application/json</ows:Value>
        <ows:Value>application/vnd.google-earth.kml xml</ows:Value>
        <ows:Value>application/vnd.google-earth.kml+xml</ows:Value>
        <ows:Value>csv</ows:Value>
        <ows:Value>gml3</ows:Value>
        <ows:Value>gml32</ows:Value>
        <ows:Value>json</ows:Value>
        <ows:Value>text/xml; subtype=gml/2.1.2</ows:Value>
        <ows:Value>text/xml; subtype=gml/3.2</ows:Value>
      </ows:Parameter>
 */
data class Parameter(
        val name   : String,
        val values : List<String>
)
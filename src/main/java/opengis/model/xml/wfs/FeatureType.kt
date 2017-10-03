package opengis.model.xml.wfs

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

/**
 * Created by Chris on 03/10/2017.
 */
/*
<FeatureType xmlns:datavic="http://land.vic.gov.au/datavic">
      <Name>datavic:WATER_ISC2010_BANKFULL_WIDTH_R</Name>
      <Title>2010 Index of Stream Condition - Bank Full Width Reach polygon features</Title>
      <Abstract/>
      <ows:Keywords>
        <ows:Keyword>ANZVI0803005112</ows:Keyword>
        <ows:Keyword>WATER Rivers</ows:Keyword>
        <ows:Keyword>ISC</ows:Keyword>
        <ows:Keyword>LiDAR</ows:Keyword>
        <ows:Keyword>PHYSICAL FORM</ows:Keyword>
        <ows:Keyword>RIPARIAN</ows:Keyword>
        <ows:Keyword>ELEVATION</ows:Keyword>
      </ows:Keywords>
      <DefaultSRS>urn:x-ogc:def:crs:EPSG:4283</DefaultSRS>
      <OtherSRS>urn:x-ogc:def:crs:EPSG:3111</OtherSRS>
      <OtherSRS>urn:x-ogc:def:crs:EPSG:3857</OtherSRS>
      <OtherSRS>urn:x-ogc:def:crs:EPSG:4326</OtherSRS>
      <OtherSRS>urn:x-ogc:def:crs:EPSG:28355</OtherSRS>
      <OtherSRS>urn:x-ogc:def:crs:EPSG:28354</OtherSRS>
      <OtherSRS>urn:x-ogc:def:crs:EPSG:900913</OtherSRS>
      <ows:WGS84BoundingBox>
        <ows:LowerCorner>140.888196123417 -39.032198371076845</ows:LowerCorner>
        <ows:UpperCorner>149.796963189816 -33.97378749712523</ows:UpperCorner>
      </ows:WGS84BoundingBox>
    </FeatureType>
 */
@Root(strict=false)
data class FeatureType(
    @field:Element(name="Name")                                      var name        : String?           = null,
    @field:Element(name="Title")                                     var title       : String?           = null,
    @field:Element(name="Abstract",required=false)                   var abstract    : String?           = null,
    @field:ElementList(name="Keywords",entry="Keyword",empty = true) var keywords    : List<String>?     = null,
    @field:Element(name="DefaultSRS")                                var defaultSrs  : String?           = null,
    @field:ElementList(name="OtherSRS", inline=true,required=false)  var otherSrs    : List<String>?     = null,
    @field:Element(name="WGS84BoundingBox")                          var boundingBox : WGS84BoundingBox? = null
)
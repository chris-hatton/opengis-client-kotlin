package opengis.model.transport.xml.wfs

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root


@Root(strict=false)
data class FeatureType(
    @field:Element    (name="Name")                                  var name        : String?           = null,
    @field:Element    (name="Title")                                 var title       : String?           = null,
    @field:Element    (name="Abstract",required=false)               var abstract    : String?           = null,
    @field:ElementList(name="Keywords",entry="Keyword",empty = true) var keywords    : List<String>?     = null,
    @field:Element    (name="DefaultSRS")                            var defaultSrs  : String?           = null,
    @field:ElementList(name="OtherSRS", inline=true,required=false)  var otherSrs    : List<String>?     = null,
    @field:Element    (name="WGS84BoundingBox")                      var boundingBox : WGS84BoundingBox? = null
)
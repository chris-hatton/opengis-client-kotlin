package opengis.model.xml.wfs

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * Created by Chris on 03/10/2017.
 */
/*
<ows:WGS84BoundingBox>
        <ows:LowerCorner>140.888196123417 -39.032198371076845</ows:LowerCorner>
        <ows:UpperCorner>149.796963189816 -33.97378749712523</ows:UpperCorner>
      </ows:WGS84BoundingBox>
 */
@Root(name="WGS84BoundingBox")
data class WGS84BoundingBox(
    @field:Element(name="LowerCorner") var lowerCorner : String? = null,
    @field:Element(name="UpperCorner") var upperCorner : String? = null
)
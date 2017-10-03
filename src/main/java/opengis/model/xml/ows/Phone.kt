package opengis.model.xml.ows

import org.simpleframework.xml.Element

/**
 * Created by Chris on 02/10/2017.
 */
/*
<ows:Phone>
  <ows:Voice>136186</ows:Voice>
  <ows:Facsimile/>
</ows:Phone>
 */
data class Phone(
    @field:Element(name="Voice"                      ) var voice     : String? = null,
    @field:Element(name="Facsimile", required = false) var facsimile : String? = null
)
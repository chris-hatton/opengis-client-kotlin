package opengis.model.xml.ows

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
    val voice     : String,
    val facsimile : String
)
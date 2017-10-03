package opengis.model.xml.ows.operation

import okhttp3.HttpUrl
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.ElementMap

/**
 * Created by Chris on 02/10/2017.
 */
/*
<ows:HTTP>
  <ows:Get xlink:href="http://services.land.vic.gov.au/catalogue/publicproxy/guest/dv_geoserver/datavic/wfs"/>
  <ows:Post xlink:href="http://services.land.vic.gov.au/catalogue/publicproxy/guest/dv_geoserver/datavic/wfs"/>
</ows:HTTP>
 */
data class DCP(
    @field:Element(name="HTTP") var httpUrls: HTTP? = null
)
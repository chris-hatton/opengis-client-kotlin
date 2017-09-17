package opengis.model.request.wfs

import geojson.Feature
import geojson.FeatureCollection

/**
 * Created by Chris on 24/08/2017.
 */
class GetFeature : WebFeatureServiceRequest<FeatureCollection>() {
    override val requestIdentifier: String = "GetFeature"
}
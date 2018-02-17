package opengis.model.app.request.wfs

import geojson.FeatureCollection

/**
 * Created by Chris on 24/08/2017.
 */
class GetFeature : WebFeatureServiceRequest<FeatureCollection>() {
    override val requestIdentifier: String = "GetFeature"
}
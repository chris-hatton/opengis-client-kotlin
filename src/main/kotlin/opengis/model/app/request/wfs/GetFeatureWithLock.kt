package opengis.model.app.request.wfs

import geojson.FeatureCollection

/**
 * Created by Chris on 24/08/2017.
 */
class GetFeatureWithLock : WebFeatureServiceRequest<FeatureCollection>() {
    override val requestIdentifier: String = "GetFeatureWithLock"
}
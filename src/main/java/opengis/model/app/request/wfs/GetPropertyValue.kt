package opengis.model.app.request.wfs

/**
 * Created by Chris on 24/08/2017.
 */
class GetPropertyValue : WebFeatureServiceRequest<String>() {
    override val requestIdentifier: String = "GetPropertyValue"
}
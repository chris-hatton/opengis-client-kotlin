package opengis.model.request.wfs

/**
 * Created by Chris on 24/08/2017.
 */
class LockFeature : WebFeatureServiceRequest<Any>() {
    override val requestIdentifier: String = "LockFeature"
}
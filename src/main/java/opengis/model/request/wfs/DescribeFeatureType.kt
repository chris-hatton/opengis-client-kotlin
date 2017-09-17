package opengis.model.request.wfs

import kimage.model.Image
import kimage.model.pixel.RGB

/**
 * Created by Chris on 24/08/2017.
 */
class DescribeFeatureType : WebFeatureServiceRequest<Image<RGB>>() {
    override val requestIdentifier: String = "DescribeFeatureType"
}
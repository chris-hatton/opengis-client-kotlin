package opengis.model.app.request.wfs

import kimage.model.Image
import kimage.model.pixel.RGB

class DescribeFeatureType : WebFeatureServiceRequest<Image<RGB>>() {
    override val requestIdentifier: String = "DescribeFeatureType"
}

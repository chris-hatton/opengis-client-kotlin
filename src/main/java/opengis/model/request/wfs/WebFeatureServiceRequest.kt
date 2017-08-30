package opengis.model.request.wfs

import opengis.model.request.OpenGisRequest

abstract class WebFeatureServiceRequest : OpenGisRequest() {

    final override val serviceIdentifier : String = "WFS"
}
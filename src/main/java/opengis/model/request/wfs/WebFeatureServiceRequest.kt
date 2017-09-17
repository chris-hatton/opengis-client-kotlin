package opengis.model.request.wfs

import opengis.model.request.OpenGisRequest

abstract class WebFeatureServiceRequest<Result> : OpenGisRequest<Result>() {

    final override val serviceIdentifier : String = "WFS"
}
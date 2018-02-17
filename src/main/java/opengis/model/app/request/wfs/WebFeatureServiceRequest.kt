package opengis.model.app.request.wfs

import opengis.model.app.request.OpenGisRequest

abstract class WebFeatureServiceRequest<Result> : OpenGisRequest<Result>() {

    final override val serviceIdentifier : String = "WFS"
}
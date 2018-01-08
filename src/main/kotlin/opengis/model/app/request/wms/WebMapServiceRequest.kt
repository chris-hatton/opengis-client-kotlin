package opengis.model.app.request.wms

import opengis.model.app.request.OpenGisRequest

abstract class WebMapServiceRequest<Result> : OpenGisRequest<Result>() {
    final override val serviceIdentifier : String = "WMS"
}

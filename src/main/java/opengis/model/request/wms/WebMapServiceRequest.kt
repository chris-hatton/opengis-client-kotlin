package opengis.model.request.wms

import opengis.model.request.OpenGisRequest

abstract class WebMapServiceRequest<Result> : OpenGisRequest<Result>() {
    final override val serviceIdentifier : String = "WMS"
}

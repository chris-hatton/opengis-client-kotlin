package opengis.model.request.wms

import opengis.model.request.OpenGisRequest

abstract class WebMapServiceRequest : OpenGisRequest() {
    final override val serviceIdentifier : String = "WMS"
}

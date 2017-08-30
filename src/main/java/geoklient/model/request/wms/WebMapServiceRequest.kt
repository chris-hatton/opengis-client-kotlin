package geoklient.model.request.wms

import geoklient.model.request.OpenGisRequest

abstract class WebMapServiceRequest : OpenGisRequest() {
    final override val serviceIdentifier : String = "WMS"
}

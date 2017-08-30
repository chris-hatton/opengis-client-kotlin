package geoklient.model.request.wfs

import geoklient.model.request.OpenGisRequest

abstract class WebFeatureServiceRequest : OpenGisRequest() {

    final override val serviceIdentifier : String = "WFS"
}
package opengis.model.app.request.wcs

import opengis.model.app.request.OpenGisRequest

abstract class WebCoverageServiceRequest<Result> : OpenGisRequest<Result>() {

    final override val serviceIdentifier : String = "WCS"
}
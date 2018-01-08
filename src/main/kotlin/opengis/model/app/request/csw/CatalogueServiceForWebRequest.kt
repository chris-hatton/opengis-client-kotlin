package opengis.model.app.request.csw

import opengis.model.app.request.OpenGisRequest

/**
 * Created by Chris on 24/11/2017.
 */
abstract class CatalogueServiceForWebRequest<Result> : OpenGisRequest<Result>() {

    final override val serviceIdentifier : String = "WFS"
}
package opengis.model.app.request.wmts

import opengis.model.app.request.OpenGisRequest

/**
 * Created by Chris on 10/08/2017.
 */
abstract class WebMapTileServiceRequest<Result> : OpenGisRequest<Result>( version = WebMapTileServiceRequest.Companion.version ) {

    companion object {
        val version = "1.0.0"
    }

    final override val serviceIdentifier : String = "WMTS"
}
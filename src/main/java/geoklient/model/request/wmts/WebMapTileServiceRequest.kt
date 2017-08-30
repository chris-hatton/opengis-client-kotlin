package geoklient.model.request.wmts

import geoklient.model.request.OpenGisRequest

/**
 * Created by Chris on 10/08/2017.
 */
abstract class WebMapTileServiceRequest : OpenGisRequest( version = WebMapTileServiceRequest.version ) {

    companion object {
        val version = "1.0.0"
    }

    final override val serviceIdentifier : String = "WMTS"
}
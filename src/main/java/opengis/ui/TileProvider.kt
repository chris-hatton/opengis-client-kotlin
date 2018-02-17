package opengis.ui

import opengis.model.app.request.OpenGisRequest
import opengis.process.Callback
import opengis.process.OpenGisRequestProcessor
import opengis.process.execute
import opengis.process.projection.Tile


/**
 * Abstraction of a TileProvider for an Android MapView,
 * one which sources image data from an OpenGIS service.
 *
 * Concrete implementations of this inc
 */
abstract class TileProvider<TileRequest : OpenGisRequest<ByteArray>> internal constructor(
        private val requestProcessor: OpenGisRequestProcessor,
        private val baseRequest : TileRequest
) {

    sealed class Exception : kotlin.Exception() {
        class BadTileData(private val data: ByteArray) : Exception() {
            val dataAsString: String get() = String(bytes = data)
        }
    }

    abstract fun getTileRequest(baseRequest: TileRequest, tile: Tile.Google): TileRequest

    fun getTile( tile: Tile.Google, callback: Callback<ByteArray> ) {

        val tileRequest: TileRequest = getTileRequest(baseRequest, tile)
        requestProcessor.execute( request = tileRequest, callback = callback)
    }
}

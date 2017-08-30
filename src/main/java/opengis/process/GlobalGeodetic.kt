package opengis.process

import geojson.BoundingBox

/**
 * 
 *       """
 * TMS Global Geodetic Profile
 * ---------------------------
 * 
 * Functions necessary for generation of global tiles in Plate Carre projection,
 * EPSG:4326, "unprojected profile".
 * 
 * Such tiles are compatible with Google Earth (as any other EPSG:4326 rasters)
 * and you can overlay the tiles on top of OpenLayers base map.
 * 
 * Pixel and tile coordinates are in TMS notation (origin [0,0] in bottom-left).
 * 
 * What coordinate conversions do we need for TMS Global Geodetic tiles?
 * 
 * Global Geodetic tiles are using geodetic coordinates (latitude,longitude)
 * directly as planar coordinates XY (it is also called Unprojected or Plate
 * Carre). We need only scaling to pixel pyramid and cutting to tiles.
 * Pyramid has on top level two tiles, so it is not square but rectangle.
 * Area [-180,-90,180,90] is scaled to 512x256 pixels.
 * TMS has coordinate origin (for pixels and tiles) in bottom-left corner.
 * Rasters are in EPSG:4326 and therefore are compatible with Google Earth.
 * 
 * LatLon      <->      Pixels      <->     Tiles
 * 
 * WGS84 coordinates   Pixels in pyramid  Tiles in pyramid
 * lat/lon         XY pixels Z zoom      XYZ from TMS
 * EPSG:4326
 *  .----.                ----
 * /      \     <->    /--------/    <->      TMS
 * \      /         /--------------/
 *  -----        /--------------------/
 * WMS, KML    Web Clients, Google Earth  TileMapService
 * """
 */
class GlobalGeodetic( val tileSize: Int = 256 ) {

    /** Converts lat/lon to pixel coordinates in given zoom of the EPSG:4326 pyramid */
    fun LatLonToPixels(lat: Double, lon: Double, zoom: Int) : GlobalMercator.PointPixels {
        val res : Double = 180 / 256.0 / Math.pow(2.0,zoom.toDouble())
        val px : Double = (180 + lat) / res
        val py : Double = (90 + lon) / res
        return GlobalMercator.PointPixels(px.toInt(), py.toInt())
    }

    /** Returns coordinates of the tile covering region in pixel coordinates */
    fun PixelsToTile(px: Int, py: Int) : GlobalMercator.Tile {
        val tx = ( Math.ceil( px / tileSize.toDouble() ) - 1 ).toInt()
        val ty = ( Math.ceil( py / tileSize.toDouble() ) - 1 ).toInt()
        return GlobalMercator.Tile(tx, ty)
    }
    
    /** Resolution (arc/pixel) for given zoom level (measured at Equator) */
    fun Resolution(zoom: Int ) : Double {
        return 180 / 256.0 / Math.pow(2.0, zoom.toDouble() )
        //return 180 / float( 1 << (8+zoom) )
    }

    /** Returns bounds of the given tile */
    fun TileBounds(tx: Int, ty: Int, zoom: Int ) : BoundingBox {
        val res : Double = 180 / 256.0 / Math.pow(2.0,zoom.toDouble())
        return BoundingBox(
            tx*256*res - 180,
            ty*256*res - 90,
            (tx+1)*256*res - 180,
            (ty+1)*256*res - 90
        )
    }
}
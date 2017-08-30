package opengis.process

import geojson.BoundingBox

typealias PointDegrees = geojson.geometry.impl.Point

/**
 * Created by Chris on 12/08/2017.
 */
/**
 * TMS Global Mercator Profile
 * ---------------------------
 *
 * Functions necessary for generation of tiles in Spherical Mercator projection,
 * EPSG:900913 (EPSG:gOOglE, Google Maps Global Mercator), EPSG:3785, OSGEO:41001.
 *
 * Such tiles are compatible with Google Maps, Microsoft Virtual Earth, Yahoo Maps,
 * UK Ordnance Survey OpenSpace API, ...
 * and you can overlay them on top of base maps of those web mapping applications.
 *
 * Pixel and tile coordinates are in TMS notation (origin [0,0] in bottom-left).
 *
 * What coordinate conversions do we need for TMS Global Mercator tiles::
 *
 * LatLon      <->       Meters      <->     Pixels    <->       Tile
 *
 * WGS84 coordinates   Spherical Mercator  Pixels in pyramid  Tiles in pyramid
 * lat/lon            XY in metres     XY pixels Z zoom      XYZ from TMS
 * EPSG:4326           EPSG:900913
 *  .----.              ---------               --                TMS
 * /      \     <->     |       |     <->     /----/    <->      Google
 * \      /             |       |           /--------/          QuadTree
 *  -----               ---------         /------------/
 * KML, public         WebMapService         Web Clients      TileMapService
 *
 * What is the coordinate extent of Earth in EPSG:900913?
 *
 * [-20037508.342789244, -20037508.342789244, 20037508.342789244, 20037508.342789244]
 * Constant 20037508.342789244 comes from the circumference of the Earth in meters,
 * which is 40 thousand kilometers, the coordinate origin is in the middle of extent.
 * In fact you can calculate the constant as: 2 * math.pi * 6378137 / 2.0
 * $ echo 180 85 | gdaltransform -s_srs EPSG:4326 -t_srs EPSG:900913
 * Polar areas with abs(latitude) bigger then 85.05112878 are clipped off.
 *
 * What are zoom level constants (pixels/meter) for pyramid with EPSG:900913?
 *
 * whole region is on top of pyramid (zoom=0) covered by 256x256 pixels tile,
 * every lower zoom level resolution is always divided by two
 * initialResolution = 20037508.342789244 * 2 / 256 = 156543.03392804062
 *
 * What is the difference between TMS and Google Maps/QuadTree tile name convention?
 *
 * The tile raster itself is the same (equal extent, projection, pixel size),
 * there is just different identification of the same raster tile.
 * Tiles in TMS are counted from [0,0] in the bottom-left corner, id is XYZ.
 * Google placed the origin [0,0] to the top-left corner, reference is XYZ.
 * Microsoft is referencing tiles by a QuadTree name, defined on the website:
 * http://msdn2.microsoft.com/en-us/library/bb259689.aspx
 *
 * The lat/lon coordinates are using WGS84 datum, yeh?
 *
 * Yes, all lat/lon we are mentioning should use WGS84 Geodetic Datum.
 * Well, the web clients like Google Maps are projecting those coordinates by
 * Spherical Mercator, so in fact lat/lon coordinates on sphere are treated as if
 * the were on the WGS84 ellipsoid.
 *
 * From MSDN documentation:
 * To simplify the calculations, we use the spherical form of projection, not
 * the ellipsoidal form. Since the projection is used only for map display,
 * and not for displaying numeric coordinates, we don't need the extra precision
 * of an ellipsoidal projection. The spherical projection causes approximately
 * 0.33 percent scale distortion in the Y direction, which is not visually noticable.
 *
 * How do I create a raster in EPSG:900913 and convert coordinates with PROJ.4?
 *
 * You can use standard GIS tools like gdalwarp, cs2cs or gdaltransform.
 * All of the tools supports -t_srs 'epsg:900913'.
 *
 * For other GIS programs check the exact definition of the projection:
 * More info at http://spatialreference.org/ref/user/google-projection/
 * The same projection is degined as EPSG:3785. WKT definition is in the official
 * EPSG database.
 *
 * Proj4 Text:
 * +proj=merc +a=6378137 +b=6378137 +lat_ts=0.0 +lon_0=0.0 +x_0=0.0 +y_0=0
 * +k=1.0 +units=m +nadgrids=@null +no_defs
 *
 * Human readable WKT format of EPGS:900913:
 * PROJCS["Google Maps Global Mercator",
 * GEOGCS["WGS 84",
 * DATUM["WGS_1984",
 * SPHEROID["WGS 84",6378137,298.2572235630016,
 * AUTHORITY["EPSG","7030"]],
 * AUTHORITY["EPSG","6326"]],
 * PRIMEM["Greenwich",0],
 * UNIT["degree",0.0174532925199433],
 * AUTHORITY["EPSG","4326"]],
 * PROJECTION["Mercator_1SP"],
 * PARAMETER["central_meridian",0],
 * PARAMETER["scale_factor",1],
 * PARAMETER["false_easting",0],
 * PARAMETER["false_northing",0],
 * UNIT["metre",1,
 * AUTHORITY["EPSG","9001"]]]
 */
class GlobalMercator( val tileSize: Int = 256 ) {

    data class PointMeters(val x: Double, val y: Double) // EPSG:900913 co-ordinates
    data class PointPixels(val x: Int, val y: Int)
    data class Tile( val x: Int, val y: Int )

    // 156543.03392804062 for tileSize 256 pixels
    private val initialResolution : Double = 2 * Math.PI * 6378137 / tileSize

    // 20037508.342789244
    private val originShift : Double = 2 * Math.PI * 6378137 / 2.0

    /** Converts given lat/lon in WGS84 Datum to XY in Spherical Mercator EPSG:900913 */
    fun LatLonToMeters( lat: Double, lon: Double ) : PointMeters {

        val mx = lon * originShift / 180.0
        var my = Math.log( Math.tan((90 + lat) * Math.PI / 360.0 )) / (Math.PI / 180.0)

        my = my * originShift / 180.0
        return PointMeters(mx, my)
    }

    /** Converts XY point from Spherical Mercator EPSG:900913 to lat/lon in WGS84 Datum */
    fun MetersToLatLon( point: PointMeters) : PointDegrees {
        val lon = (point.x / originShift) * 180.0
        var lat = (point.y / originShift) * 180.0

        lat = 180 / Math.PI * (2 * Math.atan( Math.exp( lat * Math.PI / 180.0)) - Math.PI / 2.0)
        return PointDegrees(lat, lon)
    }

    /** Converts pixel coordinates in given zoom level of pyramid to EPSG:900913 */
    fun PixelsToMeters(point: PointPixels, zoom: Int) : PointMeters {
        val res = Resolution( zoom )
        val mx = point.x * res - originShift
        val my = point.y * res - originShift
        return PointMeters(mx, my)

    }

    /** Converts EPSG:900913 to pyramid pixel coordinates in given zoom level */
    fun MetersToPixels(point: PointMeters, zoom: Int ): PointPixels {
        val res = Resolution( zoom )
        val px = (point.x + originShift) / res
        val py = (point.y + originShift) / res
        return PointPixels(px.toInt(), py.toInt())
    }

    /** Returns a tile covering region in given pixel coordinates */
    fun PixelsToTile( point: PointPixels) : Tile {
        val tx = ( Math.ceil( point.x / tileSize.toDouble() ) - 1 ).toInt()
        val ty = ( Math.ceil( point.y / tileSize.toDouble() ) - 1 ).toInt()
        return Tile(tx, ty)
    }

    /** Move the origin of pixel coordinates to top-left corner */
    fun PixelsToRaster(point: PointPixels, zoom: Int ) : PointPixels {
        val mapSize = tileSize shl zoom
        return PointPixels(point.x, mapSize - point.y)
    }

    /** Returns tile for given mercator coordinates */
    fun MetersToTile(point: PointMeters, zoom: Int ): Tile {
        val pointPixels: PointPixels = MetersToPixels( point, zoom)
        return PixelsToTile( pointPixels )
    }

    data class BoundingBoxMeters(val southWest: PointMeters, val northEast: PointMeters)

    /** Returns bounds of the given tile in EPSG:900913 coordinates */
    fun TileBounds(tile: Tile, zoom: Int ) : BoundingBoxMeters {
        val min : PointMeters = PixelsToMeters( PointPixels( tile.x * tileSize, tile.y * tileSize), zoom )
        val max : PointMeters = PixelsToMeters( PointPixels( (tile.x+1) * tileSize, (tile.y+1) * tileSize), zoom )
        return BoundingBoxMeters( southWest = min, northEast = max )
    }



    /** "Returns bounds of the given tile in latutude/longitude using WGS84 datum" */
    fun TileLatLonBounds(tile: Tile, zoom: Int ) : BoundingBox {
        val bounds = TileBounds( tile, zoom)
        val minPoint : PointDegrees = MetersToLatLon( bounds.southWest )
        val maxPoint : PointDegrees = MetersToLatLon( bounds.northEast )

        return BoundingBox( southWest = minPoint, northEast = maxPoint )
    }

    /** "Resolution (meters/pixel) for given zoom level (measured at Equator)" */
    fun Resolution( zoom: Int ) : Double {
        // return (2 * math.pi * 6378137) / (self.tileSize * 2**zoom)
        return initialResolution / Math.pow(2.0,zoom.toDouble())
    }

    /** Maximal scaledown zoom of the pyramid closest to the pixelSize. */
    fun ZoomForPixelSize(pixelSize: Int ): Int {
        for( i in 1 until 30) {
            if( pixelSize > Resolution(i) ) {
                return if( i!=0 ) i-1 else 0  // We don't want to scale up
            }
        }
        return 0
    }

    /** Converts TMS tile coordinates to Google Tile coordinates */
    fun GoogleTile(tile: Tile, zoom: Int ) : Tile {
        // coordinate origin is moved from bottom-left to top-left corner of the extent
        return Tile(tile.x, (Math.pow(2.0,zoom.toDouble()).toInt() - 1) - tile.y)
    }

    /** "Converts TMS tile coordinates to Microsoft QuadTree" */
    fun QuadTree(tile: Tile, zoom: Int ) : String {
        var quadKey = ""
        val ty : Int = (Math.pow(2.0,zoom.toDouble()).toInt() - 1) - tile.y
        for( i in zoom downTo 0) {
            var digit = 0
            val mask : Int = 1 shl (i - 1)
            if( (tile.x and mask) != 0) {
                digit += 1
            }
            if( (ty and mask) != 0 ) {
                digit += 2
            }
            quadKey += digit.toString()
        }

        return quadKey
    }
}

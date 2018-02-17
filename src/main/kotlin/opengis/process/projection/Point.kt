package opengis.process.projection

/**
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
 *
 * ======================================================================
 *
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
sealed class Point<Unit>(val x:Unit, val y:Unit) {

    companion object {
        internal val tileSize = 256

        // 156543.03392804062 for tileSize 256 pixels
        private val initialResolution : Double = 2 * Math.PI * 6378137 / tileSize

        // 20037508.342789244
        private val originShift : Double = 2 * Math.PI * 6378137 / 2.0

        /** "mercatorResolution (meters/pixel) for given zoom level (measured at Equator)" */
        internal fun mercatorResolution(zoom: Int ) : Double = initialResolution / Math.pow(2.0,zoom.toDouble())

        /** Resolution (arc/pixel) for given zoom level (measured at Equator) */
        internal fun geodeticResolution(zoom: Int ) : Double = 180 / 256.0 / Math.pow(2.0, zoom.toDouble() )

        /** Maximal scaledown zoom of the pyramid closest to the pixelSize. */
        fun zoomForMercatorPixelSize(pixelSize: Int ): Int {
            for( i in 1 until 30) {
                if( pixelSize > mercatorResolution(i) ) {
                    return if( i!=0 ) i-1 else 0  // We don't want to scale up
                }
            }
            return 0
        }
    }

    class Degrees(latitude: Double, longitude: Double) : Point<Double>(latitude, longitude) {

        val latitude  : Double get() = x
        val longitude : Double get() = y

        /** Converts given lat/lon in WGS84 Datum to XY in Spherical Mercator EPSG:900913 */
        override fun toMeters(): Meters {
            val mx = longitude * originShift / 180.0
            var my = Math.log( Math.tan((90 + latitude) * Math.PI / 360.0 )) / (Math.PI / 180.0)

            my = my * originShift / 180.0
            return Meters(mx, my)
        }

        override fun toDegrees(): Degrees = this

        /** Converts lat/lon to pixel coordinates in given zoom of the EPSG:4326 pyramid */
        override fun toPixels(zoom: Int): Pixels {
            val res : Double = 180 / 256.0 / Math.pow(2.0,zoom.toDouble())
            val px : Double = (180 + latitude) / res
            val py : Double = (90 + longitude) / res
            return Pixels(px.toInt(), py.toInt(), zoom)
        }

        /** Return the offset from the origin of the Tile, in pixels, for the location of the lat, lng at the given zoom. */
        override fun toRaster(zoom: Int): Raster {
            val meters : Meters = toMeters()
            val pixels : Pixels = meters.toPixels(zoom)
            return pixels.toRaster(zoom)
        }

        /** Return the indexes of the Google Tile within which the lat, lng occurs at the given zoom. */
        fun toGoogleTile(zoom: Int) : Tile.Google = toMeters().toTmsTile(zoom).toGoogle()
    }

    /**
     * Mercator meters (EPSG:900913 co-ordinates)
     */
    class Meters(x: Double, y: Double) : Point<Double>(x,y) {
        override fun toMeters(): Meters = this

        /** Converts XY point from Spherical Mercator EPSG:900913 to lat/lon in WGS84 Datum */
        override fun toDegrees(): Degrees {
            val lon = (x / originShift) * 180.0
            var lat = (y / originShift) * 180.0

            lat = 180 / Math.PI * (2 * Math.atan( Math.exp( lat * Math.PI / 180.0)) - Math.PI / 2.0)
            return Degrees(lat, lon)
        }

        /** Converts EPSG:900913 to pyramid pixel coordinates in given zoom level */
        override fun toPixels(zoom: Int): Pixels {
            val res = mercatorResolution( zoom )
            val px = (x + originShift) / res
            val py = (y + originShift) / res
            return Pixels(px.toInt(), py.toInt(), zoom)
        }

        override fun toRaster(zoom: Int): Raster {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        /** Returns tile for this mercator coordinate */
        fun toTmsTile(zoom: Int) : Tile.TMS = toPixels(zoom).toTmsTile()
    }

    class Pixels(x: Int, y: Int, val zoom: Int) : Point<Int>(x,y) {

        /** Converts pixel coordinates in given zoom level of pyramid to EPSG:900913 */
        override fun toMeters(): Meters {
            val res = mercatorResolution( zoom )
            val mx = x * res - originShift
            val my = y * res - originShift
            return Meters(mx, my)
        }

        override fun toDegrees(): Degrees = toMeters().toDegrees()

        override fun toPixels(zoom: Int): Pixels = if( zoom == this.zoom ) {
            this
        } else {
            val degrees = toDegrees()
            degrees.toPixels(zoom)
        }

        /** Move the origin of pixel coordinates to top-left corner */
        override fun toRaster(zoom: Int): Raster {
            val zoomedPixels = this.toPixels(zoom)
            val mapSize = tileSize shl zoom
            return Raster(zoomedPixels.x, mapSize - zoomedPixels.y)
        }

        /** Returns coordinates of the tile covering region in pixel coordinates */
        fun toTmsTile() : Tile.TMS {
            val tx = (Math.ceil(x / tileSize.toDouble()) - 1).toInt()
            val ty = (Math.ceil(y / tileSize.toDouble()) - 1).toInt()
            return Tile.TMS(tx, ty, zoom)
        }
    }
    class Raster(x: Int, y: Int) : Point<Int>(x,y) {
        override fun toMeters(): Meters {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun toDegrees(): Degrees {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun toPixels(zoom: Int): Pixels {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun toRaster(zoom: Int): Raster = this
    }

    abstract fun toMeters()  : Meters
    abstract fun toDegrees() : Degrees
    abstract fun toPixels(zoom: Int) : Pixels
    abstract fun toRaster(zoom: Int) : Raster

    fun toGeoJsonPoint() : geojson.geometry.impl.Point {
        val degrees = toDegrees()
        return geojson.geometry.impl.Point(degrees.longitude, degrees.latitude)
    }
}

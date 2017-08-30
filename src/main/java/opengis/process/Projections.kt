package opengis.process

/**
 * Created by Chris on 12/08/2017.
 */
class Projections {

    companion object {
        fun main( args: Array<String> ) {
/*
            fun Usage(s = ""):
                    print "Usage: globalmaptiles.py [-profile 'mercator'|'geodetic'] zoomlevel lat lon [latmax lonmax]"
            print
            if s:
            print s
                    print
            print "This utility prints for given WGS84 lat/lon coordinates (or bounding box) the list of tiles"
            print "covering specified area. Tiles are in the given 'profile' (default is Google Maps 'mercator')"
            print "and in the given pyramid 'zoomlevel'."
            print "For each tile several information is printed including bonding box in EPSG:900913 and WGS84."
            sys.exit(1)

            profile = 'mercator'
            zoomlevel = None
            lat, lon, latmax, lonmax = None, None, None, None
            boundingbox = False

            argv = sys.argv
            i = 1
            while i < len(argv):
            arg = argv[i]

            if arg == '-profile':
            i = i + 1
            profile = argv[i]

            if zoomlevel is None:
            zoomlevel = int(argv[i])
            elif lat is None:
            lat = float(argv[i])
            elif lon is None:
            lon = float(argv[i])
            elif latmax is None:
            latmax = float(argv[i])
            elif lonmax is None:
            lonmax = float(argv[i])
            else:
            Usage("ERROR: Too many parameters")

            i = i + 1

            if profile != 'mercator':
            Usage("ERROR: Sorry, given profile is not implemented yet.")

            if zoomlevel == None or lat == None or lon == None:
            Usage("ERROR: Specify at least 'zoomlevel', 'lat' and 'lon'.")
            if latmax is not None and lonmax is None:
            Usage("ERROR: Both 'latmax' and 'lonmax' must be given.")

            if latmax != None and lonmax != None:
            if latmax < lat:
            Usage("ERROR: 'latmax' must be bigger then 'lat'")
            if lonmax < lon:
            Usage("ERROR: 'lonmax' must be bigger then 'lon'")
            boundingbox = (lon, lat, lonmax, latmax)

            tz = zoomlevel
            mercator = GlobalMercator()

            mx, my = mercator.LatLonToMeters( lat, lon )
            print "Spherical Mercator (ESPG:900913) coordinates for lat/lon: "
            print (mx, my)
            tminx, tminy = mercator.MetersToTile( mx, my, tz )

            if boundingbox:
            mx, my = mercator.LatLonToMeters( latmax, lonmax )
            print "Spherical Mercator (ESPG:900913) cooridnate for maxlat/maxlon: "
            print (mx, my)
            tmaxx, tmaxy = mercator.MetersToTile( mx, my, tz )
            else:
            tmaxx, tmaxy = tminx, tminy

            for ty in range(tminy, tmaxy+1):
            for tx in range(tminx, tmaxx+1):
            tilefilename = "%s/%s/%s" % (tz, tx, ty)
            print tilefilename, "( TileMapService: z / x / y )"

            gx, gy = mercator.GoogleTile(tx, ty, tz)
            print "\tGoogle:", gx, gy
            quadkey = mercator.QuadTree(tx, ty, tz)
            print "\tQuadkey:", quadkey, '(',int(quadkey, 4),')'
            bounds = mercator.TileBounds( tx, ty, tz)
            print
            print "\tEPSG:900913 Extent: ", bounds
            wgsbounds = mercator.TileLatLonBounds( tx, ty, tz)
            print "\tWGS84 Extent:", wgsbounds
            print "\tgdalwarp -ts 256 256 -te %s %s %s %s %s %s_%s_%s.tif" % (
            bounds[0], bounds[1], bounds[2], bounds[3], "<your-raster-file-in-epsg900913.ext>", tz, tx, ty)
            print
            */
        }
    }
}
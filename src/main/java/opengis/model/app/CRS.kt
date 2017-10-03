package opengis.model.app

/**
 * Kotlin sealed-class model of the various representations of
 * Coordinate Reference System allowable in the OpenGIS specification.
 *
 * OpenGIS standards Coordinate Reference Systems
 */
sealed class CRS(val nameSpace: String) {

    enum class Namespace {
        CRS,
        EPSG
    }

    /**
     * Layer Coordinate Reference Systems
     */
    class Layer( nameSpace: String, val name: String ) : CRS( nameSpace ) {

        constructor( identifier: String ) : this(
            nameSpace = identifier.substringBefore(separator),
            name      = identifier.substringAfter (separator)
        )

        companion object {

            private val separator = ':'

            // Two different registrations of the same Geodetic / Ellipsoid Sphere coordinate system
            val WGS84    : Layer = Layer(nameSpace = Namespace.CRS.toString(), name = "84")
            val EPSG4326 : Layer = Layer(nameSpace = Namespace.EPSG.toString(), name = "4326")



            // Two different registrations fo the same Spherical Mercator projection (used by web-maps)


            val default = WGS84
        }

        override fun toString(): String = nameSpace + ':' + name
    }

    sealed class Vertical( nameSpace: String ) : CRS( nameSpace ) {
        data class Label( val label: String )
        data class URL  ( val url: URL)
    }
}
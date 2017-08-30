package geoklient.model

/**
 * Kotlin sealed-class model of the various representations of
 * Elevation allowable in the OpenGIS specification.
 *
 * Ref: Section 6.7.5, section 7.3.3.13.
 */
sealed class Elevation( val crs: CRS.Vertical? = null ) {
    class Value( val value: Double, crs: CRS.Vertical? = null ) : Elevation( crs )
    class Range( val lowerValue: Double, val upperValue: Double, crs: CRS.Vertical? = null ) : Elevation( crs )
}

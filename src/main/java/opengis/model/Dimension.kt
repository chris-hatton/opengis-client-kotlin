package opengis.model

/**
 * Ref: Section C.2
 */
data class Dimension<out T>(
    val name           : String,           // Attribute stating name of dimensional axis.
    val units          : String,           // Attribute indicating units of dimensional axis.
    val unitSymbol     : String?  = null,  // Attribute specifying symbol.
    val default        : T?       = null,  // Attribute indicating default value that will be used if GetMap request does not specify a value. If attribute is absent, then shall respond with a service exception if request does not include a value for that dimension.
    val multipleValues : Boolean? = false, // Boolean attribute indicating whether multiple values of the dimension may be requested. 0 (or “false”) = single values only; 1 (or “true”) = multiple values permitted. Default = 0.
    val nearestValue   : Boolean? = false, // Boolean attribute indicating whether nearest value of the dimension will be returned in response to a request for a nearby value. 0 (or “false”) = request value(s) must correspond exactly to declared extent value(s); 1 (or “true”) = request values may be approximate. Default = 0.
    val current        : Boolean? = false, // Boolean attribute valid only for temporal extents (i.e. if attribute name="time"). This attribute, if it either 1 or “true”, indicates (a) that temporal data are normally kept current and (b) that the request parameter TIME may include the keyword “current” instead of an ending value (see C.4.1). Default = 0.
    val extent         : String            // Text content indicating available value(s) for dimension.
)

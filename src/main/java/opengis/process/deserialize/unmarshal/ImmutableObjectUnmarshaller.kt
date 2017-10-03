package opengis.process.deserialize.unmarshal

import org.xmlpull.v1.XmlPullParser

abstract class ImmutableObjectUnmarshaller<M,T>(val fields: List<Field<M,*>> ) : Unmarshaller<T> {

    /** Convenience constructor allowing a literal-list of fields to be specified. */
    constructor( vararg fields: Field<M,*> ) : this( fields.toList() )

    sealed class Exception : kotlin.Exception() {
        data class NoUnmarshaller( val tag: String ) : Exception()
    }

    data class Field<in Ephemeral,Value>(val tag: String, val unmarshaller: Unmarshaller<Value>, val store: (Ephemeral, Value)->Unit ) {
        internal fun pipe(ephemeral: Ephemeral, parser: XmlPullParser) {
            val fieldValue : Value = unmarshaller.unmarshal(parser)
            store(ephemeral,fieldValue)
        }
    }

    /** Create a short-lived, mutable object into which the unordered XML tags can be unmarshalled */
    abstract fun createEphemeral() : M

    /** After all tags have been un-marshalled, convert the short-lived, mutable object into it's final immutable form. */
    abstract fun toFinal( ephemeral: M ) : T

    @Synchronized
    override fun unmarshal(parser: XmlPullParser): T {

        val ephemeral = createEphemeral()

        while (parser.next() != XmlPullParser.END_TAG) {
            when(parser.eventType) {
                XmlPullParser.START_TAG -> {
                    val tag = parser.name
                    val field = fields.firstOrNull { it.tag == tag } ?: throw Exception.NoUnmarshaller(tag)
                    field.pipe(ephemeral,parser)
                }
                else -> {}  // Ignore
            }
        }

        return toFinal(ephemeral)
    }
}

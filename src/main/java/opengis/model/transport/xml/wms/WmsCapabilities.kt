package opengis.model.transport.xml.wms

import opengis.model.transport.xml.ows.operation.DCPType
import opengis.model.transport.xml.ows.operation.OnlineResource
import opengis.model.transport.xml.ows.operation.URL
import org.simpleframework.xml.*

/**
 * A `WMS_Capabilities` document is returned in response to a GetCapabilities request made on a WMS.
 */
@Root(strict=false, name = "WMS_Capabilities")
class WmsCapabilities(
        @field:Element(name="Service"    ) var service    : Service?    = null,
        @field:Element(name="Capability" ) var capability : Capability? = null
) {
    /**
     * General service metadata.
     *
     * @param name The WMT-defined name for this type of service.
     */
    @Root(name="Service")
    data class Service(
            @field:Element(name="Name"              ) var name     : String? = null,
            @field:Element(name="Title"             ) var title    : String? = null,
            @field:Element(name="Abstract"          ) var abstract : String? = null,
            @field:ElementList(name="KeywordList", empty = true, entry="Keyword", required = false) var keywords : List<String>? = null,
            @field:Element(name="OnlineResource", required = false ) var onlineResource : String? = null,
            @field:Element(name="ContactInformation") var contact        : Contact? = null,
            @field:Element(name="Fees"              ) var fees           : String? = null,
            @field:Element(name="AccessConstraints" ) var string         : String? = null,
            @field:Element(name="LayerLimit", required = false ) var layerLimit     : Int? = null,
            @field:Element(name="MaxWidth",   required = false ) var maxWidth       : Int? = null,
            @field:Element(name="MaxHeight",  required = false ) var maxHeight      : Int? = null
    ) {
        /**
         * Information about a contact person for the service.
         */
        @Root(name="Contact")
        data class Contact(
                @field:Path("ContactPersonPrimary")
            @field:Element(name="ContactPerson") var name : String? = null,
                @field:Path("ContactPersonPrimary")
            @field:Element(name="ContactOrganization") var organization : String? = null,

                @field:Element(name="ContactPosition"             ) var position     : String? = null,
                @field:Element(name="ContactAddress"              ) var address      : Address? = null,
                @field:Element(name="ContactVoiceTelephone"       ) var telephone    : String? = null,
                @field:Element(name="ContactFacsimileTelephone", required = false ) var fax : String? = null, // Not in xsd
                @field:Element(name="ContactElectronicMailAddress") var emailAddress : String? = null
        ) {
            @Root(name="Address")
            data class Address(
                @field:Element(name="AddressType"     ) var addressType     : String? = null,
                @field:Element(name="Address"         ) var address         : String? = null,
                @field:Element(name="City"            ) var city            : String? = null,
                @field:Element(name="StateOrProvince" ) var stateOrProvince : String? = null,
                @field:Element(name="PostCode"        ) var postcode        : String? = null,
                @field:Element(name="Country"         ) var country         : String? = null
            )
        }
    }

    /**
     * A Capability lists available request types, how exceptions may be
     * reported, and whether any extended capabilities are defined.
     * It also includes an optional list of map layers available from this
     * server.
     */
    @Root(name="Capability", strict = false)
    data class Capability(

        /*
         * Ideally, the name of each request capability should not require hard-coding here; each
         * named element should be parsed as a capability.  This is a short-cut for now.
         * TODO: Write a custom Converter to handle this.
         */
        @field:Path("Request") @field:Element(name="GetCapabilities") var getCapabilitiesRequestCapability : OperationType? = null,
        @field:Path("Request") @field:Element(name="GetMap"         ) var getMapRequestCapability          : OperationType? = null,
        @field:Path("Request") @field:Element(name="GetFeatureInfo" ) var getFeatureInfoRequestCapability  : OperationType? = null,

        // TODO: Handle '_ExtendedCapabilities' from schema

        @field:Element(name="Exception" ) var exception : Exception? = null,
        @field:Element(name="Layer"     ) var layer     : Layer? = null
    ) {

        /**
         * For each operation offered by the server, list the available output
         * formats and the online resource.
         */
        @Root(name="Request", strict = false)
        data class OperationType(
            @field:ElementList( entry = "Format",  inline = true, empty = true ) var formats : List<String>?  = null,
            @field:ElementList( entry = "DCPType", inline = true, empty = true ) var dcpType : List<DCPType>? = null

            // TODO: Handle '_ExtendedOperation' from schema
        )

        /**
         * An Exception element indicates which error-reporting formats are supported.
         */
        @Root(name="Exception")
        data class Exception(
            @field:ElementList( entry="Format", inline = true, empty = true ) var formats : List<String>? = null
        )

        /**
         * @param name                  The Name is typically for machine-to-machine communication.
         * @param title                 The Title is for informative display to a human.
         * @param abstract              The abstract is a longer narrative description of an object.
         * @param keywords              List of keywords or keyword phrases to help catalog searching.
         * @param crs                   Identifier for a single Coordinate Reference System (CRS).
         * @param geographicBoundingBox Indicates the limits of the enclosing rectangle in longitude and latitude decimal degrees.
         * @param dataURL               A Map Server may use DataURL offer a link to the underlying data represented by a particular layer.
         * @param featureListURL        A Map Server may use FeatureListURL to point to a list of the features represented in a Layer.
         * @param minScaleDenominator   Minimum scale denominator for which it is appropriate to display this layer.
         * @param maxScaleDenominator   Maximum scale denominator for which it is appropriate to display this layer.
         */
        @Root(name="Layer")
        data class Layer(
                @field:Element      (name = "Name",                     required=false)                                  var name                  : String?             = null,
                @field:Element      (name = "Title", required = true)                                                    var title                 : String?             = null,
                @field:Element      (name = "Abstract",                 required=false)                                  var abstract              : String?             = null,
                @field:ElementList  (name = "KeywordList",              empty = true, entry="Keyword", required = false) var keywords              : List<String>?       = null,
                @field:ElementList  (entry = "CRS",                     empty = true, inline = true, required = false)   var crss                  : List<String>?       = null,
                @field:Element      (name = "EX_GeographicBoundingBox", required = false)                                var geographicBoundingBox : EXBoundingBox?      = null,
                @field:ElementList  (entry = "BoundingBox",             empty = true, inline = true, required = false)   var boundingBoxes         : List<BoundingBox>?  = null,
                @field:ElementList  (entry = "Dimension",               empty = true, inline = true, required = false)   var dimensions            : List<Dimension>?    = null,
                @field:Element      (name = "Attribution",              required = false)                                var attribution           : Attribution?             = null,
                @field:ElementList  (entry = "AuthorityURL",            empty = true, inline = true, required = false)   var authorityURLs         : List<AuthorityURL>? = null,
                @field:ElementList  (entry = "Identifier",              empty = true, inline = true, required = false)   var identifiers           : List<String>?       = null,
                @field:ElementList  (entry = "MetadataURL",             empty = true, inline = true, required = false)   var metadataURLs          : List<MetadataURL>?  = null,
                @field:ElementList  (entry = "DataURL",                 empty = true, inline = true, required = false)   var dataURLs              : List<URL>?          = null,
                @field:ElementList  (entry = "FeatureListURL",          empty = true, inline = true, required = false)   var featureListURLs       : List<URL>?          = null,
                @field:ElementList  (entry = "Style",                   empty = true, inline = true, required = false)   var styles                : List<Style>?        = null,
                @field:Element      (name = "MinScaleDenominator",      required = false)                                var minScaleDenominator   : Double?             = null,
                @field:Element      (name = "MaxScaleDenominator",      required = false)                                var maxScaleDenominator   : Double?             = null,
                @field:ElementList  (entry = "Layer",                   empty = true, inline = true, required = false)  var layers                : List<Layer>?        = null,

                @field:Attribute(name = "queryable"  , required = false) var queryable   : Boolean    = false,
                @field:Attribute(name = "cascaded"   , required = false) var cascaded    : Int        = 0,
                @field:Attribute(name = "opaque"     , required = false) var opaque      : Boolean    = false,
                @field:Attribute(name = "noSubsets"  , required = false) var noSubsets   : Boolean    = false,
                @field:Attribute(name = "fixedWidth" , required = false) var fixedWidth  : Int        = 0,
                @field:Attribute(name = "fixedHeight", required = false) var fixedHeight : Int        = 0
        ) {
            /**
             * The EX_GeographicBoundingBox attributes indicate the limits of the enclosing
             * rectangle in longitude and latitude decimal degrees.
             */
            @Root(name="EX_GeographicBoundingBox")
            data class EXBoundingBox(
                @field:Element(name="westBoundLongitude") var westBoundLongitude : Double? = null,
                @field:Element(name="eastBoundLongitude") var eastBoundLongitude : Double? = null,
                @field:Element(name="southBoundLatitude") var southBoundLatitude : Double? = null,
                @field:Element(name="northBoundLatitude") var northBoundLatitude : Double? = null
            )

            /**
             * The BoundingBox attributes indicate the limits of the bounding box
             * in units of the specified coordinate reference system.
             */
            @Root(name="BoundingBox")
            data class BoundingBox(
                @field:Attribute(name="CRS" , required = true ) var CRS  : String? = null,
                @field:Attribute(name="minx", required = true ) var minx : Double? = null,
                @field:Attribute(name="miny", required = true ) var miny : Double? = null,
                @field:Attribute(name="maxx", required = true ) var maxx : Double? = null,
                @field:Attribute(name="maxy", required = true ) var maxy : Double? = null,
                @field:Attribute(name="resx", required = false) var resx : Double? = null,
                @field:Attribute(name="resy", required = false) var resy : Double? = null
            )

            /**
             * The Dimension element declares the existence of a dimension and indicates what
             * values along a dimension are valid.
             */
            @Root(name="Dimension")
            data class Dimension(
                @field:Attribute(name="name"          , required = true  ) var name           : String?  = null,
                @field:Attribute(name="units"         , required = true  ) var units          : String?  = null,
                @field:Attribute(name="unitSymbol"    , required = false ) var unitSymbol     : String?  = null,
                @field:Attribute(name="default"       , required = false ) var default        : String?  = null,
                @field:Attribute(name="multipleValues", required = false ) var multipleValues : Boolean? = null,
                @field:Attribute(name="nearestValue"  , required = false ) var nearestValue   : Boolean? = null,
                @field:Attribute(name="current"       , required = false ) var current        : Boolean? = null
            )

            /**
             * Attribution indicates the provider of a Layer or collection of Layers.
             * The provider's URL, descriptive title string, and/or logo image URL
             * may be supplied.  Client applications may choose to display one or
             * more of these items.  A format element indicates the MIME type of
             * the logo image located at LogoURL.  The logo image's width and height
             * assist client applications in laying out space to display the logo.
             */
            @Root(name="Attribution")
            data class Attribution(
                    @field:Element(name="Title",          required = false) var title          : String?         = null,
                    @field:Element(name="OnlineResource", required = false) var onlineResource : OnlineResource? = null,
                    @field:Element(name="LogoURL",        required = false) var logoUrl        : LogoURL?        = null
            )

            /**
             * LogoURL is undocumented in the OpenGIS WMS 1.3.0 schema.
             */
            @Root(name="LogoURL")
            data class LogoURL(
                    @field:Element  (name="Format",         required = true) var format         : String?         = null,
                    @field:Element  (name="OnlineResource", required = true) var onlineResource : OnlineResource? = null,
                    @field:Attribute(name="width",          required = true) var width          : Int?            = null,
                    @field:Attribute(name="height",         required = true) var height         : Int?            = null
            )

            /**
             * A Map Server may use zero or more Identifier elements to list ID
             * numbers or labels defined by a particular Authority.  For example,
             * the Global Change Master Directory (gcmd.gsfc.nasa.gov) defines a
             * DIF_ID label for every dataset.  The authority name and explanatory
             * URL are defined in a separate AuthorityURL element, which may be
             * defined once and inherited by subsidiary layers.  Identifiers
             * themselves are not inherited.
             */
            @Root(name="AuthorityURL")
            data class AuthorityURL(
                    @field:Element  (name="OnlineResource", required = true) var onlineResource : OnlineResource? = null,
                    @field:Attribute(name="name",           required = true) var name           : String?         = null
            )

            /**
             * A Map Server may use zero or more MetadataURL elements to offer
             * detailed, standardized metadata about the data underneath a
             * particular layer. The type attribute indicates the standard to which
             * the metadata complies.  The format element indicates how the metadata is structured.
             *
             * @param format An available format's MIME type.
             */
            @Root(name="MetadataURL")
            data class MetadataURL (
                    @field:Element  (name="Format",         required = true) var format         : String?         = null,
                    @field:Element  (name="OnlineResource", required = true) var onlineResource : OnlineResource? = null,
                    @field:Attribute(name="type",           required = true) var type : String? = null
            )

            @Root(name="LegendURL")
            data class LegendURL (
                    @field:Element  (name="Format",         required = true) var format         : String?         = null,
                    @field:Element  (name="OnlineResource", required = true) var onlineResource : OnlineResource? = null,
                    @field:Attribute(name="width",          required = true) var width  : Int? = null,
                    @field:Attribute(name="height",         required = true) var height : Int? = null
            )

            /**
             * A Style element lists the name by which a style is requested and a
             * human-readable title for pick lists, optionally (and ideally)
             * provides a human-readable description, and optionally gives a style
             * URL.
             */
            @Root(name="Style")
            data class Style(
                    @field:Element     (name="Name",          required = true )            var name          : String?    = null,
                    @field:Element     (name="Title",         required = false)            var title         : String?    = null,
                    @field:Element     (name="Abstract",      required = false)            var abstract      : String?    = null,
                    @field:ElementList (entry = "LegendURL",  empty = true, inline = true) var legendUrls    : List<LegendURL>? = null,
                    @field:Element     (name="StyleSheetURL", required = false)            var styleSheetUrl : URL?       = null,
                    @field:Element     (name="StyleURL",      required = false)            var styleUrl      : URL?       = null
            )
        }
    }
}
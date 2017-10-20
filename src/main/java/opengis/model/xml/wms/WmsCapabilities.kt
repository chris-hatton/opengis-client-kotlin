package opengis.model.xml.wms

import opengis.model.xml.ows.operation.DCPType
import org.simpleframework.xml.*

/**
 * A WMS_Capabilities document is returned in response to a GetCapabilities request made on a WMS.
 */
@Root(strict=false, name = "WMS_Capabilities")
class WmsCapabilities(
        //@field:Attribute var updateSequence : Int         = 0,
        //@field:Attribute var version        : String      = "",
        @field:Element(name="Service"    ) var service    : Service?    = null,
        @field:Element(name="Capability" ) var capability : Capability? = null
) {
    /**
     * General service metadata.
     *
     * @param name The WMT-defined name for this type of service.
     */
    data class Service(
        @field:Element(name="Name"              ) var name     : String? = null,
        @field:Element(name="Title"             ) var title    : String? = null,
        @field:Element(name="Abstract"          ) var abstract : String? = null,
        @field:ElementList(name="KeywordList", empty = true, entry="Keyword") var keywords : List<String>? = null,
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
     * Created by Chris on 06/10/2017.
     */
    data class Capability(

        /*
         * Ideally, the name of each request capability should not require hard-coding here; each
         * named element should be parsed as a capability.  This is a short-cut for now.
         * TODO: Write a custom Converter to handle this.
         */
        @field:Path("Request") @field:Element(name="GetCapabilities") var getCapabilitiesRequestCapability : Request? = null,
        @field:Path("Request") @field:Element(name="GetMap"         ) var getMapRequestCapability          : Request? = null,
        @field:Path("Request") @field:Element(name="GetFeatureInfo" ) var getFeatureInfoRequestCapability  : Request? = null,

        @field:Element(name="Exception" ) var exception : Exception? = null,
        @field:Element(name="Layer"     ) var layer     : Layer? = null
    ) {


        data class Request(
            @field:ElementList(entry="Format",inline = true) var formats : List<String>? = null,
            @field:Element(name = "DCPType") var dcpType : DCPType? = null
        )

        data class Exception(
            @field:ElementList(entry="Format",inline = true) var formats : List<String>? = null
        )

        data class Layer(
            @field:Element(name = "Title"       ) var title        : String?       = null,
            @field:Element(name = "CRS"         ) var crs          : String?       = null,
            @field:Element(name = "AuthorityURL") var authorityUrl : AuthorityUrl? = null,

            /**
             * Nested list of zero or more map Layers offered by this server.
             */
            @field:ElementList(entry="Layer",inline = true) var layer : List<Layer>? = null
        ) {
            data class AuthorityUrl(
                @field:Attribute(name = "name"          ) var name  : String? = null,
                @field:Element  (name = "OnlineResource") var title : String? = null
            )

            data class Layer(
                @field:Element(name = "Name",                     required=false) var name                  : String? = null,
                @field:Element(name = "Title")                                    var title                 : String? = null,
                @field:Element(name = "Abstract",                 required=false) var abstract              : String? = null,
                @field:ElementList(name="KeywordList", empty = true, entry="Keyword") var keywords : List<String>? = null,
                @field:Element(name = "CRS",                      required=false) var crs                   : String? = null,
                @field:Element(name = "EX_GeographicBoundingBox", required=false) var geographicBoundingBox : EXBoundingBox? = null,
                @field:Element(name = "BoundingBox",              required=false) var boundingBox           : BoundingBox?   = null,
                @field:Element(name = "Dimension",                required=false) var dimension             : Dimension? = null,
                @field:Element(name = "Attribution",              required=false) var attribution           : String? = null,
                @field:Element(name = "AuthorityURL",             required=false) var authorityURL          : AuthorityUrl? = null,
                @field:Element(name = "Identifier",               required=false) var identifier            : String? = null,
                @field:Element(name = "MetadataURL",              required=false) var metadataURL           : String? = null,
                @field:Element(name = "DataURL",                  required=false) var dataURL               : String? = null,
                @field:Element(name = "FeatureListURL",           required=false) var featureListURL        : String? = null,
                @field:Element(name = "Style",                    required=false) var style                 : String? = null,
                @field:Element(name = "MinScaleDenominator",      required=false) var minScaleDenominator   : String? = null,
                @field:Element(name = "MaxScaleDenominator",      required=false) var maxScaleDenominator   : String? = null,
                @field:Element(name = "Layer",                    required=false) var layer                 : String? = null,

                @field:Attribute(name = "queryable"  ) var queryable   : Boolean = false,
                @field:Attribute(name = "cascaded"   ) var cascaded    : Int = 0,
                @field:Attribute(name = "opaque"     ) var opaque      : Boolean = false,
                @field:Attribute(name = "noSubsets"  ) var noSubsets   : Boolean = false,
                @field:Attribute(name = "fixedWidth" ) var fixedWidth  : Int = 0,
                @field:Attribute(name = "fixedHeight") var fixedHeight : Int = 0
            ) {
                /**
                 * The EX_GeographicBoundingBox attributes indicate the limits of the enclosing
                 * rectangle in longitude and latitude decimal degrees.
                 */
                @Root(name="EX_GeographicBoundingBox")
                data class EXBoundingBox(
                    @field:Element(name="WestBoundLongitude") var westBoundLongitude : Int,
                    @field:Element(name="EastBoundLongitude") var eastBoundLongitude : Int,
                    @field:Element(name="SouthBoundLatitude") var southBoundLatitude : Int,
                    @field:Element(name="NorthBoundLatitude") var northBoundLatitude : Int
                )

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

                @Root(name="Dimension")
                data class Dimension(
                    @field:Attribute(name="Name"          , required = true  ) var name           : String?  = null,
                    @field:Attribute(name="Units"         , required = true  ) var units          : String?  = null,
                    @field:Attribute(name="UnitSymbol"    , required = false ) var unitSymbol     : String?  = null,
                    @field:Attribute(name="Default"       , required = false ) var default        : String?  = null,
                    @field:Attribute(name="MultipleValues", required = false ) var multipleValues : Boolean? = null,
                    @field:Attribute(name="NearestValue"  , required = false ) var nearestValue   : Boolean? = null,
                    @field:Attribute(name="Current"       , required = false ) var current        : Boolean? = null
                )

                @Root(name="Attribution")
                data class Attribution(
                        /*
                        <element ref="wms:Title" minOccurs="0"/>
                <element ref="wms:OnlineResource" minOccurs="0"/>
                <element ref="wms:LogoURL" minOccurs="0"/>
                */
                )

                @Root(name="LogoURL")
                data class LogoURL(
                        /*
                        <complexType>
                <sequence>
                <element ref="wms:Format"/>
                <element ref="wms:OnlineResource"/>
                </sequence>
                <attribute name="width" type="positiveInteger"/>
                <attribute name="height" type="positiveInteger"/>
                </complexType>
                */
                )
            }
        }
    }
}
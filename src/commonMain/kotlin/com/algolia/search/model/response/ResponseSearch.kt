package com.algolia.search.model.response

import com.algolia.search.endpoint.EndpointSearch
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.QueryID
import com.algolia.search.model.insights.InsightsEvent
import com.algolia.search.model.search.*
import com.algolia.search.model.settings.Settings
import com.algolia.search.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject


@Serializable
public data class ResponseSearch(
    /**
     * The hits returned by the search. Hits are ordered according to the ranking or sorting of the index being queried.
     * Hits are made of the schemaless JSON objects that you stored in the index.
     */
    @SerialName(KeyHits) val hitsOrNull: List<Hit>? = null,
    /**
     * The number of hits matched by the query.
     */
    @SerialName(KeyNbHits) val nbHitsOrNull: Int? = null,
    /**
     * Index of the current page (zero-based). See the page search parameter.
     * Not returned if you use offset/length for pagination.
     */
    @SerialName(KeyPage) val pageOrNull: Int? = null,
    /**
     * The maximum number of hits returned per page. See the hitsPerPage search parameter.
     * Not returned if you use offset & length for pagination.
     */
    @SerialName(KeyHitsPerPage) val hitsPerPageOrNull: Int? = null,
    /**
     * Array of userData object. Only returned if at least one query rule containing a custom userData
     * consequence was applied.
     */
    @SerialName(KeyUserData) val userDataOrNull: List<JsonObject>? = null,
    /**
     * The number of returned pages. Calculation is based on the total number of hits (nbHits) divided by the number of
     * hits per page (hitsPerPage), rounded up to the nearest integer.
     * Not returned if you use offset & length for pagination.
     */
    @SerialName(KeyNbPages) val nbPagesOrNull: Int? = null,
    /**
     * Time the server took to process the request, in milliseconds. This does not include network time.
     */
    @SerialName(KeyProcessingTimeMS) val processingTimeMSOrNull: Long? = null,
    /**
     * Whether the nbHits is exhaustive (true) or approximate (false). An approximation is done when the query takes
     * more than 50ms to be processed (this can happen when using complex filters on millions on records).
     * See the related [discussion][https://www.algolia.com/doc/faq/index-configuration/my-facet-and-hit-counts-are-not-accurate/]
     */
    @SerialName(KeyExhaustiveNbHits) val exhaustiveNbHitsOrNull: Boolean? = null,
    /**
     * Whether the facet count is exhaustive (true) or approximate (false).
     * See the related [discussion][https://www.algolia.com/doc/faq/index-configuration/my-facet-and-hit-counts-are-not-accurate/].
     */
    @SerialName(KeyExhaustiveFacetsCount) val exhaustiveFacetsCountOrNull: Boolean? = null,
    /**
     * An echo of the query text. See the [Query.query] search parameter.
     */
    @SerialName(KeyQuery) val queryOrNull: String? = null,
    /**
     * A markup text indicating which parts of the original query have been removed in order to retrieve a non-empty
     * result set.
     * The removed parts are surrounded by <em> tags.
     * Only returned when [Query.removeWordsIfNoResults] or [Settings.removeWordsIfNoResults] is set to
     * [RemoveWordIfNoResults.LastWords] or [RemoveWordIfNoResults.FirstWords].
     */
    @SerialName(KeyQueryAfterRemoval) val queryAfterRemovalOrNull: String? = null,
    /**
     * A url-encoded string of all [Query] parameters.
     */
    @SerialName(KeyParams) val paramsOrNull: String? = null,
    /**
     * Used to return warnings about the query.
     */
    @SerialName(KeyMessage) val messageOrNull: String? = null,
    /**
     * The computed geo location.
     * Only returned when [Query.aroundLatLngViaIP] or [Query.aroundLatLng] is set.
     */
    @SerialName(KeyAroundLatLng) @Serializable(KSerializerPoint::class) val aroundLatLngOrNull: Point? = null,
    /**
     * The automatically computed radius. For legacy reasons, this parameter is a string and not an integer.
     * Only returned for geo queries without an explicitly specified [Query.aroundRadius].
     */
    @SerialName(KeyAutomaticRadius) val automaticRadiusOrNull: Float? = null,
    /**
     * Actual host name of the server that processed the request. Our DNS supports automatic failover and load
     * balancing, so this may differ from the host name used in the request.
     * Returned only if [Query.getRankingInfo] is set to true.
     */
    @SerialName(KeyServerUsed) val serverUsedOrNull: String? = null,
    /**
     * Index name used for the query. In case of A/B test, the index targeted isn’t always the index used by the query.
     * Returned only if [Query.getRankingInfo] is set to true.
     */
    @SerialName(KeyIndexUsed) val indexUsedOrNull: IndexName? = null,
    /**
     * In case of A/B test, reports the variant ID used. The variant ID is the position in the array of variants
     * (starting at 1).
     * Returned only if [Query.getRankingInfo] is set to true.
     */
    @SerialName(KeyAbTestVariantID) val abTestVariantIDOrNull: Int? = null,
    /**
     * The query string that will be searched, after
     * [normalization][https://www.algolia.com/doc/guides/managing-results/optimize-search-results/handling-natural-languages-nlp/in-depth/normalization/#what-is-normalization].
     * Normalization includes removing stop words (if [Query.removeStopWords] or [Settings.removeStopWords] is enabled),
     * and transforming portions of the query string into phrase queries
     * (see [Query.advancedSyntax] or [Settings.advancedSyntax]).
     * Returned only if [Query.getRankingInfo] is set to true.
     */
    @SerialName(KeyParsedQuery) val parsedQueryOrNull: String? = null,
    /**
     * A mapping of each facet name to the corresponding facet counts.
     * Returned only if [Query.facets] is non-empty.
     */
    @SerialName(KeyFacets) @Serializable(KSerializerFacetMap::class) val facetsOrNull: Map<Attribute, List<Facet>>? = null,
    /**
     * A mapping of each facet name to the corresponding facet counts for disjunctive facets.
     * Returned only by the [EndpointSearch.searchDisjunctiveFacets] method.
     * [Documentation][https://www.algolia.com/doc/guides/building-search-ui/going-further/backend-search/how-to/faceting/?language=kotlin#conjunctive-and-disjunctive-faceting]
     */
    @SerialName(KeyDisjunctiveFacets) @Serializable(KSerializerFacetMap::class) val disjunctiveFacetsOrNull: Map<Attribute, List<Facet>>? = null,
    /**
     * Statistics for numerical facets.
     * Returned only if [Query.facets] is non-empty and at least one of the returned facets contains numerical values.
     */
    @SerialName(KeyFacets_Stats) val facetStatsOrNull: Map<Attribute, FacetStats>? = null,
    /**
     * Returned only by the [EndpointSearch.browse] method.
     */
    @SerialName(KeyCursor) val cursorOrNull: Cursor? = null,
    @SerialName(KeyIndex) val indexNameOrNull: IndexName? = null,
    @SerialName(KeyProcessed) val processedOrNull: Boolean? = null,
    /**
     * Identifies the query uniquely. Can be used by [InsightsEvent].
     */
    @SerialName(KeyQueryID) val queryIDOrNull: QueryID? = null
) {

    @Transient
    public val hits: List<Hit>
        get() = hitsOrNull!!

    @Transient
    public val nbHits: Int
        get() = nbHitsOrNull!!

    @Transient
    public val page: Int
        get() = pageOrNull!!

    @Transient
    public val hitsPerPage: Int
        get() = hitsPerPageOrNull!!

    @Transient
    public val userData: List<JsonObject>
        get() = userDataOrNull!!

    @Transient
    public val nbPages: Int
        get() = nbPagesOrNull!!

    @Transient
    public val processingTimeMS: Long
        get() = processingTimeMSOrNull!!

    @Transient
    public val exhaustiveNbHits: Boolean
        get() = exhaustiveNbHitsOrNull!!

    @Transient
    public val exhaustiveFacetsCount: Boolean
        get() = exhaustiveFacetsCountOrNull!!

    @Transient
    public val query: String
        get() = queryOrNull!!

    @Transient
    public val queryAfterRemoval: String
        get() = queryAfterRemovalOrNull!!

    @Transient
    public val params: String
        get() = paramsOrNull!!

    @Transient
    public val message: String
        get() = messageOrNull!!

    @Transient
    public val aroundLatLng: Point
        get() = aroundLatLngOrNull!!

    @Transient
    public val automaticRadius: Float
        get() = automaticRadiusOrNull!!

    @Transient
    public val serverUsed: String
        get() = serverUsedOrNull!!

    @Transient
    public val indexUsed: IndexName
        get() = indexUsedOrNull!!

    @Transient
    public val abTestVariantID: Int
        get() = abTestVariantIDOrNull!!

    @Transient
    public val parsedQuery: String
        get() = parsedQueryOrNull!!

    @Transient
    public val facets: Map<Attribute, List<Facet>>
        get() = facetsOrNull!!

    @Transient
    public val disjunctiveFacets: Map<Attribute, List<Facet>>
        get() = disjunctiveFacetsOrNull!!

    @Transient
    public val facetStats: Map<Attribute, FacetStats>
        get() = facetStatsOrNull!!

    @Transient
    public val cursor: Cursor
        get() = cursorOrNull!!

    @Transient
    public val indexName: IndexName
        get() = indexNameOrNull!!

    @Transient
    public val processed: Boolean
        get() = processedOrNull!!

    @Transient
    public val queryID: QueryID
        get() = queryIDOrNull!!

    /**
     * A Hit returned by the search.
     */
    @Serializable(Hit.Companion::class)
    public data class Hit(
        val json: JsonObject
    ) : Map<String, JsonElement> by json {

        /**
         * Deserialize the value of an [Attribute] to [T].
         */
        public fun <T> getValue(serializer: KSerializer<T>, attribute: Attribute): T {
            return Json.fromJson(serializer, json.getAs(attribute.raw))
        }

        /**
         * Deserialize the entire [json] to [T].
         */
        public fun <T> deserialize(serializer: KSerializer<T>): T {
            return JsonNonStrict.fromJson(serializer, json)
        }

        @Serializer(Hit::class)
        companion object : KSerializer<Hit> {

            override fun deserialize(decoder: Decoder): Hit {
                return Hit(decoder.asJsonInput().jsonObject)
            }

            override fun serialize(encoder: Encoder, obj: Hit) {
                encoder.asJsonOutput().encodeJson(obj.json)
            }
        }
    }
}
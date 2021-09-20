package com.algolia.search.model.response

import com.algolia.search.ExperimentalAlgoliaClientAPI
import com.algolia.search.endpoint.EndpointSearch
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.QueryID
import com.algolia.search.model.analytics.ABTestID
import com.algolia.search.model.filter.FilterGroup
import com.algolia.search.model.insights.InsightsEvent
import com.algolia.search.model.rule.RenderingContent
import com.algolia.search.model.search.Cursor
import com.algolia.search.model.search.Explain
import com.algolia.search.model.search.Facet
import com.algolia.search.model.search.FacetStats
import com.algolia.search.model.search.Point
import com.algolia.search.model.search.Query
import com.algolia.search.model.search.RankingInfo
import com.algolia.search.model.search.RemoveWordIfNoResults
import com.algolia.search.model.settings.Settings
import com.algolia.search.serialize.KSerializerFacetMap
import com.algolia.search.serialize.KSerializerPoint
import com.algolia.search.serialize.KeyABTestID
import com.algolia.search.serialize.KeyAbTestVariantID
import com.algolia.search.serialize.KeyAppliedRelevancyStrictness
import com.algolia.search.serialize.KeyAppliedRules
import com.algolia.search.serialize.KeyAroundLatLng
import com.algolia.search.serialize.KeyAutomaticRadius
import com.algolia.search.serialize.KeyCursor
import com.algolia.search.serialize.KeyDisjunctiveFacets
import com.algolia.search.serialize.KeyExhaustiveFacetsCount
import com.algolia.search.serialize.KeyExhaustiveNbHits
import com.algolia.search.serialize.KeyExplain
import com.algolia.search.serialize.KeyExtract
import com.algolia.search.serialize.KeyExtractAttribute
import com.algolia.search.serialize.KeyFacets
import com.algolia.search.serialize.KeyFacets_Stats
import com.algolia.search.serialize.KeyHierarchicalFacets
import com.algolia.search.serialize.KeyHits
import com.algolia.search.serialize.KeyHitsPerPage
import com.algolia.search.serialize.KeyIndex
import com.algolia.search.serialize.KeyIndexUsed
import com.algolia.search.serialize.KeyLength
import com.algolia.search.serialize.KeyMessage
import com.algolia.search.serialize.KeyNbHits
import com.algolia.search.serialize.KeyNbPages
import com.algolia.search.serialize.KeyNbSortedHits
import com.algolia.search.serialize.KeyOffset
import com.algolia.search.serialize.KeyPage
import com.algolia.search.serialize.KeyParams
import com.algolia.search.serialize.KeyParsedQuery
import com.algolia.search.serialize.KeyProcessed
import com.algolia.search.serialize.KeyProcessingTimeMS
import com.algolia.search.serialize.KeyQuery
import com.algolia.search.serialize.KeyQueryAfterRemoval
import com.algolia.search.serialize.KeyQueryID
import com.algolia.search.serialize.KeyRenderingContent
import com.algolia.search.serialize.KeyScore
import com.algolia.search.serialize.KeyServerUsed
import com.algolia.search.serialize.KeyUserData
import com.algolia.search.serialize.Key_Answer
import com.algolia.search.serialize.Key_DistinctSeqID
import com.algolia.search.serialize.Key_HighlightResult
import com.algolia.search.serialize.Key_RankingInfo
import com.algolia.search.serialize.Key_SnippetResult
import com.algolia.search.serialize.internal.Json
import com.algolia.search.serialize.internal.JsonNonStrict
import com.algolia.search.serialize.internal.asJsonInput
import com.algolia.search.serialize.internal.asJsonOutput
import com.algolia.search.serialize.internal.jsonObjectOrNull
import com.algolia.search.serialize.internal.jsonPrimitiveOrNull
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject

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
     * Index of the current page (zero-based). See the [Query.page] search parameter.
     * Not returned if you use offset/length for pagination.
     */
    @SerialName(KeyPage) val pageOrNull: Int? = null,
    /**
     * The maximum number of hits returned per page. See the [Query.hitsPerPage] search parameter.
     * Not returned if you use offset & length for pagination.
     */
    @SerialName(KeyHitsPerPage) val hitsPerPageOrNull: Int? = null,
    /**
     * Alternative to [page] (zero-based). Is returned only when [Query.offset] [Query.length] is specified.
     */
    @SerialName(KeyOffset) val offsetOrNull: Int? = null,
    /**
     * Alternative to [hitsPerPageOrNull] (zero-based). Is returned only when [Query.offset] [Query.length] is specified.
     */
    @SerialName(KeyLength) val lengthOrNull: Int? = null,
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
     * Returned only by the [EndpointSearch.advancedSearch] method.
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
    @SerialName(KeyQueryID) val queryIDOrNull: QueryID? = null,
    /**
     * A mapping of each facet name to the corresponding facet counts for hierarchical facets.
     * Returned only by the [EndpointSearch.advancedSearch] method, only if a [FilterGroup.And.Hierarchical] is used.
     */
    @SerialName(KeyHierarchicalFacets) val hierarchicalFacetsOrNull: Map<Attribute, List<Facet>>? = null,
    /**
     * Meta-information as to how the query was processed.
     */
    @SerialName(KeyExplain) val explainOrNull: Explain? = null,
    /**
     * The rules applied to the query.
     */
    @SerialName(KeyAppliedRules) val appliedRulesOrNull: List<JsonObject>? = null,
    /**
     * Applied relevancy score in the virtual index [0-100].
     */
    @SerialName(KeyAppliedRelevancyStrictness) val appliedRelevancyStrictnessOrNull: Int? = null,
    /**
     * Number of relevant hits to display in case of non-zero `relevancyStrictness` applied.
     */
    @SerialName(KeyNbSortedHits) val nbSortedHitsOrNull: Int? = null,

    /**
     * Content defining how the search interface should be rendered.
     */
    @SerialName(KeyRenderingContent) val renderingContentOrNull: RenderingContent? = null,

    /**
     * In case of A/B test, reports the ID of the A/B test used.
     * Returned only if [Query.getRankingInfo] is set to true.
     */
    @SerialName(KeyABTestID) val abTestIDOrNull: ABTestID? = null
) {

    public val hits: List<Hit>
        get() = hitsOrNull!!

    public val nbHits: Int
        get() = nbHitsOrNull!!

    public val page: Int
        get() = pageOrNull!!

    public val hitsPerPage: Int
        get() = hitsPerPageOrNull!!

    public val length: Int
        get() = lengthOrNull!!

    public val offset: Int
        get() = offsetOrNull!!

    public val userData: List<JsonObject>
        get() = userDataOrNull!!

    public val nbPages: Int
        get() = nbPagesOrNull!!

    public val processingTimeMS: Long
        get() = processingTimeMSOrNull!!

    public val exhaustiveNbHits: Boolean
        get() = exhaustiveNbHitsOrNull!!

    public val exhaustiveFacetsCount: Boolean
        get() = exhaustiveFacetsCountOrNull!!

    public val query: String
        get() = queryOrNull!!

    public val queryAfterRemoval: String
        get() = queryAfterRemovalOrNull!!

    public val params: String
        get() = paramsOrNull!!

    public val message: String
        get() = messageOrNull!!

    public val aroundLatLng: Point
        get() = aroundLatLngOrNull!!

    public val automaticRadius: Float
        get() = automaticRadiusOrNull!!

    public val serverUsed: String
        get() = serverUsedOrNull!!

    public val indexUsed: IndexName
        get() = indexUsedOrNull!!

    public val abTestVariantID: Int
        get() = abTestVariantIDOrNull!!

    public val parsedQuery: String
        get() = parsedQueryOrNull!!

    public val facets: Map<Attribute, List<Facet>>
        get() = facetsOrNull!!

    public val disjunctiveFacets: Map<Attribute, List<Facet>>
        get() = disjunctiveFacetsOrNull!!

    public val facetStats: Map<Attribute, FacetStats>
        get() = facetStatsOrNull!!

    public val cursor: Cursor
        get() = cursorOrNull!!

    public val indexName: IndexName
        get() = indexNameOrNull!!

    public val processed: Boolean
        get() = processedOrNull!!

    public val queryID: QueryID
        get() = queryIDOrNull!!

    public val hierarchicalFacets: Map<Attribute, List<Facet>>
        get() = hierarchicalFacetsOrNull!!

    public val explain: Explain
        get() = explainOrNull!!

    public val appliedRules: List<JsonObject>
        get() = appliedRulesOrNull!!

    public val appliedRelevancyStrictness: Int
        get() = requireNotNull(appliedRelevancyStrictnessOrNull)

    public val nbSortedHits: Int
        get() = requireNotNull(nbSortedHitsOrNull)

    public val renderingContent: RenderingContent
        get() = requireNotNull(renderingContentOrNull)

    /**
     * In case of A/B test, reports the ID of the A/B test used.
     * Returned only if [Query.getRankingInfo] is set to true.
     *
     * @throws IllegalStateException if [abTestIDOrNull] is null.
    */
    public val abTestID: ABTestID
        get() = checkNotNull(abTestIDOrNull)

    /**
     * Returns the position (0-based) within the [hits] result list of the record matching against the given [objectID].
     * If the [objectID] is not found, -1 is returned.
     */
    public fun getObjectPosition(objectID: ObjectID): Int {
        return hits.indexOfFirst { it.json["objectID"]?.jsonPrimitiveOrNull?.content == objectID.raw }
    }

    /**
     * A Hit returned by the search.
     */
    @Serializable(Hit.Companion::class)
    public data class Hit(
        val json: JsonObject,
    ) : Map<String, JsonElement> by json {

        public val distinctSeqIDOrNull: Int? = json[Key_DistinctSeqID]?.jsonPrimitiveOrNull?.int

        public val rankingInfoOrNull: RankingInfo? = json[Key_RankingInfo]?.jsonObjectOrNull?.let {
            JsonNonStrict.decodeFromJsonElement(RankingInfo.serializer(), it)
        }

        public val highlightResultOrNull: JsonObject? = json[Key_HighlightResult]?.jsonObjectOrNull

        public val snippetResultOrNull: JsonObject? = json[Key_SnippetResult]?.jsonObjectOrNull

        @ExperimentalAlgoliaClientAPI
        public val answerOrNull: Answer? = json[Key_Answer]?.jsonObjectOrNull?.let {
            JsonNonStrict.decodeFromJsonElement(Answer.serializer(), it)
        }

        public val rankingInfo: RankingInfo
            get() = rankingInfoOrNull!!

        public val distinctSeqID: Int
            get() = distinctSeqIDOrNull!!

        public val highlightResult: JsonObject
            get() = highlightResultOrNull!!

        public val snippetResult: JsonObject
            get() = snippetResultOrNull!!

        @ExperimentalAlgoliaClientAPI
        public val answer: Answer
            get() = answerOrNull!!

        /**
         * Deserialize the value of an [Attribute] to [T].
         */
        public fun <T> getValue(serializer: KSerializer<T>, attribute: Attribute): T {
            return Json.decodeFromJsonElement(serializer, json.getValue(attribute.raw))
        }

        /**
         * Deserialize the entire [json] to [T].
         */
        public fun <T> deserialize(deserializer: DeserializationStrategy<T>): T {
            return JsonNonStrict.decodeFromJsonElement(deserializer, json)
        }

        @OptIn(ExperimentalSerializationApi::class)
        @Serializer(Hit::class)
        public companion object : KSerializer<Hit> {

            override fun deserialize(decoder: Decoder): Hit {
                return Hit(decoder.asJsonInput().jsonObject)
            }

            override fun serialize(encoder: Encoder, value: Hit) {
                encoder.asJsonOutput().encodeJsonElement(value.json)
            }
        }
    }

    @Serializable
    public data class Answer(
        @SerialName(KeyExtract) val extract: String,
        @SerialName(KeyScore) val score: Double,
        @SerialName(KeyExtractAttribute) val extractAttribute: Attribute,
    )
}

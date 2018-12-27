package serialize

import client.serialize.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestKeys {

    @Test
    fun keys() {
        assertEquals(Query, "query")
        assertEquals(SearchableAttributes, "searchableAttributes")
        assertEquals(AttributesForFaceting, "attributesForFaceting")
        assertEquals(UnretrievableAttributes, "UnretrievableAttributes")
        assertEquals(AttributesToRetrieve, "attributesToRetrieve")
        assertEquals(RestrictSearchableAttributes, "restrictSearchableAttributes")
        assertEquals(Ranking, "ranking")
        assertEquals(CustomRanking, "customRanking")
        assertEquals(Replicas, "replicas")
        assertEquals(Filters, "filters")
        assertEquals(FacetFilters, "facetFilters")
        assertEquals(OptionalFilters, "optionalFilters")
        assertEquals(NumericFilters, "numericFilters")
        assertEquals(TagFilters, "tagFilters")
        assertEquals(SumOrFiltersScores, "sumOrFiltersScores")
        assertEquals(Facets, "facets")
        assertEquals(MaxValuesPerFacet, "maxValuesPerFacet")
        assertEquals(FacetingAfterDistinct, "facetingAfterDistinct")
        assertEquals(SortFacetValuesBy, "sortFacetValuesBy")
        assertEquals(AttributesToHighlight, "attributesToHighlight")
        assertEquals(AttributesToSnippet, "attributesToSnippet")
        assertEquals(HighlightPreTag, "highlightPreTag")
        assertEquals(HighlightPostTag, "highlightPostTag")
        assertEquals(SnippetEllipsisText, "snippetEllipsisText")
        assertEquals(RestrictHighlightAndSnippetArrays, "restrictHighlightAndSnippetArrays")
        assertEquals(Page, "page")
        assertEquals(HitsPerPage, "hitsPerPage")
        assertEquals(Offset, "offset")
        assertEquals(Length, "length")
        assertEquals(PaginationLimitedTo, "paginationLimitedTo")
        assertEquals(MinWordSizefor1Typo, "minWordSizefor1Typo")
        assertEquals(MinWordSizefor2Typos, "minWordSizefor2Typos")
        assertEquals(TypoTolerance, "typoTolerance")
        assertEquals(AllowTyposOnNumericTokens, "allowTyposOnNumericTokens")
        assertEquals(DisableTypoToleranceOnAttributes, "disableTypoToleranceOnAttributes")
        assertEquals(DisableTypoToleranceOnWords, "disableTypoToleranceOnWords")
        assertEquals(SeparatorsToIndex, "separatorsToIndex")
        assertEquals(AroundLatLng, "aroundLatLng")
        assertEquals(AroundLatLngViaIP, "aroundLatLngViaIP")
        assertEquals(AroundRadius, "aroundRadius")
        assertEquals(AroundPrecision, "aroundPrecision")
        assertEquals(MinimumAroundRadius, "minimumAroundRadius")
        assertEquals(InsideBoundingBox, "insideBoundingBox")
        assertEquals(InsidePolygon, "insidePolygon")
        assertEquals(IgnorePlurals, "ignorePlurals")
        assertEquals(RemoveStopWords, "removeStopWords")
        assertEquals(CamelCaseAttributes, "camelCaseAttributes")
        assertEquals(DecompoundedAttributes, "decompoundedAttributes")
        assertEquals(KeepDiacriticsOnCharacters, "keepDiacriticsOnCharacters")
        assertEquals(QueryLanguages, "queryLanguages")
        assertEquals(EnableRules, "enableRules")
        assertEquals(RuleContexts, "ruleContexts")
        assertEquals(EnablePersonalization, "enablePersonalization")
        assertEquals(QueryType, "queryType")
        assertEquals(RemoveWordsIfNoResults, "removeWordsIfNoResults")
        assertEquals(AdvancedSyntax, "advancedSyntax")
        assertEquals(OptionalWords, "optionalWords")
        assertEquals(DisablePrefixOnAttributes, "disablePrefixOnAttributes")
        assertEquals(DisableExactOnAttributes, "disableExactOnAttributes")
        assertEquals(ExactOnSingleWordQuery, "exactOnSingleWordQuery")
        assertEquals(AlternativesAsExact, "alternativesAsExact")
        assertEquals(NumericAttributesForFiltering, "numericAttributesForFiltering")
        assertEquals(AllowCompressionOfIntegerArray, "allowCompressionOfIntegerArray")
        assertEquals(AttributeForDistinct, "attributeForDistinct")
        assertEquals(Distinct, "distinct")
        assertEquals(GetRankingInfo, "getRankingInfo")
        assertEquals(ClickAnalytics, "clickAnalytics")
        assertEquals(Analytics, "analytics")
        assertEquals(AnalyticsTags, "analyticsTags")
        assertEquals(Synonyms, "synonyms")
        assertEquals(ReplaceSynonymsInHighlight, "replaceSynonymsInHighlight")
        assertEquals(MinProximity, "minProximity")
        assertEquals(ResponseFields, "responseFields")
        assertEquals(MaxFacetHits, "maxFacetHits")
        assertEquals(PercentileComputation, "percentileComputation")
        assertEquals(Geo, "geo")
        assertEquals(Typo, "typo")
        assertEquals(Words, "words")
        assertEquals(Proximity, "proximity")
        assertEquals(Attribute, "attribute")
        assertEquals(Exact, "exact")
        assertEquals(Custom, "custom")
        assertEquals(Asc, "asc")
        assertEquals(Desc, "desc")
        assertEquals(Strict, "strict")
        assertEquals(Min, "min")
    }
}
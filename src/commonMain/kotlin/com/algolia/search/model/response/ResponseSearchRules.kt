package com.algolia.search.model.response

import com.algolia.search.model.response.ResponseSearchRules.Hit
import com.algolia.search.model.rule.Rule
import com.algolia.search.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonObject


@Serializable
public data class ResponseSearchRules(
    /**
     * A list of [Hit].
     */
    @SerialName(KeyHits) val hits: List<Hit>,
    /**
     *  Number of hits.
     */
    @SerialName(KeyNbHits) val nbHits: Int,
    /**
     * Returned page number.
     */
    @SerialName(KeyPage) val page: Int,
    /**
     * Total number of pages.
     */
    @SerialName(KeyNbPages) val nbPages: Int
) {

    @Serializable(Hit.Companion::class)
    data class Hit(
        val rule: Rule,
        private val json : JsonObject
    ) {

        @Serializer(Hit::class)
        companion object : DeserializationStrategy<Hit> {

            override fun deserialize(decoder: Decoder): Hit {
                val json = decoder.asJsonInput().jsonObject
                val rule = JsonNonStrict.fromJson(Rule.serializer(), json)

                return Hit(rule, json)
            }
        }
    }

}
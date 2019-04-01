package documentation.parameters.advanced

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocMaxFacetHits {

//    maxFacetHits: Int = number_of_facet_hits

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                maxFacetHits = 10
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                maxFacetHits = 5
            }

            index.search(query)
        }
    }
}
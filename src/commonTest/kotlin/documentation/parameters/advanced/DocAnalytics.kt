package documentation.parameters.advanced

import com.algolia.search.dsl.query
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocAnalytics {

//    analytics: Boolean = true|false

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                analytics = true
            }

            index.search(query)
        }
    }
}
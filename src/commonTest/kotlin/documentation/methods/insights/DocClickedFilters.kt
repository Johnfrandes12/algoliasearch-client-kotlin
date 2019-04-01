package documentation.methods.insights

import clientInsights
import com.algolia.search.dsl.filtering.Filter
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.insights.EventName
import com.algolia.search.model.insights.UserToken
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocClickedFilters {

//    suspend fun ClientInsights.User.clickedFilters(
//        #{indexName}: __IndexName__,
//        #{eventName}: __EventName__,
//        #{filters}: __List<Filter.Facet>__,
//        timestamp: __Long?__ = null
//    ): HttpResponse

    @Test
    fun clickedFilters() {
        runBlocking {
            val userToken = UserToken("user-id")

            clientInsights.User(userToken).clickedFilters(
                indexName = IndexName("indexName"),
                eventName = EventName("eventName"),
                filters = listOf(Filter.Facet(Attribute("brand"), "apple"))
            )
        }
    }
}
package dsl.filtering

import attributeA
import com.algolia.search.dsl.filtering.DSLGroupFilter
import com.algolia.search.dsl.filtering.Filter
import com.algolia.search.dsl.filtering.NumericOperator
import shouldEqual
import unknown
import kotlin.test.Test


internal class TestDSLGroupFilter {

    @Test
    fun dx() {
        val dsl = DSLGroupFilter {
            +tag(unknown)
            +facet(attributeA, 0)
            +range(attributeA, 0 until 2)
            +comparison(attributeA, LesserOrEquals, 0)
        }

        dsl shouldEqual setOf(
            Filter.Tag(unknown),
            Filter.Facet(attributeA, 0),
            Filter.Numeric(attributeA, 0 until 2),
            Filter.Numeric(attributeA, NumericOperator.LesserOrEquals, 0)
        )
    }
}
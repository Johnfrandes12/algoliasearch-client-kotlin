package query

import client.query.helper.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestFilterBuilder {

    @Test
    fun showcaseOneConjunctiveWidget() {
        val category = Attribute("category")
        val categoryBook = FilterFacet(category, "book")
        val categoryOffice = FilterFacet(category, "office")
        val categoryGift = FilterFacet(category, "gift")
        val groupA = Group.And("groupA")
        val groupB = Group.And("groupB")

        FilterBuilder {
            groupA += categoryBook
            groupA += categoryOffice
            groupA += categoryGift
            assertEquals("category:book AND category:office AND category:gift", build())
        }

        FilterBuilder {
            groupA += categoryBook
            groupA += categoryOffice
            groupB += categoryGift
            assertEquals("(category:book AND category:office) AND category:gift", build())
            assertEquals(setOf(categoryBook, categoryOffice), groupA.get())
        }
    }

    @Test
    fun showCaseTwoDisjunctiveWidgets() {
        // First widget for disjunctive faceting on a category attribute
        val category = Attribute("category")
        val categoryBook = FilterFacet(category, "book")
        val categoryOffice = FilterFacet(category, "office")
        // Second widget for disjunctive faceting on a color attribute
        val color = Attribute("color")
        val colorRed = FilterFacet(color, "red")
        val colorBlue = FilterFacet(color, "blue")

        val categories = Group.Or("categories")
        val colors = Group.Or("colors")

        FilterBuilder {
            categories += categoryBook
            categories += categoryOffice
            assertEquals("category:book OR category:office", build())
            colors += colorRed
            colors += colorBlue
            assertEquals("(category:book OR category:office) AND (color:red OR color:blue)", build())
            categories -= categoryBook
            assertEquals("category:office AND (color:red OR color:blue)", build())
            categories.clear()
            assertEquals("color:red OR color:blue", build())
            clear()
            assertEquals("", build())
        }
    }

    @Test
    fun showCaseOneConjunctiveOneDisjunctive() {
        // First widget for disjunctive faceting on a category attribute
        val category = Attribute("category")
        val categoryBook = FilterFacet(category, "book")
        val categoryOffice = FilterFacet(category, "office")

        // Second widget for conjunctive faceting on a euro attribute
        val price = Attribute("price")
        val comparison = FilterComparison(price, NumericOperator.NotEquals, 15.0)
        val range = FilterRange(price, 5.0, 20.0)

        val categories = Group.Or("categories")
        val prices = Group.And("prices")

        FilterBuilder {
            categories += listOf(categoryBook, categoryOffice)
            assertEquals("category:book OR category:office", build())
            prices += comparison
            assertEquals("price != 15.0 AND (category:book OR category:office)", build())
            prices += range
            assertEquals("price != 15.0 AND price:5.0 TO 20.0 AND (category:book OR category:office)", build())
            categories -= categoryBook
            assertEquals("price != 15.0 AND price:5.0 TO 20.0 AND category:office", build())
        }
    }

    @Test
    fun showcaseReplaceAttribute() {
        // Widget for conjunctive faceting on a euro attribute
        val euro = Attribute("euro")
        val dollar = Attribute("dollar")
        val comparison = FilterComparison(euro, NumericOperator.NotEquals, 15.0)
        val range = FilterRange(euro, 5.0, 20.0)
        val euros = Group.And("euros")

        FilterBuilder {
            euros += comparison
            euros += range
            assertEquals("euro != 15.0 AND euro:5.0 TO 20.0", build())
            euros.replaceAttribute(euro, dollar)
            assertEquals("dollar != 15.0 AND dollar:5.0 TO 20.0", build())
        }
    }

    @Test
    fun showcaseDisjunctiveFiltersOfSimilarTypesButDifferentAttributes() {
        val price = Attribute("price")
        val nbLike = Attribute("nbLike")
        val comparisonPrice = FilterComparison(price, NumericOperator.NotEquals, 15.0)
        val rangeLike = FilterRange(nbLike, 100.0, 200.0)
        val groupA = Group.Or("groupA")
        val groupB = Group.Or("groupB")

        // In this scenario, we want to add them to the same OR group
        FilterBuilder {
            groupA += comparisonPrice
            groupA += rangeLike
            assertEquals("price != 15.0 OR nbLike:100.0 TO 200.0", build())
            assertEquals(setOf(comparisonPrice), groupA.get(price))
        }

        // In this scenario, we want to add them to different OR group
        FilterBuilder {
            groupA += comparisonPrice
            groupB += rangeLike
            assertEquals("price != 15.0 AND nbLike:100.0 TO 200.0", build())
        }
    }
}
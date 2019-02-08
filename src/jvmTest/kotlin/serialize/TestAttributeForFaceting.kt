package serialize

import attributeA
import com.algolia.search.model.AttributeForFaceting
import com.algolia.search.model.AttributeForFaceting.*
import com.algolia.search.serialize.KeyFilterOnly
import com.algolia.search.serialize.KeySearchable
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestAttributeForFaceting : TestSerializer<AttributeForFaceting>(AttributeForFaceting) {

    override val items = listOf(
        FilterOnly(attributeA) to JsonLiteral("$KeyFilterOnly($attributeA)"),
        Searchable(attributeA) to JsonLiteral("$KeySearchable($attributeA)"),
        Default(attributeA) to JsonLiteral(attributeA.raw)
    )
}
package documentation.methods.rule

import com.algolia.search.model.ObjectID
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocDeleteRule {

//    suspend fun Index.deleteRule(
//        #{objectID}: __ObjectID__,
//        #{forwardToReplicas}: __Boolean?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun deleteRule() {
        runBlocking {
            index.deleteRule(ObjectID("nyID"), forwardToReplicas = true)
        }
    }
}
package com.algolia.search.client.internal

import com.algolia.search.client.ClientInsights
import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.Credentials
import com.algolia.search.endpoint.EndpointInsights
import com.algolia.search.endpoint.EndpointInsightsImpl
import com.algolia.search.model.insights.InsightsEvent
import com.algolia.search.model.insights.UserToken
import com.algolia.search.transport.Transport

/**
 * Client to manage [InsightsEvent].
 */
public class ClientInsightsImpl internal constructor(
    internal val transport: Transport,
) : ClientInsights,
    EndpointInsights by EndpointInsightsImpl(transport),
    Configuration by transport,
    Credentials by transport.credentials {

    override fun User(userToken: UserToken): ClientInsights.User {
        return ClientInsights.User(insights = this, userToken = userToken)
    }
}

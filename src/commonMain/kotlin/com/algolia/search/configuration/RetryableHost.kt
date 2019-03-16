package com.algolia.search.configuration

import com.algolia.search.model.Time


data class RetryableHost(
    val url: String,
    val callType: CallType? = null
) {

    internal var isUp: Boolean = true
    internal var lastUpdated: Long = Time.getCurrentTimeMillis()
    internal var retryCount: Int = 0
}
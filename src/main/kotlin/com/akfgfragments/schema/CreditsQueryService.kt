package com.akfgfragments.schema

import com.akfgfragments.models.CreditedEntryType
import com.akfgfragments.models.Credits
import com.expediagroup.graphql.server.operations.Query

class CreditsQueryService : Query {
    @Suppress("unused")
    suspend fun getCredits(type: CreditedEntryType, title: String): Credits =
        Credits.getCredits(type, title)
}
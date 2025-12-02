package com.akfgfragments.schema

import com.akfgfragments.models.Tracklist
import com.expediagroup.graphql.server.operations.Query

class TracklistQueryService : Query {
    @Suppress("unused")
    suspend fun getTracklist(release: String): Tracklist =
        Tracklist.getTracklist(release)
}
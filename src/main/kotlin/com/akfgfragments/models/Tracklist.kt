package com.akfgfragments.models

import com.akfgfragments.db.model.MariaDbRepository
import com.expediagroup.graphql.generator.annotations.GraphQLDescription

data class TracklistEntry(
    val track: String,
    val additionalInfo: String
)

@GraphQLDescription("A model to describe the Tracklist entity")
data class Tracklist(
    val release: String,
    val tracklist: List<TracklistEntry>
) {
    companion object {
        suspend fun getTracklist(release: String) =
            MariaDbRepository().getTracklist(release)
    }
}
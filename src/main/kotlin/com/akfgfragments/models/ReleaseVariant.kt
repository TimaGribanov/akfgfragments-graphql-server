package com.akfgfragments.models

import com.akfgfragments.db.model.MariaDbRepository
import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import java.time.LocalDateTime

@GraphQLDescription("A model to describe the ReleaseVariant entity")
data class ReleaseVariant(
    val id: Int,
    val masterReleaseId: Int,
    val coverUri: String?,
    val format: String,
    val releaseDate: String,
    val catalogueNumber: String,
    val comment: String?
) {
    companion object {
        suspend fun get(masterReleaseId: Int): List<ReleaseVariant> =
            MariaDbRepository().getReleaseVariants(masterReleaseId)
    }
}
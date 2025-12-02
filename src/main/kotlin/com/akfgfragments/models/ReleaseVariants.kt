package com.akfgfragments.models

import com.akfgfragments.db.model.MariaDbRepository
import com.expediagroup.graphql.generator.annotations.GraphQLDescription

@GraphQLDescription("A model to describe the ReleaseVariants entity")
data class ReleaseVariants(
    val id: Int,
    val masterReleaseId: Int,
    val masterReleaseTitle: String,
    val coverUri: String?,
    val format: String,
    val releaseDate: String,
    val catalogueNumber: String,
    val comment: String?,
    val description: String?
) {
    companion object {
        suspend fun get(masterReleaseId: Int): List<ReleaseVariants> =
            MariaDbRepository().getReleaseVariants(masterReleaseId)
    }
}
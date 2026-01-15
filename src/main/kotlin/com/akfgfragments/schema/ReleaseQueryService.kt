package com.akfgfragments.schema

import com.akfgfragments.models.Release
import com.akfgfragments.models.ReleaseVariants
import com.expediagroup.graphql.server.operations.Query

class ReleaseQueryService : Query {
    @Suppress("unused")
    suspend fun getAllReleases(): List<Release> =
        Release.getAllReleases()

    @Suppress("unused")
    suspend fun getReleasesByType(type: String): List<Release> =
        Release.getReleasesByType(type)

    @Suppress("unused")
    suspend fun getReleaseByName(name: String): Release =
        Release.getReleaseByName(name)

    @Suppress("unused")
    suspend fun getReleasesByBand(band: String): List<Release> =
        Release.getReleasesByBand(band)

    @Suppress("unused")
    suspend fun getReleaseVariants(masterReleaseId: Int): List<ReleaseVariants> =
        ReleaseVariants.getReleaseVariants(masterReleaseId)
}
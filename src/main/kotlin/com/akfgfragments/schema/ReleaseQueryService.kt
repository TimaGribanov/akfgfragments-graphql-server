package com.akfgfragments.schema

import com.akfgfragments.models.Release
import com.expediagroup.graphql.server.operations.Query

class ReleaseQueryService : Query {
    @Suppress("unused")
    suspend fun getAllReleases(): List<Release> =
        Release.getAll()

    @Suppress("unused")
    suspend fun getReleasesByType(type: String): List<Release> =
        Release.getByType(type)

    @Suppress("unused")
    suspend fun getReleaseByName(name: String): Release =
        Release.getByName(name)

    @Suppress("unused")
    suspend fun getReleasesByBand(band: String): List<Release> =
        Release.getByBand(band)
}
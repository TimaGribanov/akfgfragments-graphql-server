package com.akfgfragments.models

import com.akfgfragments.db.model.MariaDbRepository
import com.expediagroup.graphql.generator.annotations.GraphQLDescription

//type in DB = ordinal + 1
enum class ReleaseType(val value: String) {
    ALBUM("album"),
    SINGLE("single"),
    MINI_ALBUM("mini-album"),
    COMPILATION("compilation"),
    INDIE("indie"),
    VIDEO("video"),
    OTHER("other");

    companion object {
        private val map = entries.associateBy(ReleaseType::value)
        infix fun from(entry: String): ReleaseType = map[entry]!!
    }
}

@GraphQLDescription("A model to describe the Release entity")
data class Release(
    val id: Int,
    override val band: String,
    val type: String,
    override val titleJapanese: String?,
    override val titleRomaji: String?,
    override val titleEnglish: String?,
    override val titleGerman: String?,
    override val titleIndonesian: String?,
    override val titleRussian: String?,
    override val titleUkrainian: String?,
    override val titleBelarusian: String?,
    override val titleItalian: String?,
    val coverUri: String?,
    override val spotify: String?,
    override val appleMusic: String?,
    override val amazonMusic: String?,
    override val deezer: String?,
    override val ytMusic: String?
) : CreditedEntry {
    companion object {
        suspend fun getAllReleases(): List<Release> =
            MariaDbRepository().allReleases()

        suspend fun getReleasesByType(type: String): List<Release> =
            MariaDbRepository().releasesByType(ReleaseType.from(type))

        suspend fun getReleaseByName(name: String): Release =
            MariaDbRepository().releaseByName(name)

        suspend fun getReleasesByBand(band: String): List<Release> =
            MariaDbRepository().releasesByBand(band)
    }
}
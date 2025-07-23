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
        infix fun from(entry: String) : ReleaseType = map[entry]!!
    }
}

@GraphQLDescription("A model to describe the Release entity")
data class Release(
    val id: Int,
    val band: String,
    val type: String,
    val titleJapanese: String?,
    val titleRomaji: String?,
    val titleEnglish: String?,
    val titleGerman: String?,
    val titleIndonesian: String?,
    val titleRussian: String?,
    val titleUkrainian: String?,
    val titleBelarusian: String?,
    val titleItalian: String?,
    val coverUri: String?,
    val spotify: String?,
    val appleMusic: String?,
    val amazonMusic: String?,
    val deezer: String?,
    val ytMusic: String?
) {
    companion object {
        suspend fun getAll(): List<Release> =
            MariaDbRepository().allReleases()

        suspend fun getByType(type: String): List<Release> =
            MariaDbRepository().releasesByType(ReleaseType.from(type))

        suspend fun getByName(name: String): Release =
            MariaDbRepository().releaseByName(name)

        suspend fun getByBand(band: String): List<Release> =
            MariaDbRepository().releasesByBand(band)
    }
}

//TODO: releases, songs, persons, mvs, lyrics, tabs
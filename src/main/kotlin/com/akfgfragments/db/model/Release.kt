package com.akfgfragments.db.model

import kotlinx.serialization.Serializable

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
        private val map = ReleaseType.entries.associateBy(ReleaseType::value)
        infix fun from(entry: String) : ReleaseType = map[entry]!!
    }
}

@Serializable
data class Release(
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
    val coverUri: String?,
    val spotify: String?,
    val appleMusic: String?,
    val amazonMusic: String?,
    val deezer: String?,
    val ytMusic: String?
)

//TODO: releases, songs, persons, mvs, lyrics, tabs
//TODO: modify the model of GraphQL accordingly, or maybe unify them
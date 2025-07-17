package com.akfgfragments.db.model

import kotlinx.serialization.Serializable

@Serializable
data class Song(
    val band: String,
    val titleJapanese: String?,
    val titleRomaji: String?,
    val titleEnglish: String?,
    val titleGerman: String?,
    val titleIndonesian: String?,
    val titleRussian: String?,
    val titleUkrainian: String?,
    val titleBelarusian: String?,
    val titleItalian: String?,
    val spotify: String?,
    val appleMusic: String?,
    val amazonMusic: String?,
    val deezer: String?,
    val ytMusic: String?
)
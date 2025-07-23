package com.akfgfragments.models

import com.akfgfragments.db.model.MariaDbRepository
import com.expediagroup.graphql.generator.annotations.GraphQLDescription

@GraphQLDescription("A model to describe the Song entity")
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
) {
    companion object {
        suspend fun getAll(): List<Song> =
            MariaDbRepository().allSongs()

        suspend fun getByName(name: String): Song =
            MariaDbRepository().songByName(name)

        suspend fun getByBand(band: String): List<Song> =
            MariaDbRepository().songsByBand(band)
    }
}
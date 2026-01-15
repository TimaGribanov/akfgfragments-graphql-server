package com.akfgfragments.models

import com.akfgfragments.db.model.MariaDbRepository
import com.expediagroup.graphql.generator.annotations.GraphQLDescription

@GraphQLDescription("A model to describe the Song entity")
data class Song(
    override val band: String,
    override val titleJapanese: String?,
    override val titleRomaji: String?,
    override val titleEnglish: String?,
    override val titleGerman: String?,
    override val titleIndonesian: String?,
    override val titleRussian: String?,
    override val titleUkrainian: String?,
    override val titleBelarusian: String?,
    override val titleItalian: String?,
    override val spotify: String?,
    override val appleMusic: String?,
    override val amazonMusic: String?,
    override val deezer: String?,
    override val ytMusic: String?,
): CreditedEntry {
    companion object {
        suspend fun getAllSongs(): List<Song> =
            MariaDbRepository().allSongs()

        suspend fun getSongByName(name: String): Song =
            MariaDbRepository().songByName(name)

        suspend fun getSongsByBand(band: String): List<Song> =
            MariaDbRepository().songsByBand(band)
    }
}
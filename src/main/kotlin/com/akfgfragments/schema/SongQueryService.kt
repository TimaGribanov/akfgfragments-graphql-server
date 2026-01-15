package com.akfgfragments.schema

import com.akfgfragments.models.Song
import com.expediagroup.graphql.server.operations.Query

class SongQueryService : Query {
    @Suppress("unused")
    suspend fun getAllSongs(): List<Song> =
        Song.getAllSongs()

    @Suppress("unused")
    suspend fun getSongByName(name: String): Song =
        Song.getSongByName(name)

    @Suppress("unused")
    suspend fun getSongsByBand(band: String): List<Song> =
        Song.getSongsByBand(band)
}
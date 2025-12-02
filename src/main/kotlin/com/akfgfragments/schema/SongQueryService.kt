package com.akfgfragments.schema

import com.akfgfragments.models.Song
import com.expediagroup.graphql.server.operations.Query

class SongQueryService : Query {
    @Suppress("unused")
    suspend fun getAllSongs(): List<Song> =
        Song.getAll()

    @Suppress("unused")
    suspend fun getSongByName(name: String): Song =
        Song.getByName(name)

    @Suppress("unused")
    suspend fun getSongsByBand(band: String): List<Song> =
        Song.getByBand(band)
}
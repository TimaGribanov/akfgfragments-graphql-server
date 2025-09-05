package com.akfgfragments.db.model

import com.akfgfragments.models.Language
import com.akfgfragments.models.Lyrics
import com.akfgfragments.models.Release
import com.akfgfragments.models.ReleaseType
import com.akfgfragments.models.ReleaseVariant
import com.akfgfragments.models.Song
import com.akfgfragments.models.Tracklist

interface Repository {
    suspend fun allReleases(): List<Release>
    suspend fun releasesByType(type: ReleaseType): List<Release>
    suspend fun releaseByName(name: String): Release
    suspend fun releasesByBand(band: String): List<Release>
//    suspend fun addRelease(release: Release)
//    suspend fun removeRelease(id: Int): Boolean

    suspend fun getReleaseVariants(masterReleaseId: Int): List<ReleaseVariant>

    suspend fun allSongs(): List<Song>
    suspend fun songByName(name: String): Song
    suspend fun songsByBand(band: String): List<Song>
    suspend fun addSong(song: Song)
    suspend fun removeSong(id: Int): Boolean

    suspend fun getLyrics(songTitle: String, lang: Language): Lyrics

    suspend fun getTracklist(release: String): Tracklist
}
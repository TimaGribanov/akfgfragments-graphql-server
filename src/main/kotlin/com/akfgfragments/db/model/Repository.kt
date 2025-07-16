package com.akfgfragments.db.model

interface Repository {
    suspend fun allReleases(): List<Release>
    suspend fun releasesByType(type: ReleaseType): List<Release>
    suspend fun addRelease(release: Release)
    suspend fun removeRelease(id: Int): Boolean

    suspend fun allSongs(): List<Song>
    suspend fun addSong(song: Song)
    suspend fun removeSong(id: Int): Boolean
}
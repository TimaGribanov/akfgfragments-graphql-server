package com.akfgfragments.db.model

import com.akfgfragments.models.CreditedEntryType
import com.akfgfragments.models.Credits
import com.akfgfragments.models.Language
import com.akfgfragments.models.Linktree
import com.akfgfragments.models.Lyrics
import com.akfgfragments.models.MusicVideo
import com.akfgfragments.models.Person
import com.akfgfragments.models.Release
import com.akfgfragments.models.ReleaseType
import com.akfgfragments.models.ReleaseVariants
import com.akfgfragments.models.Song
import com.akfgfragments.models.Tracklist

interface Repository {
    suspend fun allReleases(): List<Release>
    suspend fun releasesByType(type: ReleaseType): List<Release>
    suspend fun releaseByName(name: String): Release
    suspend fun releasesByBand(band: String): List<Release>

    suspend fun getReleaseVariants(masterReleaseId: Int): List<ReleaseVariants>

    suspend fun allSongs(): List<Song>
    suspend fun songByName(name: String): Song
    suspend fun songsByBand(band: String): List<Song>

    suspend fun getLyrics(songTitle: String, lang: Language): Lyrics

    suspend fun getTracklist(release: String): Tracklist

    suspend fun getAllPeople(): List<Person>
    suspend fun getPerson(name: String): Person

    suspend fun getAllMusicVideos(): List<MusicVideo>
    suspend fun getMusicVideoByTitle(title: String): MusicVideo

    suspend fun getAllLinks(): List<Linktree>
    suspend fun getLinktree(id: Int): Linktree

    suspend fun getCredits(type: CreditedEntryType, title: String): Credits
}
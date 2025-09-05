package com.akfgfragments.db.model

import com.akfgfragments.db.LyricsDAO
import com.akfgfragments.db.LyricsTable
import com.akfgfragments.db.ReleaseDAO
import com.akfgfragments.db.ReleaseTable
import com.akfgfragments.db.ReleaseVariantDAO
import com.akfgfragments.db.ReleaseVariantTable
import com.akfgfragments.db.SongDAO
import com.akfgfragments.db.SongTable
import com.akfgfragments.db.TracklistDAO
import com.akfgfragments.db.TracklistTable
import com.akfgfragments.db.daoToModel
import com.akfgfragments.db.suspendTransaction
import com.akfgfragments.models.Language
import com.akfgfragments.models.Lyrics
import com.akfgfragments.models.Release
import com.akfgfragments.models.ReleaseType
import com.akfgfragments.models.ReleaseVariant
import com.akfgfragments.models.Song
import com.akfgfragments.models.Tracklist
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere

class MariaDbRepository : Repository {
    override suspend fun allReleases(): List<Release> = suspendTransaction {
        ReleaseDAO.all().map(::daoToModel)
    }

    override suspend fun releasesByType(type: ReleaseType): List<Release> = suspendTransaction {
        ReleaseDAO
            .find { ReleaseTable.type eq type.value }
            .map(::daoToModel)
    }

    override suspend fun releaseByName(name: String): Release = suspendTransaction {
        ReleaseDAO
            .find { ReleaseTable.titleRomaji eq name }
            .map(::daoToModel)[0]
    }

    override suspend fun releasesByBand(band: String): List<Release> = suspendTransaction {
        ReleaseDAO
            .find { ReleaseTable.band eq band }
            .map(::daoToModel)
    }

//    //TODO: not finished
//    override suspend fun addRelease(release: Release): Unit = suspendTransaction {
//        ReleaseDAO.new {
//            band = release.band
//            type = release.type
//            titleJapanese = release.titleJapanese
//            titleRomaji = release.titleRomaji
//            titleEnglish = release.titleEnglish
//            titleGerman = release.titleGerman
//            titleIndonesian = release.titleIndonesian
//            titleRussian = release.titleRussian
//            titleUkrainian = release.titleUkrainian
//            titleBelarusian = release.titleBelarusian
//            titleItalian = release.titleItalian
//            coverUri = release.coverUri
//            spotify = release.spotify
//            appleMusic = release.appleMusic
//            amazonMusic = release.amazonMusic
//            deezer = release.deezer
//            ytMusic = release.ytMusic
//        }
//    }
//
//    override suspend fun editRelease(id: Int, newRelease: Release): Unit = suspendTransaction {
//        ReleaseDAO.findByIdAndUpdate(id) {
//            it.band = newRelease.band
//            it.type = newRelease.type
//            it.titleJapanese = newRelease.titleJapanese
//            it.titleRomaji = newRelease.titleRomaji
//            it.titleEnglish = newRelease.titleEnglish
//            it.titleGerman = newRelease.titleGerman
//            it.titleIndonesian = newRelease.titleIndonesian
//            it.titleRussian = newRelease.titleRussian
//            it.titleUkrainian = newRelease.titleUkrainian
//            it.titleBelarusian = newRelease.titleBelarusian
//            it.titleItalian = newRelease.titleItalian
//            it.coverUri = newRelease.coverUri
//            it.spotify = newRelease.spotify
//            it.appleMusic = newRelease.appleMusic
//            it.amazonMusic = newRelease.amazonMusic
//            it.deezer = newRelease.deezer
//            it.ytMusic = newRelease.ytMusic
//        }
//    }
//
//    //TODO: not finished
//    override suspend fun removeRelease(id: Int): Boolean = suspendTransaction {
//        val rowsDeleted = ReleaseTable.deleteWhere {
//            ReleaseTable.id eq id
//        }
//        rowsDeleted == 1
//    }

    override suspend fun getReleaseVariants(masterReleaseId: Int): List<ReleaseVariant> = suspendTransaction {
        ReleaseVariantDAO
            .find{ ReleaseVariantTable.masterReleaseId eq masterReleaseId }
            .map(::daoToModel)
    }

    override suspend fun allSongs(): List<Song> = suspendTransaction {
        SongDAO.all().map(::daoToModel)
    }

    override suspend fun songByName(name: String): Song = suspendTransaction {
        SongDAO
            .find { SongTable.titleRomaji eq name }
            .map(::daoToModel)[0]
    }

    override suspend fun songsByBand(band: String): List<Song> = suspendTransaction {
        SongDAO
            .find { SongTable.band eq band }
            .map(::daoToModel)
    }

    override suspend fun addSong(song: Song) {
        TODO("Not yet implemented")
    }

    override suspend fun removeSong(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getLyrics(songTitle: String, lang: Language): Lyrics = suspendTransaction {
        LyricsDAO
            .find { (LyricsTable.songTitle eq songTitle).and (LyricsTable.lang eq lang.code) }
            .map(::daoToModel)[0]
    }

    override suspend fun getTracklist(release: String): Tracklist = suspendTransaction {
        TracklistDAO
            .find { TracklistTable.release eq release }
            .map(::daoToModel)[0]
    }
}
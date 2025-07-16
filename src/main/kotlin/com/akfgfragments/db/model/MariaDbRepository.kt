package com.akfgfragments.db.model

import com.akfgfragments.db.ReleaseDAO
import com.akfgfragments.db.ReleaseTable
import com.akfgfragments.db.SongDAO
import com.akfgfragments.db.daoToModel
import com.akfgfragments.db.suspendTransaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
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

    //TODO: not finished
    override suspend fun addRelease(release: Release): Unit = suspendTransaction {
        ReleaseDAO.new {
            band = release.band
            type = release.type.toString()
            titleJapanese = release.titleJapanese
            titleRomaji = release.titleRomaji
            titleEnglish = release.titleEnglish
            titleGerman = release.titleGerman
            titleIndonesian = release.titleIndonesian
            titleRussian = release.titleRussian
            titleUkrainian = release.titleUkrainian
            titleBelarusian = release.titleBelarusian
            coverUri = release.coverUri
            spotify = release.spotify
            appleMusic = release.appleMusic
            amazonMusic = release.amazonMusic
            deezer = release.deezer
            ytMusic = release.ytMusic
        }
    }

    //TODO: not finished
    override suspend fun removeRelease(id: Int): Boolean = suspendTransaction {
        val rowsDeleted = ReleaseTable.deleteWhere {
            ReleaseTable.id eq id
        }
        rowsDeleted == 1
    }

    override suspend fun allSongs(): List<Song> = suspendTransaction {
        SongDAO.all().map(::daoToModel)
    }

    override suspend fun addSong(song: Song) {
        TODO("Not yet implemented")
    }

    override suspend fun removeSong(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}
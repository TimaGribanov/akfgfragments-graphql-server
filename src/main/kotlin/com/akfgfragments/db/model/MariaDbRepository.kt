package com.akfgfragments.db.model

import com.akfgfragments.db.CreditsDAO
import com.akfgfragments.db.CreditsTable
import com.akfgfragments.db.LinktreeDAO
import com.akfgfragments.db.LinktreeTable
import com.akfgfragments.db.LyricsDAO
import com.akfgfragments.db.LyricsTable
import com.akfgfragments.db.MusicVideoDAO
import com.akfgfragments.db.MusicVideoTable
import com.akfgfragments.db.PersonDAO
import com.akfgfragments.db.PersonTable
import com.akfgfragments.db.ReleaseDAO
import com.akfgfragments.db.ReleaseTable
import com.akfgfragments.db.ReleaseVariantsDAO
import com.akfgfragments.db.ReleaseVariantsTable
import com.akfgfragments.db.SongDAO
import com.akfgfragments.db.SongTable
import com.akfgfragments.db.TracklistDAO
import com.akfgfragments.db.TracklistTable
import com.akfgfragments.db.daoToModel
import com.akfgfragments.db.suspendTransaction
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
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.or

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

    override suspend fun getReleaseVariants(masterReleaseId: Int): List<ReleaseVariants> = suspendTransaction {
        ReleaseVariantsDAO
            .find { ReleaseVariantsTable.masterReleaseId eq masterReleaseId }
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

    override suspend fun getLyrics(songTitle: String, lang: Language): Lyrics = suspendTransaction {
        LyricsDAO
            .find { (LyricsTable.songTitle eq songTitle).and(LyricsTable.lang eq lang.code) }
            .map(::daoToModel)[0]
    }

    override suspend fun getTracklist(release: String): Tracklist = suspendTransaction {
        TracklistDAO
            .find { TracklistTable.release eq release }
            .map(::daoToModel)[0]
    }

    override suspend fun getAllPeople(): List<Person> = suspendTransaction {
        PersonDAO.all().map(::daoToModel)
    }

    override suspend fun getPerson(name: String): Person = suspendTransaction {
        PersonDAO
            .find { (PersonTable.nameEnglish eq name).or(PersonTable.nameJapanese eq name) }
            .map(::daoToModel)[0]
    }

    override suspend fun getAllMusicVideos(): List<MusicVideo> = suspendTransaction {
        MusicVideoDAO.all().map(::daoToModel)
    }

    override suspend fun getMusicVideoByTitle(title: String): MusicVideo = suspendTransaction {
        MusicVideoDAO
            .find { MusicVideoTable.mvTitle eq title }
            .map(::daoToModel)[0]
    }

    override suspend fun getAllLinks(): List<Linktree> = suspendTransaction {
        LinktreeDAO.all().map(::daoToModel)
    }

    override suspend fun getLinktree(id: Int): Linktree = suspendTransaction {
        LinktreeDAO
            .find { LinktreeTable.id eq id }
            .map(::daoToModel)[0]
    }

    override suspend fun getCredits(type: CreditedEntryType, title: String): Credits = suspendTransaction {
        CreditsDAO
            .find { (CreditsTable.relationType eq type.value).and(CreditsTable.releaseOrSong eq title ) }
            .map(::daoToModel)[0]
    }
}
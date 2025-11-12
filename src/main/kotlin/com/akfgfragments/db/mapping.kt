package com.akfgfragments.db

import com.akfgfragments.models.Language
import com.akfgfragments.models.Linktree
import com.akfgfragments.models.Lyrics
import com.akfgfragments.models.MusicVideo
import com.akfgfragments.models.MusicVideoType
import com.akfgfragments.models.Person
import com.akfgfragments.models.Release
import com.akfgfragments.models.ReleaseVariants
import com.akfgfragments.models.Song
import com.akfgfragments.models.SourceType
import com.akfgfragments.models.Tracklist
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.javatime.timestamp
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object ReleaseTable : IntIdTable("Release") {
    val band = varchar("band", 50)
    val type = varchar("type", 20)
    val titleJapanese = text("titleJapanese").nullable()
    val titleRomaji = text("titleRomaji").nullable()
    val titleEnglish = text("titleEnglish").nullable()
    val titleGerman = text("titleGerman").nullable()
    val titleIndonesian = text("titleIndonesian").nullable()
    val titleRussian = text("titleRussian").nullable()
    val titleUkrainian = text("titleUkrainian").nullable()
    val titleBelarusian = text("titleBelarusian").nullable()
    val titleItalian = text("titleItalian").nullable()
    val coverUri = varchar("coverUri", 100).nullable()
    val spotify = varchar("spotify", 100).nullable()
    val appleMusic = varchar("appleMusic", 100).nullable()
    val amazonMusic = varchar("amazonMusic", 100).nullable()
    val deezer = varchar("deezer", 100).nullable()
    val ytMusic = varchar("ytMusic", 100).nullable()
}

class ReleaseDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ReleaseDAO>(ReleaseTable, entityCtor = { ReleaseDAO(it) })

    var band by ReleaseTable.band
    var type by ReleaseTable.type
    var titleJapanese by ReleaseTable.titleJapanese
    var titleRomaji by ReleaseTable.titleRomaji
    var titleEnglish by ReleaseTable.titleEnglish
    var titleGerman by ReleaseTable.titleGerman
    var titleIndonesian by ReleaseTable.titleIndonesian
    var titleRussian by ReleaseTable.titleRussian
    var titleUkrainian by ReleaseTable.titleUkrainian
    var titleBelarusian by ReleaseTable.titleBelarusian
    var titleItalian by ReleaseTable.titleItalian
    var coverUri by ReleaseTable.coverUri
    var spotify by ReleaseTable.spotify
    var appleMusic by ReleaseTable.appleMusic
    var amazonMusic by ReleaseTable.amazonMusic
    var deezer by ReleaseTable.deezer
    var ytMusic by ReleaseTable.ytMusic
}

object ReleaseVariantsTable : IntIdTable("ReleaseVariants") {
    val masterReleaseId = integer("masterReleaseID")
    val masterReleaseTitle = text("masterReleaseTitle")
    val coverUri = text("coverUri").nullable()
    val format = varchar("format", 20)
    val releaseDate = timestamp("releaseDate")
    val catalogueNumber = varchar("catalogueNumber", 50)
    val comment = varchar("comment", 100).nullable()
    val description = text("description").nullable()
}

class ReleaseVariantsDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ReleaseVariantsDAO>(ReleaseVariantsTable, entityCtor = { ReleaseVariantsDAO(it) })

    var masterReleaseId by ReleaseVariantsTable.masterReleaseId
    var masterReleaseTitle by ReleaseVariantsTable.masterReleaseTitle
    var coverUri by ReleaseVariantsTable.coverUri
    val format by ReleaseVariantsTable.format
    var releaseDate by ReleaseVariantsTable.releaseDate
    var catalogueNumber by ReleaseVariantsTable.catalogueNumber
    var comment by ReleaseVariantsTable.comment
    var description by ReleaseVariantsTable.description
}

object SongTable : IntIdTable("Song") {
    val band = varchar("band", 50)
    val titleJapanese = text("titleJapanese").nullable()
    val titleRomaji = text("titleRomaji").nullable()
    val titleEnglish = text("titleEnglish").nullable()
    val titleGerman = text("titleGerman").nullable()
    val titleIndonesian = text("titleIndonesian").nullable()
    val titleRussian = text("titleRussian").nullable()
    val titleUkrainian = text("titleUkrainian").nullable()
    val titleBelarusian = text("titleBelarusian").nullable()
    val titleItalian = text("titleItalian").nullable()
    val spotify = varchar("spotify", 100).nullable()
    val appleMusic = varchar("appleMusic", 100).nullable()
    val amazonMusic = varchar("amazonMusic", 100).nullable()
    val deezer = varchar("deezer", 100).nullable()
    val ytMusic = varchar("ytMusic", 100).nullable()
}

class SongDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SongDAO>(SongTable, entityCtor = { SongDAO(it) })

    var band by SongTable.band
    var titleJapanese by SongTable.titleJapanese
    var titleRomaji by SongTable.titleRomaji
    var titleEnglish by SongTable.titleEnglish
    var titleGerman by SongTable.titleGerman
    var titleIndonesian by SongTable.titleIndonesian
    var titleRussian by SongTable.titleRussian
    var titleUkrainian by SongTable.titleUkrainian
    var titleBelarusian by SongTable.titleBelarusian
    var titleItalian by SongTable.titleItalian
    var spotify by SongTable.spotify
    var appleMusic by SongTable.appleMusic
    var amazonMusic by SongTable.amazonMusic
    var deezer by SongTable.deezer
    var ytMusic by SongTable.ytMusic
}

object LyricsTable : IntIdTable("Lyrics") {
    val band = varchar("band", 50)
    val songTitle = varchar("song", 100)
    val lang = varchar("lang", 2)
    val text = text("text")
    val sourceType = varchar("sourceType", 10)
    val sourceName = varchar("source", 100)
}

class LyricsDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<LyricsDAO>(LyricsTable, entityCtor = { LyricsDAO(it) })

    var band by LyricsTable.band
    var songTitle by LyricsTable.songTitle
    var lang by LyricsTable.lang
    var text by LyricsTable.text
    var sourceType by LyricsTable.sourceType
    var sourceName by LyricsTable.sourceName
}

object TracklistTable : IntIdTable("Tracklist") {
    val release = varchar("release", 100)
    val tracklist = text("tracklist")
}

class TracklistDAO(id: EntityID<Int>) : IntEntity(id) {
    //entityCtor is needed here to fix problem with native with graalvm: https://github.com/JetBrains/Exposed/issues/1274#issuecomment-2018356868
    companion object : IntEntityClass<TracklistDAO>(TracklistTable, entityCtor = { TracklistDAO(it) })

    var release by TracklistTable.release
    var tracklist by TracklistTable.tracklist
}

object PersonTable : IntIdTable("Person") {
    val nameJapanese = varchar("nameJapanese", 100)
    val nameEnglish = varchar("nameEnglish", 100)
    val nameRussian = varchar("nameRussian", 100).nullable()
    val nameUkrainian = varchar("nameUkrainian", 100).nullable()
    val nameBelarusian = varchar("nameBelarusian", 100).nullable()
}

class PersonDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PersonDAO>(PersonTable, entityCtor = { PersonDAO(it) })

    var nameJapanese by PersonTable.nameJapanese
    var nameEnglish by PersonTable.nameEnglish
    var nameRussian by PersonTable.nameRussian
    var nameUkrainian by PersonTable.nameUkrainian
    var nameBelarusian by PersonTable.nameBelarusian
}

object MusicVideoTable : IntIdTable("MusicVideo") {
    val mvTitle = varchar("mvTitle", 100)
    val song = text("song")
    val director = varchar("director", 100).nullable()
    val year = date("year")
    val url = text("url")
    val type = varchar("type", 10)
}

class MusicVideoDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<MusicVideoDAO>(MusicVideoTable, entityCtor = { MusicVideoDAO(it) })

    var mvTitle by MusicVideoTable.mvTitle
    var song by MusicVideoTable.song
    var director by MusicVideoTable.director
    var year by MusicVideoTable.year
    var url by MusicVideoTable.url
    var type by MusicVideoTable.type
}

object LinktreeTable : IntIdTable("Linktree") {
    val header = text("header")
    val image = text("image")
    val urlEnglish = text("urlEnglish").nullable()
    val urlJapanese = text("urlJapanese").nullable()
    val urlGerman = text("urlGerman").nullable()
    val urlIndonesian = text("urlIndonesian").nullable()
    val urlItalian = text("urlItalian").nullable()
    val urlSpanish = text("urlSpanish").nullable()
    val urlRussian = text("urlRussian").nullable()
    val urlUkrainian = text("urlUkrainian").nullable()
    val urlBelarusian = text("urlBelarusian").nullable()
}

class LinktreeDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<LinktreeDAO>(LinktreeTable, entityCtor = { LinktreeDAO(it) })

    var header by LinktreeTable.header
    var image by LinktreeTable.image
    var urlEnglish by LinktreeTable.urlEnglish
    var urlJapanese by LinktreeTable.urlJapanese
    var urlGerman by LinktreeTable.urlGerman
    var urlIndonesian by LinktreeTable.urlIndonesian
    var urlItalian by LinktreeTable.urlItalian
    var urlSpanish by LinktreeTable.urlSpanish
    var urlRussian by LinktreeTable.urlRussian
    var urlUkrainian by LinktreeTable.urlUkrainian
    var urlBelarusian by LinktreeTable.urlBelarusian
}

suspend fun <T> suspendTransaction(block: Transaction.() -> T): T =
    newSuspendedTransaction(Dispatchers.IO, statement = block)

fun daoToModel(dao: ReleaseDAO) = Release(
    dao.id.value,
    dao.band,
    dao.type,
    dao.titleJapanese,
    dao.titleRomaji,
    dao.titleEnglish,
    dao.titleGerman,
    dao.titleIndonesian,
    dao.titleRussian,
    dao.titleUkrainian,
    dao.titleBelarusian,
    dao.titleItalian,
    dao.coverUri,
    dao.spotify,
    dao.appleMusic,
    dao.amazonMusic,
    dao.deezer,
    dao.ytMusic
)

fun daoToModel(dao: ReleaseVariantsDAO) = ReleaseVariants(
    dao.id.value,
    dao.masterReleaseId,
    dao.masterReleaseTitle,
    dao.coverUri,
    dao.format,
    dao.releaseDate.toString().split("T")[0],
    dao.catalogueNumber,
    dao.comment,
    dao.description
)

fun daoToModel(dao: SongDAO) = Song(
    dao.band,
    dao.titleJapanese,
    dao.titleRomaji,
    dao.titleEnglish,
    dao.titleGerman,
    dao.titleIndonesian,
    dao.titleRussian,
    dao.titleUkrainian,
    dao.titleBelarusian,
    dao.titleItalian,
    dao.spotify,
    dao.appleMusic,
    dao.amazonMusic,
    dao.deezer,
    dao.ytMusic
)

fun daoToModel(dao: LyricsDAO) = Lyrics(
    dao.band,
    dao.songTitle,
    Language.from(dao.lang),
    dao.text,
    SourceType.valueOf(dao.sourceType),
    dao.sourceName
)

fun daoToModel(dao: TracklistDAO) = Tracklist(
    dao.release,
    dao.tracklist.split(";")
)

fun daoToModel(dao: PersonDAO) = Person(
    dao.id.value,
    dao.nameJapanese,
    dao.nameEnglish,
    dao.nameRussian,
    dao.nameUkrainian,
    dao.nameBelarusian
)

fun daoToModel(dao: MusicVideoDAO) = MusicVideo(
    dao.id.value,
    dao.mvTitle,
    dao.song,
    dao.director,
    dao.year.year,
    dao.url,
    MusicVideoType.from(dao.type)
)

fun daoToModel(dao: LinktreeDAO) = Linktree(
    dao.id.value,
    dao.header,
    dao.image,
    dao.urlEnglish,
    dao.urlJapanese,
    dao.urlGerman,
    dao.urlIndonesian,
    dao.urlItalian,
    dao.urlSpanish,
    dao.urlRussian,
    dao.urlUkrainian,
    dao.urlBelarusian
)
package com.akfgfragments.db

import com.akfgfragments.models.Language
import com.akfgfragments.models.Lyrics
import com.akfgfragments.models.Release
import com.akfgfragments.models.Song
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Transaction
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
    companion object : IntEntityClass<ReleaseDAO>(ReleaseTable)

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
    companion object : IntEntityClass<SongDAO>(SongTable)

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
}

class LyricsDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<LyricsDAO>(LyricsTable)

    var band by LyricsTable.band
    var songTitle by LyricsTable.songTitle
    var lang by LyricsTable.lang
    var text by LyricsTable.text
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
    dao.text
)
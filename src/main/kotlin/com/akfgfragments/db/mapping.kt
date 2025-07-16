package com.akfgfragments.db

import com.akfgfragments.db.model.Release
import com.akfgfragments.db.model.Song
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
    val titleJapanese = varchar("titleJapanese", 100).nullable()
    val titleRomaji = varchar("titleRomaji", 100).nullable()
    val titleEnglish = varchar("titleEnglish", 100).nullable()
    val titleGerman = varchar("titleGerman", 100).nullable()
    val titleIndonesian = varchar("titleIndonesian", 100).nullable()
    val titleRussian = varchar("titleRussian", 100).nullable()
    val titleUkrainian = varchar("titleUkrainian", 100).nullable()
    val titleBelarusian = varchar("titleBelarusian", 100).nullable()
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
    var coverUri by ReleaseTable.coverUri
    var spotify by ReleaseTable.spotify
    var appleMusic by ReleaseTable.appleMusic
    var amazonMusic by ReleaseTable.amazonMusic
    var deezer by ReleaseTable.deezer
    var ytMusic by ReleaseTable.ytMusic
}

object SongTable : IntIdTable("Song") {
    val band = varchar("band", 50)
    val titleJapanese = varchar("titleJapanese", 100).nullable()
    val titleRomaji = varchar("titleRomaji", 100).nullable()
    val titleEnglish = varchar("titleEnglish", 100).nullable()
    val titleGerman = varchar("titleGerman", 100).nullable()
    val titleIndonesian = varchar("titleIndonesian", 100).nullable()
    val titleRussian = varchar("titleRussian", 100).nullable()
    val titleUkrainian = varchar("titleUkrainian", 100).nullable()
    val titleBelarusian = varchar("titleBelarusian", 100).nullable()
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
    var spotify by SongTable.spotify
    var appleMusic by SongTable.appleMusic
    var amazonMusic by SongTable.amazonMusic
    var deezer by SongTable.deezer
    var ytMusic by SongTable.ytMusic
}

suspend fun <T> suspendTransaction(block: Transaction.() -> T): T =
    newSuspendedTransaction(Dispatchers.IO, statement = block)

fun daoToModel(dao: ReleaseDAO) = Release(
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
    dao.spotify,
    dao.appleMusic,
    dao.amazonMusic,
    dao.deezer,
    dao.ytMusic
)
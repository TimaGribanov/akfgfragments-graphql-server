package com.akfgfragments.models

enum class CreditedEntryType(val value: String) {
    RELEASE("release"),
    SONG("song");

    companion object {
        private val map = CreditedEntryType.entries.associateBy(CreditedEntryType::value)
        infix fun from(entry: String): CreditedEntryType = map[entry]!!
    }
}

interface CreditedEntry {
    val band: String
    val titleJapanese: String?
    val titleRomaji: String?
    val titleEnglish: String?
    val titleGerman: String?
    val titleIndonesian: String?
    val titleRussian: String?
    val titleUkrainian: String?
    val titleBelarusian: String?
    val titleItalian: String?
    val spotify: String?
    val appleMusic: String?
    val amazonMusic: String?
    val deezer: String?
    val ytMusic: String?

    companion object {
        suspend fun getByTypeAndTitle(type: CreditedEntryType, title: String): CreditedEntry {
            return if (type == CreditedEntryType.RELEASE)
                Release.getReleaseByName(title)
            else
                Song.getSongByName(title)
        }
    }
}
package com.akfgfragments.models

import com.akfgfragments.db.model.MariaDbRepository
import com.expediagroup.graphql.generator.annotations.GraphQLDescription

enum class Language(val code: String) {
    JAPANESE("ja"),
    ROMAJI("ro"),
    ENGLISH("en"),
    GERMAN("de"),
    INDONESIAN("id"),
    RUSSIAN("ru"),
    UKRAINIAN("uk"),
    BELARUSIAN("be"),
    ITALIAN("it"),
    FRENCH("fr"),
    PORTUGUESE("pt"),
    SPANISH("es");

    companion object {
        private val map = Language.entries.associateBy(Language::code)
        infix fun from(entry: String) : Language = map[entry]!!
    }
}

@GraphQLDescription("A model to describe the Lyrics entity")
data class Lyrics(
    val band: String,
    val songTitle: String,
    val lang: Language,
    val text: String
) {
    companion object {
        suspend fun getLyrics(songTitle: String, lang: Language) =
            MariaDbRepository().getLyrics(songTitle, lang)
    }
}
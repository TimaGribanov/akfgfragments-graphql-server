package com.akfgfragments.schema

import com.akfgfragments.models.Language
import com.akfgfragments.models.Lyrics
import com.expediagroup.graphql.server.operations.Query

class LyricsQueryService : Query {
    @Suppress("unused")
    suspend fun getLyrics(songTitle: String, lang: Language): Lyrics =
        Lyrics.getLyrics(songTitle, lang)
}
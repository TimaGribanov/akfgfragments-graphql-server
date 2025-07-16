package com.akfgfragments.schema.models

import com.expediagroup.graphql.generator.annotations.GraphQLDescription

@GraphQLDescription("")
data class Release(
    val id: Int,
    val band: String,
    val titleJapanese: String,
    val titleRomaji: String,
    val titleEnglish: String,
    val titleGerman: String,
    val titleIndonesian: String,
    val titleRussian: String,
    val titleUkrainian: String,
    val titleBelarusian: String,
    val imgUri: String,
    val spotify: String,
    val appleMusic: String,
    val amazonMusic: String,
    val deezer: String,
    val youtubeMusic: String
) {
    companion object {
        fun search(ids: List<Int>): List<Release> {
            return listOf()
        }
    }
}

package com.akfgfragments.models

import com.akfgfragments.db.model.MariaDbRepository
import com.expediagroup.graphql.generator.annotations.GraphQLDescription

@GraphQLDescription("A model to describe the Linktree entity")
data class Linktree(
    val id: Int,
    val header: String,
    val image: String,
    val urlEnglish: String?,
    val urlJapanese: String?,
    val urlGerman: String?,
    val urlIndonesian: String?,
    val urlItalian: String?,
    val urlSpanish: String?,
    val urlRussian: String?,
    val urlUkrainian: String?,
    val urlBelarusian: String?
) {
    companion object {
        suspend fun getAll(): List<Linktree> =
            MariaDbRepository().getAllLinks()

        suspend fun getById(id: Int): Linktree =
            MariaDbRepository().getLinktree(id)
    }
}
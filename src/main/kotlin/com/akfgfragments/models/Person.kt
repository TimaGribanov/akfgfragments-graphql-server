package com.akfgfragments.models

import com.akfgfragments.db.model.MariaDbRepository
import com.expediagroup.graphql.generator.annotations.GraphQLDescription

@GraphQLDescription("A model to describe the Person entity")
data class Person(
    val id: Int,
    val nameJapanese: String,
    val nameEnglish: String,
    val nameRussian: String?,
    val nameUkrainian: String?,
    val nameBelarusian: String?
) {
    companion object {
        suspend fun getAll(): List<Person> =
            MariaDbRepository().getAllPeople()

        suspend fun getByName(name: String): Person =
            MariaDbRepository().getPerson(name)
    }
}
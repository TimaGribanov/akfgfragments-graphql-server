package com.akfgfragments.schema

import com.akfgfragments.models.Person
import com.expediagroup.graphql.server.operations.Query

class PersonQueryService : Query {
    @Suppress("unused")
    suspend fun getAllPeople(): List<Person> =
        Person.getAllPeople()

    @Suppress("unused")
    suspend fun getPersonByName(name: String): Person =
        Person.getPersonByName(name)
}
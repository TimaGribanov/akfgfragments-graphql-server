package com.akfgfragments.schema

import com.akfgfragments.models.Linktree
import com.expediagroup.graphql.server.operations.Query

class LinktreeQueryService : Query {
    @Suppress("unused")
    suspend fun getAllLinks(): List<Linktree> =
        Linktree.getAll()

    @Suppress("unused")
    suspend fun getLinktree(id: Int): Linktree =
        Linktree.getById(id)
}
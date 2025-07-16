package com.akfgfragments.schema.dataloaders

import com.akfgfragments.schema.models.Release
import com.expediagroup.graphql.dataloader.KotlinDataLoader
import graphql.GraphQLContext
import kotlinx.coroutines.runBlocking
import org.dataloader.DataLoader
import org.dataloader.DataLoaderFactory
import java.util.concurrent.CompletableFuture

val ReleaseDataLoader = object : KotlinDataLoader<Int, Release?> {
    override val dataLoaderName = "RELEASE_LOADER"
    override fun getDataLoader(graphQLContext: GraphQLContext): DataLoader<Int, Release?> =
        DataLoaderFactory.newDataLoader { ids ->
            CompletableFuture.supplyAsync {
                runBlocking { Release.search(ids).toMutableList() }
            }
        }
}
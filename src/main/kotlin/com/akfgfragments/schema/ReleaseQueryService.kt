package com.akfgfragments.schema

import com.akfgfragments.schema.dataloaders.ReleaseDataLoader
import com.akfgfragments.schema.models.Release
import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.server.extensions.getValuesFromDataLoader
import com.expediagroup.graphql.server.operations.Query
import graphql.schema.DataFetchingEnvironment
import java.util.concurrent.CompletableFuture

//class ReleaseQueryService : Query {
//    @GraphQLDescription("Return a list of releases based on ReleaseSearchParams")
//    fun searchReleases(params: ReleaseSearchParams, dfe: DataFetchingEnvironment): CompletableFuture<Release> {
//        dfe.getValuesFromDataLoader(ReleaseDataLoader.dataLoaderName, params.ids)
//    }
//}

data class ReleaseSearchParams(val ids: List<Int>)
package com.akfgfragments.schema

import com.akfgfragments.models.MusicVideo
import com.expediagroup.graphql.server.operations.Query

class MusicVideoQueryService : Query {
    @Suppress("unused")
    suspend fun getAllMusicVideos(): List<MusicVideo> =
        MusicVideo.getAll()

    @Suppress("unused")
    suspend fun getMusicVideoByTitle(title: String): MusicVideo =
        MusicVideo.getByTitle(title)
}
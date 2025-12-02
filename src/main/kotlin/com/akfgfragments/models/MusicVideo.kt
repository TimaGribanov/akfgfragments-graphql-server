package com.akfgfragments.models

import com.akfgfragments.db.model.MariaDbRepository
import com.expediagroup.graphql.generator.annotations.GraphQLDescription

enum class MusicVideoType(val value: String) {
    YOUTUBE("youtube"),
    LOCAL("local");

    companion object {
        private val map = entries.associateBy(MusicVideoType::value)
        infix fun from(entry: String): MusicVideoType = map[entry]!!
    }
}

@GraphQLDescription("A model to describe the Music Video entity")
data class MusicVideo(
    val id: Int,
    val mvTitle: String,
    val song: String,
    val director: String?,
    val year: Int,
    val url: String,
    val type: MusicVideoType
) {
    companion object {
        suspend fun getAll(): List<MusicVideo> =
            MariaDbRepository().getAllMusicVideos()

        suspend fun getByTitle(title: String): MusicVideo =
            MariaDbRepository().getMusicVideoByTitle(title)
    }
}
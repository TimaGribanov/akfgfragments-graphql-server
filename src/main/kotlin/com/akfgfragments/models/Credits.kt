package com.akfgfragments.models

import com.akfgfragments.db.model.MariaDbRepository

data class Credits(
    val id: Int,
    val relationType: CreditedEntryType,
    val releaseOrSong: CreditedEntry,
    val creditType: String,
    val person: Person
) {
    companion object {
        suspend fun getCreditsForSong(title: String): Credits = getCredits(CreditedEntryType.SONG, title)

        suspend fun getCreditsForRelease(title: String): Credits = getCredits(CreditedEntryType.RELEASE, title)

        suspend fun getCredits(type: CreditedEntryType, title: String): Credits =
            MariaDbRepository().getCredits(type, title)
    }
}
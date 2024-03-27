package com.example.challengekosmosusfr.data.remote.model

import com.google.gson.annotations.SerializedName

data class GetCharactersResponse(
    @SerializedName(value = "info")
    val infoPageRemote: InfoPageRemote,
    @SerializedName(value = "results")
    val characterRemotes: List<CharacterRemote>
)
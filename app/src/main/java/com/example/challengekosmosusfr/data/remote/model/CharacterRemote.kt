package com.example.challengekosmosusfr.data.remote.model

import com.example.challengekosmosusfr.domain.Character
import com.google.gson.annotations.SerializedName

data class CharacterRemote(
    val id: Int,
    val name: String,
    val image: String,
    val created: String,
    @SerializedName(value = "episode")
    val episodes: List<String>,
    val gender: String,
    @SerializedName(value = "location")
    val locationRemote: LocationRemote,
    @SerializedName(value = "origin")
    val originRemote: OriginRemote,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)

fun CharacterRemote.toCharacter() =
    Character(
        id = this.id,
        name = this.name,
        image = this.image,
        gender = this.gender,
        created = this.created,
        episode = this.episodes,
        location = this.locationRemote,
        origin = this.originRemote,
        species = this.species,
        status = this.status,
        type = this.type,
        url = this.url
    )
package com.example.challengekosmosusfr.domain

import com.example.challengekosmosusfr.data.remote.model.LocationRemote
import com.example.challengekosmosusfr.data.remote.model.OriginRemote

data class Character(
    val id: Int,
    val name: String,
    val image: String,
    val created: String,
    val episode: List<String>,
    val gender: String,
    val location: LocationRemote,
    val origin: OriginRemote,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)

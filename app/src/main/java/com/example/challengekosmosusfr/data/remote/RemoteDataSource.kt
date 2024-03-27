package com.example.challengekosmosusfr.data.remote

import com.example.challengekosmosusfr.data.remote.model.CharacterRemote
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val rickAndMortyServices: RickAndMortyServices
) {
    suspend fun getCharacters(pageNumber: Int) : List<CharacterRemote> {
        return rickAndMortyServices.getCharacters(pageNumber).characterRemotes ?: emptyList()
    }
}
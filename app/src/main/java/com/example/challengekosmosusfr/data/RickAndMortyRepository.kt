package com.example.challengekosmosusfr.data

import com.example.challengekosmosusfr.data.remote.RemoteDataSource
import com.example.challengekosmosusfr.data.remote.model.toCharacter
import com.example.challengekosmosusfr.domain.Character
import javax.inject.Inject

class RickAndMortyRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
){
    suspend fun getCharacters(pageNumber: Int) : List<Character> {
        return remoteDataSource.getCharacters(pageNumber).map { itCharacterRemote ->
            itCharacterRemote.toCharacter()
        }
    }
}
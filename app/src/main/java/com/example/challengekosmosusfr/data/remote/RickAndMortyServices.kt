package com.example.challengekosmosusfr.data.remote

import com.example.challengekosmosusfr.data.remote.model.GetCharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyServices {
    @GET("character/?")
    suspend fun getCharacters(
        @Query("page") pageNumber: Int
    ) : GetCharactersResponse
}
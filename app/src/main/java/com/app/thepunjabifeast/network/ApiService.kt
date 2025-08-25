package com.app.thepunjabifeast.network

import com.app.thepunjabifeast.data.model.SignInRequest
import com.app.thepunjabifeast.data.model.SignInResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

  /*  @POST("users/")
    suspend fun signUp(): RegisterRequest*/

    @POST("auth/login")
    suspend fun signIn(@Body request: SignInRequest): SignInResponse


    /*  @GET("users")
      suspend fun getUsers(): List<User>*/

    /*@GET("pokemon/")
    suspend fun getPokemonsByOffset(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokeListResponse*/
}
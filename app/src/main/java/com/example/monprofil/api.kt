package com.example.monprofil

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {


    //acteurs
    @GET("trending/person/week")
    suspend fun lastActors(@Query("api_key") apikey: String):TmbResultActors
    @GET("search/person")
    suspend fun actorsKeyWord(@Query("api_key")apikey: String,@Query("query") motcle: String) : TmbResultActors


    //series
    @GET("trending/tv/week")
    suspend fun lastSeries(@Query("api_key")apikey: String) : TmdbResultsSeries
    @GET("search/tv")
    suspend fun seriesKeyWord(@Query("api_key")apikey: String,@Query("query") motcle: String) : TmdbResultsSeries
    @GET ("tv/{id}")
    suspend fun serieInfo (@Path("id") id: String, @Query("api_key")apikey: String, @Query("append_to_response")append_to_response:String):Series


    //movies
    @GET("search/movie")
    suspend fun movieKeyWord(@Query("api_key")apikey: String,@Query("query") motcle: String) : TmdbResult
    @GET("trending/movie/week")
    suspend fun lastMovies(@Query("api_key")apikey: String) : TmdbResult

    @GET ("movie/{id}")
    suspend fun movieInfo (@Path("id") id: String,@Query("api_key")apikey: String,@Query("append_to_response")append_to_response:String):Movie







}
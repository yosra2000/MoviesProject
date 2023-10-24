package com.example.monprofil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel: ViewModel() {
    val movies = MutableStateFlow<List<Movie>>(listOf())
    val isLoading = MutableStateFlow(false)
    val actors=MutableStateFlow<List<Actors>>(listOf())
    val series=MutableStateFlow<List<Series>>(listOf())
    val serie=MutableStateFlow<Series?>(null)
    val movie=MutableStateFlow<Movie?>(null)
    val api_key = "9b47a3879875a76cf6b1d94d45ab56b4"
    val append_to_response="credits"
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();
    val api = retrofit.create(Api::class.java)

    init{
        viewModelScope.launch {
            isLoading.value = true
            joinAll(
                lastActors(),
                lastSeries(),
                lastMovies()
            )
            isLoading.value = false

        }

    }


    //acteurs
    private fun lastActors(): Job{
       return viewModelScope.launch {
            actors.value = api.lastActors(api_key).results
        }
    }
    fun SeachActors(motcle: String){
        viewModelScope.launch{
            actors.value = api.actorsKeyWord(api_key, motcle).results
        }
    }


    //SERIRES
    fun lastSeries() : Job{

       return viewModelScope.launch{
            series.value = api.lastSeries(api_key).results

       }
    }

    fun SeachSeries(motcle: String){
        viewModelScope.launch{
            series.value = api.seriesKeyWord(api_key, motcle).results
        }
    }



    fun serieInfo(id: String) {
        viewModelScope.launch{
            serie.value = api.serieInfo(id, api_key ,append_to_response)
        }
    }


    //films
        private fun lastMovies(): Job{
            //Log.e("coucou", "start:"+movies.value.size.toString())
            return viewModelScope.launch{
                movies.value = api.lastMovies(api_key).results
                //Log.e("coucou", "serach:"+movies.value.size.toString())
            }
        }

        fun SearchKeyWord(motcle: String){
            viewModelScope.launch{
                movies.value = api.movieKeyWord(api_key, motcle).results
            }
}


        fun movieDetails(id: String): Job{
        return viewModelScope.launch{
                movie.value = api.movieInfo(id, api_key ,append_to_response)
            }
        }






}
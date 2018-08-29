package com.digitalhouse.mvp.Model.DAO.Internet.Retrofit;

import com.digitalhouse.mvp.Model.POJO.PokemonContainer;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServicePokemon {

    @GET("api/v2/pokemon")
    Call<PokemonContainer> get();
}

package com.digitalhouse.mvp.Model.DAO.Internet;

import android.support.annotation.NonNull;

import com.digitalhouse.mvp.Model.DAO.DaoPokemon;
import com.digitalhouse.mvp.Model.DAO.Internet.Retrofit.ServicePokemon;
import com.digitalhouse.mvp.Utils.AppExecutors;
import com.digitalhouse.mvp.Model.POJO.Pokemon;
import com.digitalhouse.mvp.Model.POJO.PokemonContainer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaoPokemonInternet implements DaoPokemon {

    // Atributos
    private static DaoPokemonInternet instance;

    private ServicePokemon servicePokemon;
    private AppExecutors mAppExecutors;

    // Fabric
    public static DaoPokemonInternet getInstance(@NonNull ServicePokemon servicePokemon, @NonNull AppExecutors mAppExecutors) {
        if (instance == null){
            instance = new DaoPokemonInternet(servicePokemon, mAppExecutors);
        }
        return instance;
    }

    // Constructor
    private DaoPokemonInternet(@NonNull ServicePokemon servicePokemon, @NonNull AppExecutors mAppExecutors) {
        this.servicePokemon = servicePokemon;
        this.mAppExecutors = mAppExecutors;
    }


    @Override
    public void loadPokemon(final ResultListener<List<Pokemon>> callback) {
            servicePokemon.get().enqueue(new Callback<PokemonContainer>() {
                @Override
                public void onResponse(Call<PokemonContainer> call, Response<PokemonContainer> response) {
                    callback.onFinish(response.body().getListaPokemon());
                }

                @Override
                public void onFailure(Call<PokemonContainer> call, Throwable t) {
                    callback.onError();
                }
            });
    }

    @Override
    public void refreshPokemon() {
        // NOT REQUIRE
    }
}

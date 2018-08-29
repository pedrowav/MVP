package com.digitalhouse.mvp;

import android.content.Context;
import android.support.annotation.NonNull;

import com.digitalhouse.mvp.Model.DAO.Internet.DaoPokemonInternet;
import com.digitalhouse.mvp.Model.DAO.Internet.Retrofit.RetrofitHelper;
import com.digitalhouse.mvp.Model.DAO.Internet.Retrofit.ServicePokemon;
import com.digitalhouse.mvp.Utils.AppExecutors;
import com.digitalhouse.mvp.Model.DAO.RepositoryPokemon;

import retrofit2.Retrofit;

import static com.google.common.base.Preconditions.checkNotNull;

public class Injection {
    private static final String BASE_URL = "https://pokeapi.co";

    public static RepositoryPokemon providePokemonRepository(@NonNull Context context) {
        // El context era para base de datos que no uso.
        checkNotNull(context);

        //Creación del cliente de retrofit
        Retrofit retrofit = RetrofitHelper.getRetrofit(BASE_URL, null);
        ServicePokemon servicePokemon = retrofit.create(ServicePokemon.class);

        return RepositoryPokemon.getInstance(
                DaoPokemonInternet.getInstance(servicePokemon, new AppExecutors()));
    }
}

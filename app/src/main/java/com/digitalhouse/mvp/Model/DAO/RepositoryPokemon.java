package com.digitalhouse.mvp.Model.DAO;

import android.support.annotation.NonNull;

import com.digitalhouse.mvp.Model.DAO.Internet.DaoPokemonInternet;
import com.digitalhouse.mvp.Model.POJO.Pokemon;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class RepositoryPokemon implements DaoPokemon {

    // Atributos
    private static RepositoryPokemon instance;

    private DaoPokemonInternet myDaoPokemonInternet;
    private Map<String, Pokemon> mCachedPokemon;

    private boolean mCacheIsDirty = false;


    // Fabric
    public static RepositoryPokemon getInstance(DaoPokemonInternet myRemoteDataSource) {
        if (instance == null){
            instance = new RepositoryPokemon(myRemoteDataSource);
        }
        return instance;
    }

    // Constructor
    private RepositoryPokemon(DaoPokemonInternet myDaoPokemonInternet) {
        this.myDaoPokemonInternet = myDaoPokemonInternet;
    }

    @Override
    public void loadPokemon(ResultListener<List<Pokemon>> callback) {
        checkNotNull(callback);

        // Respond immediately with cache if available and not dirty
        if (mCachedPokemon != null && !mCacheIsDirty) {
            callback.onFinish(new ArrayList<>(mCachedPokemon.values()));
            return;
        }

        if (mCacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.
            getPokemonsFromDaoInternet(callback);
        } else {
            // VOY AL LOCAL
            // Si esta vacio
            if (true){
                getPokemonsFromDaoInternet(callback);
            }

        }
    }

    @Override
    public void refreshPokemon() {
        mCacheIsDirty = true;
    }


    private void getPokemonsFromDaoInternet(@NonNull final ResultListener<List<Pokemon>> callback) {
        myDaoPokemonInternet.loadPokemon(new ResultListener<List<Pokemon>>() {
            @Override
            public void onFinish(List<Pokemon> result) {
                refreshCache(result);
                //refreshLocalDataSource(result);
                callback.onFinish(new ArrayList<>(mCachedPokemon.values()));
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }


    private void refreshCache(List<Pokemon> result) {
        if (mCachedPokemon == null) {
            mCachedPokemon = new LinkedHashMap<>();
        }
        mCachedPokemon.clear();
        for (Pokemon pokemon : result) {
            mCachedPokemon.put(pokemon.getName(), pokemon);
        }
        mCacheIsDirty = false;
    }
}

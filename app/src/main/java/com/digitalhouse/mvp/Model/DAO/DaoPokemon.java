package com.digitalhouse.mvp.Model.DAO;

import com.digitalhouse.mvp.Model.POJO.Pokemon;

import java.util.List;

public interface DaoPokemon {

    interface ResultListener<T> {
        void onFinish(T result);
        void onError();
    }

    void loadPokemon(ResultListener<List<Pokemon>> callback);

    void refreshPokemon();
}

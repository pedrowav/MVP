package com.digitalhouse.mvp.Pokemon;

import com.digitalhouse.mvp.BasePresenter;
import com.digitalhouse.mvp.BaseView;
import com.digitalhouse.mvp.Model.POJO.Pokemon;

import java.util.List;

public interface PokemonContract {
    interface View extends BaseView<Presenter> {
        void showPokemon(List<Pokemon> pokemons);
    }


    interface Presenter extends BasePresenter {
        void loadPokemon(boolean forceUpdate);
    }
}

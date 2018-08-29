package com.digitalhouse.mvp.Model.POJO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonContainer {
    @SerializedName("results")
    private List<Pokemon> listaPokemon;

    public List<Pokemon> getListaPokemon() {
        return listaPokemon;
    }
}

package com.digitalhouse.mvp.Pokemon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.digitalhouse.mvp.Injection;
import com.digitalhouse.mvp.Model.POJO.Pokemon;
import com.digitalhouse.mvp.R;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainActivity extends AppCompatActivity implements PokemonContract.View {

    // Atributos
    private PokemonContract.Presenter myPresenter;
    private PokemonRecyclerAdapter pokemonRecyclerAdapter;
    private boolean isActive = false;

    @Override
    protected void onResume() {
        super.onResume();
        myPresenter.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        isActive = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isActive = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creo e inicializo el PokemonPresenter
        myPresenter = new PokemonPresenter(Injection.providePokemonRepository(this), this);

        // Inicializo el RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        pokemonRecyclerAdapter = new PokemonRecyclerAdapter(new ArrayList<Pokemon>());
        recyclerView.setAdapter(pokemonRecyclerAdapter);
    }

    @Override
    public void setPresenter(PokemonContract.Presenter presenter) {
        myPresenter = checkNotNull(presenter);
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void showPokemon(List<Pokemon> pokemons) {
        pokemonRecyclerAdapter.setListPokemon(pokemons);
    }
}

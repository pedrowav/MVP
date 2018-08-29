package com.digitalhouse.mvp.Pokemon;

import com.digitalhouse.mvp.Model.DAO.DaoPokemon;
import com.digitalhouse.mvp.Model.DAO.RepositoryPokemon;
import com.digitalhouse.mvp.Model.POJO.Pokemon;

import java.util.List;

public class PokemonPresenter implements PokemonContract.Presenter {
    // Atributos
    private final PokemonContract.View myView;
    private final RepositoryPokemon myRepositoryPokemon;

    private boolean mFirstLoad = true;

    // Constructor
    // Cuando creo el presenter le tengo que asignar una view y un repositorio
    public PokemonPresenter(RepositoryPokemon repositoryPokemon, PokemonContract.View view){
        myRepositoryPokemon = repositoryPokemon;
        myView = view;
        myView.setPresenter(this);
    }

    // Responsabilidades
    @Override
    public void start() {
        loadPokemon(false);
    }

    @Override
    public void loadPokemon(boolean forceUpdate) {
        // Simplification for sample: a network reload will be forced on first load.
        loadPokemon(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    public void loadPokemon(boolean forceUpdate, final boolean showLoadingUI){
        if (showLoadingUI) {
            //myView.setLoadingIndicator(true);
        }
        if (forceUpdate) {
            myRepositoryPokemon.refreshPokemon();
        }

        myRepositoryPokemon.loadPokemon(new DaoPokemon.ResultListener<List<Pokemon>>() {
            @Override
            public void onFinish(List<Pokemon> result) {

                // Aprovechar para filtrar

                // Si no esta activa la vista, ya esta no importa el pedido.
                if (!myView.isActive()){
                    return;
                }

                if(showLoadingUI){
                    //myView.setLoadingIndicator(false);
                }

                // Process the information
                myView.showPokemon(result);
            }

            @Override
            public void onError() {
                System.out.println("Error loading pokemons");
            }
        });
    }
}

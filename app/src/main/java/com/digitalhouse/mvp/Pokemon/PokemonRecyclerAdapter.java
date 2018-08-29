package com.digitalhouse.mvp.Pokemon;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitalhouse.mvp.Model.POJO.Pokemon;
import com.digitalhouse.mvp.R;

import java.util.List;

public class PokemonRecyclerAdapter extends RecyclerView.Adapter<PokemonRecyclerAdapter.MyViewHolder> {
    private List<Pokemon> listPokemon;

    public PokemonRecyclerAdapter(List<Pokemon> listPokemon) {
        this.listPokemon = listPokemon;
    }

    public void setListPokemon(List<Pokemon> listPokemon) {
        this.listPokemon = listPokemon;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item, parent, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Pokemon pokemon = listPokemon.get(position);
        holder.cargarPokemon(pokemon);
    }

    @Override
    public int getItemCount() {
        return listPokemon.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }

        public void cargarPokemon(Pokemon pokemon){
            name.setText(pokemon.getName());
        }

    }


}

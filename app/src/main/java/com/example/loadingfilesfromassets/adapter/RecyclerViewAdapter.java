package com.example.loadingfilesfromassets.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loadingfilesfromassets.R;
import com.example.loadingfilesfromassets.model.Pokemon;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<Pokemon> pokemonList;
    private Context context;

    public RecyclerViewAdapter(Context _context, List<Pokemon> _pokemonList) {

        this.context = _context;
        this.pokemonList = _pokemonList;
    }

    public Context getContext() {

        return this.context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        // maria putrah

//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item, viewGroup, false);
//
//        return new MyViewHolder(view);

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item, viewGroup, false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Pokemon pokemon = pokemonList.get(position);
        holder.pokemonName.setText(pokemon.getName());

        switch (pokemon.getType()) {

            case "fire":
                holder.pokemonType.setImageResource(R.drawable.fire);
                break;
            case "water":
                holder.pokemonType.setImageResource(R.drawable.water);
                break;
            case "grass":
                holder.pokemonType.setImageResource(R.drawable.grass);
                break;
            default:
                holder.pokemonType.setImageResource(R.drawable.other);

        }

    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView pokemonName;
        ImageView pokemonType;

        public MyViewHolder(@NonNull View view) {
            super(view);

            pokemonName = view.findViewById(R.id.pokemon_name);
            pokemonType = view.findViewById(R.id.pokemon_type);
        }
    }
}

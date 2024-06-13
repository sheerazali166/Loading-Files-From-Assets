package com.example.loadingfilesfromassets;

import static com.example.loadingfilesfromassets.reader.JsonReader.loadJSONFromAsset;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loadingfilesfromassets.adapter.RecyclerViewAdapter;
import com.example.loadingfilesfromassets.model.Pokemon;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<Pokemon> pokemonArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);

        recyclerViewAdapter = new RecyclerViewAdapter(this, pokemonArrayList);
        mRecyclerView.setAdapter(recyclerViewAdapter);

        Log.d("TAG", "start");
        new ReadPokemonDataOnBackGround(this).execute();




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private class ReadPokemonDataOnBackGround extends AsyncTask<Void, Integer, ArrayList<Pokemon>> {

        private ProgressDialog progressDialog;

        public ReadPokemonDataOnBackGround(Context context) {
            this.progressDialog = new ProgressDialog(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog.setMessage("Reading Data...");
            progressDialog.show();
        }

        @Override
        protected ArrayList<Pokemon> doInBackground(Void... voids) {

            String pokemonJson = loadJSONFromAsset(getApplicationContext(), "pokemonlist.json");

            Gson gson = new Gson();
            Pokemon[] pokemon = gson.fromJson(pokemonJson, Pokemon[].class);

            return new ArrayList<Pokemon>(Arrays.asList(pokemon));
        }

        @Override
        protected void onPostExecute(ArrayList<Pokemon> pokemon) {
            super.onPostExecute(pokemon);

            pokemonArrayList.clear();
            Log.d("TAG",pokemon.get(0).toString());
            pokemonArrayList.addAll(pokemon);

            recyclerViewAdapter.notifyDataSetChanged();
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }
}
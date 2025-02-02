package com.example.pmdm_ut05_tarea;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HeroAdapter.SelectionModeListener {
    private RecyclerView recyclerView;
    private HeroAdapter adapter;
    private List<Hero> heroList;
    private List<Hero> selectedHeroes = new ArrayList<>();
    private FloatingActionButton fab;
    private boolean isSelectionMode = false;

    private ActivityResultLauncher<Intent> detalleHeroeLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        recyclerView = findViewById(R.id.recyclerViewHeroes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        detalleHeroeLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null && data.hasExtra("hero")) {
                            Hero updatedHero = (Hero) data.getSerializableExtra("hero");


                            for (int i = 0; i < heroList.size(); i++) {
                                if (heroList.get(i).getId() == updatedHero.getId()) {
                                    heroList.set(i, updatedHero);
                                    adapter.notifyItemChanged(i);
                                    break;
                                }
                            }
                        }
                    }
                }
        );


        heroList = Hero.generateHeroes();
        adapter = new HeroAdapter(heroList, detalleHeroeLauncher, this);
        recyclerView.setAdapter(adapter);


        fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> openAddHeroActivity());
    }

    private void openAddHeroActivity() {
        Intent intent = new Intent(this, AddHeroActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_previous:
                moveSelection(-1);
                return true;
            case R.id.action_next:
                moveSelection(1);
                return true;
            case R.id.action_about:
                showAppInfo();
                return true;
            case R.id.action_delete:
                deleteSelectedHeroes();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void moveSelection(int direction) {
        if (!heroList.isEmpty()) {
            Hero movedHero = heroList.remove(0);
            heroList.add(movedHero);
            adapter.notifyDataSetChanged();
        }
    }


    private void showAppInfo() {
        Snackbar.make(recyclerView, "We can be heroes v1.0 (1)", Snackbar.LENGTH_LONG).show();
    }


    private void deleteSelectedHeroes() {
        heroList.removeAll(selectedHeroes);
        selectedHeroes.clear();
        adapter.notifyDataSetChanged();
        updateSelectionMode(false);
    }


    private void updateSelectionMode(boolean isActive) {
        isSelectionMode = isActive;
        fab.setVisibility(isActive ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onSelectionModeChanged(boolean isActive) {
        updateSelectionMode(isActive);
    }

}


package com.example.pmdm_ut05_tarea;



import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.HeroViewHolder> {
    private List<Hero> heroList;
    private ActivityResultLauncher<Intent> detalleHeroeLauncher;
    private SelectionModeListener listener;

    public HeroAdapter(List<Hero> heroList, ActivityResultLauncher<Intent> detalleHeroeLauncher, SelectionModeListener listener) {
        this.heroList = heroList;
        this.detalleHeroeLauncher = detalleHeroeLauncher;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hero, parent, false);
        return new HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroViewHolder holder, int position) {
        Hero hero = heroList.get(position);

        holder.heroId.setText(String.valueOf(hero.getId()));
        holder.heroName.setText(hero.getHeroName());
        holder.realName.setText(hero.getRealName());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetalleHeroeActivity.class);
            intent.putExtra("hero", hero);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    public static class HeroViewHolder extends RecyclerView.ViewHolder {
        TextView heroId, heroName, realName;

        public HeroViewHolder(@NonNull View itemView) {
            super(itemView);
            heroId = itemView.findViewById(R.id.textHeroId);
            heroName = itemView.findViewById(R.id.textHeroName);
            realName = itemView.findViewById(R.id.textRealName);
        }
    }

    public interface SelectionModeListener {
        void onSelectionModeChanged(boolean isActive);
    }
}

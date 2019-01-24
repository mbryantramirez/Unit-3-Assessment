package org.pursuit.unit_03_assessment.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.pursuit.unit_03_assessment.model.Planet;
import org.pursuit.unit_03_assessment.R;

import java.util.ArrayList;
import java.util.List;

public class PlanetAdapter extends RecyclerView.Adapter<PlanetViewHolder> {
    List<Planet> planetList = new ArrayList<>();

    public PlanetAdapter(List<Planet> planetList) {
        this.planetList = planetList;
    }

    @NonNull
    @Override
    public PlanetViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.planet_item_view, viewGroup, false);
        return new PlanetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetViewHolder planetViewHolder, int i) {
        planetViewHolder.onBind(planetList.get(i));
    }

    @Override
    public int getItemCount() {
        return planetList.size();
    }
}

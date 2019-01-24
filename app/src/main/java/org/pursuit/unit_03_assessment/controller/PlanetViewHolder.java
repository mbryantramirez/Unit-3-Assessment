package org.pursuit.unit_03_assessment.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.pursuit.unit_03_assessment.DisplayActivity;
import org.pursuit.unit_03_assessment.R;
import org.pursuit.unit_03_assessment.model.Planet;

public class PlanetViewHolder extends RecyclerView.ViewHolder {
    protected TextView nameTv;

    public PlanetViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTv = itemView.findViewById(R.id.planet_textview);
    }

    public void onBind(final Planet planet) {
        nameTv.setText(planet.getName());
        nameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = new Bundle();
                extras.putString("name", planet.getName());
                extras.putInt("number", planet.getNumber());
                extras.putString("image", planet.getImage());
                Intent intent = new Intent(itemView.getContext(), DisplayActivity.class);
                intent.putExtras(extras);
                v.getContext().startActivity(intent);
            }
        });
    }
}

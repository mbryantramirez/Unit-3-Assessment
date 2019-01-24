package org.pursuit.unit_03_assessment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.pursuit.unit_03_assessment.controller.PlanetAdapter;
import org.pursuit.unit_03_assessment.model.Planet;
import org.pursuit.unit_03_assessment.model.PlanetList;
import org.pursuit.unit_03_assessment.network.PlanetService;
import org.pursuit.unit_03_assessment.network.myRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RecyclerActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        retrofit = new myRetrofit().getInstance();
        PlanetService planetService = retrofit.create(PlanetService.class);
        Call<PlanetList> planetListCall = planetService.getMyPlanetList();
        planetListCall.enqueue(new Callback<PlanetList>() {
            @Override
            public void onResponse(Call<PlanetList> call, Response<PlanetList> response) {
                List<Planet> myPlanetsList = response.body().getMyPlanetList();
                recyclerView = findViewById(R.id.recycler_view);
                recyclerView.setAdapter(new PlanetAdapter(myPlanetsList));
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }

            @Override
            public void onFailure(Call<PlanetList> call, Throwable t) {

            }
        });
    }
}

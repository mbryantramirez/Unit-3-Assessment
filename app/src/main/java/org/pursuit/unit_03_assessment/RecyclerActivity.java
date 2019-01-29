package org.pursuit.unit_03_assessment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
    private static final String TAG = "Unit 3 Assessment";
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
                /**
                 * if your trying to get something from your response body you should wrap it in a null check otherwise your app will crash
                 *  check if response.body is null before you do anything with the response data
                 */
                List<Planet> myPlanetsList = response.body().getMyPlanetList();
                recyclerView = findViewById(R.id.recycler_view);
                recyclerView.setAdapter(new PlanetAdapter(myPlanetsList));
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }

            @Override
            public void onFailure(Call<PlanetList> call, Throwable t) {
                Log.d(TAG, "onFailure: Something went wrong" + t.getMessage());
            }
        });
    }
}

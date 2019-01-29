package org.pursuit.unit_03_assessment.model;

import com.google.gson.annotations.SerializedName;
import org.pursuit.unit_03_assessment.model.Planet;

import java.util.ArrayList;
import java.util.List;

public class PlanetList {

    /**
     * initializing list here is redundant
     * the name of this field should be the same as the field in the JSON response in this case the JSONArray is planets so the list should have that name
     *
     * using the @SerializedName("planets) annotation and passing in the key string from the JSON response would also work
     *
     */
    @SerializedName("planets")
    private List<Planet> myPlanetList = new ArrayList<>();

    public PlanetList(List<Planet> myPlanetList) {
        this.myPlanetList = myPlanetList;
    }

    public List<Planet> getMyPlanetList() {
        return myPlanetList;
    }

    public void setMyPlanetList(List<Planet> myPlanetList) {
        this.myPlanetList = myPlanetList;
    }
}

package org.pursuit.unit_03_assessment.model;

import org.pursuit.unit_03_assessment.model.Planet;

import java.util.ArrayList;
import java.util.List;

public class PlanetList {
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

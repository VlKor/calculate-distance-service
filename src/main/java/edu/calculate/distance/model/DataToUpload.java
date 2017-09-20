package edu.calculate.distance.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "dataToUpload")
public class DataToUpload {
    List<DistanceDTO> distances = new ArrayList<>();
    List<City> cities = new ArrayList<>();

    public DataToUpload() {
    }

    public DataToUpload(List<DistanceDTO> distances, List<City> cities) {
        this.distances = distances;
        this.cities = cities;
    }

    public List<DistanceDTO> getDistances() {
        return distances;
    }

    @XmlElement(name = "distance")
    public void setDistances(List<DistanceDTO> distances) {
        this.distances = distances;
    }

    public List<City> getCities() {
        return cities;
    }

    @XmlElement(name = "city")
    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "DataToUpload{" +
                "distances=" + distances +
                ", cities=" + cities +
                '}';
    }
}

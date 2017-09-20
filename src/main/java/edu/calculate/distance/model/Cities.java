package edu.calculate.distance.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "cities")
public class Cities {
    List<City> cities = new ArrayList<>();

    public Cities(List<City> cities) {
        this.cities = cities;
    }

    public Cities() {
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
        return "Cities{" +
                "cities=" + cities +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cities cities1 = (Cities) o;
        return Objects.equals(cities, cities1.cities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cities);
    }
}

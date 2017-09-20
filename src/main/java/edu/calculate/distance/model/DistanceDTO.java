package edu.calculate.distance.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "distance")
public class DistanceDTO {
    private Integer fromCityId;
    private Integer toCityId;
    private String fromCity;
    private String toCity;
    private Integer distance;

    public DistanceDTO() {
    }

    public DistanceDTO(String fromCity, String toCity, Integer distance) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.distance = distance;
    }

    public String getFromCity() {
        return fromCity;
    }

    @XmlElement
    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    @XmlElement
    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public Integer getDistance() {
        return distance;
    }

    @XmlElement
    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getFromCityId() {
        return fromCityId;
    }

    public void setFromCityId(Integer fromCityId) {
        this.fromCityId = fromCityId;
    }

    public Integer getToCityId() {
        return toCityId;
    }

    public void setToCityId(Integer toCityId) {
        this.toCityId = toCityId;
    }

    @Override
    public String toString() {
        return "DistanceDTO{" +
                "fromCityId=" + fromCityId +
                ", toCityId=" + toCityId +
                ", fromCity='" + fromCity + '\'' +
                ", toCity='" + toCity + '\'' +
                ", distance=" + distance +
                '}';
    }
}

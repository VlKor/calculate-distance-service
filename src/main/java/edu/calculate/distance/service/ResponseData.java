package edu.calculate.distance.service;

import edu.calculate.distance.CalculationType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "response")
public class ResponseData {

    private CalculationType type;
    private String fromCity;
    private String toCity;
    private Integer distance;
    private String error;

    public ResponseData() {
    }

    public ResponseData(CalculationType type, String fromCity, String toCity, Integer distance, String error) {
        this.type = type;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.distance = distance;
        this.error = error;
    }

    public CalculationType getType() {
        return type;
    }

    @XmlElement
    public void setType(CalculationType type) {
        this.type = type;
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

    public String getError() {
        return error;
    }

    @XmlElement
    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "type=" + type +
                ", fromCity='" + fromCity + '\'' +
                ", toCity='" + toCity + '\'' +
                ", distance=" + distance +
                ", error='" + error + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseData that = (ResponseData) o;
        return type == that.type &&
                Objects.equals(fromCity, that.fromCity) &&
                Objects.equals(toCity, that.toCity) &&
                Objects.equals(distance, that.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, fromCity, toCity, distance);
    }
}

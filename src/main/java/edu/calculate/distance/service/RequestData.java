package edu.calculate.distance.service;

import edu.calculate.distance.CalculationType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "request")
public class RequestData {

    private CalculationType type;
    private String fromCity;
    private String toCity;

    public RequestData() {
    }

    public RequestData(CalculationType type, String fromCity, String toCity) {
        this.type = type;
        this.fromCity = fromCity;
        this.toCity = toCity;
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

    @Override
    public String toString() {
        return "RequestData{" +
                "type=" + type +
                ", fromCity='" + fromCity + '\'' +
                ", toCity='" + toCity + '\'' +
                '}';
    }
}

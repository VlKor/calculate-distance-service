package edu.calculate.distance.model;

import javax.persistence.*;

@Entity
@Table(name = "distance")
public class Distance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "from_city")
    private Integer fromCity;

    @Column(name = "to_city")
    private Integer toCity;

    @Column(name = "distance")
    private Integer distance;

    public Distance() {
    }

    public Distance(Integer id, Integer fromCity, Integer toCity, Integer distance) {
        this.id = id;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.distance = distance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFrom_city() {
        return fromCity;
    }

    public void setFrom_city(Integer from_city) {
        this.fromCity = from_city;
    }

    public Integer getTo_city() {
        return toCity;
    }

    public void setTo_city(Integer to_city) {
        this.toCity = to_city;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Distance{" +
                "id=" + id +
                ", fromCity=" + fromCity +
                ", toCity=" + toCity +
                ", distance=" + distance +
                '}';
    }
}
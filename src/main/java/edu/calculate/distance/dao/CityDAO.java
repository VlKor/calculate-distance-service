package edu.calculate.distance.dao;

import edu.calculate.distance.model.Cities;
import edu.calculate.distance.model.City;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class CityDAO {

    private final EntityManager entityManager;

    public CityDAO() {
        entityManager = EntityManagerCreator.createManger();
    }

    public Cities getCities() {
        List<City> cities = entityManager.createQuery("from City", City.class).getResultList();
        return new Cities(cities);
    }

    public City getCityByName(String name) {
        return entityManager.createQuery("select city from City city where city.name = :name", City.class)
                .setParameter("name", name).getSingleResult();
    }

    public void updateOrCreate(City city) {
        entityManager.getTransaction().begin();
        try {
            City updatedCity = getCityByName(city.getName());
            updatedCity.setLatitude(city.getLatitude());
            updatedCity.setLongitude(city.getLongitude());
            entityManager.merge(updatedCity);
        } catch (NoResultException e) {
            entityManager.persist(city);
        }
        entityManager.getTransaction().commit();
    }
}

package edu.calculate.distance.dao;

import edu.calculate.distance.model.Distance;
import edu.calculate.distance.model.DistanceDTO;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class DistanceDAO {

    private final EntityManager entityManager;

    public DistanceDAO() {
        this.entityManager = EntityManagerCreator.createManger();
    }

    public Distance getDistance(Integer fromCity, Integer toCity) {
        String query = "select distance " +
                "from Distance distance " +
                "where ((distance.fromCity= :fromCity and distance.toCity= :toCity) " +
                "or (distance.fromCity = :toCity and distance.toCity= :fromCity))" +
                "and distance.distance is not null";
        return entityManager.createQuery(query, Distance.class)
                .setParameter("fromCity", fromCity)
                .setParameter("toCity", toCity)
                .getSingleResult();
    }

    public void updateOrCreate(DistanceDTO distanceDTO) {
        entityManager.getTransaction().begin();
        try {
            Distance distance = getDistance(distanceDTO.getFromCityId(), distanceDTO.getToCityId());
            distance.setDistance(distanceDTO.getDistance());
            entityManager.merge(distance);
        } catch (NoResultException e) {
            Distance distance = new Distance(null, distanceDTO.getFromCityId(), distanceDTO.getToCityId(), distanceDTO.getDistance());
            entityManager.persist(distance);
        }
        entityManager.getTransaction().commit();
    }
}

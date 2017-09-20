package edu.calculate.distance.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerCreator {
    public static EntityManager createManger() {
        return Persistence.createEntityManagerFactory("distance-calculator").createEntityManager();
    }
}

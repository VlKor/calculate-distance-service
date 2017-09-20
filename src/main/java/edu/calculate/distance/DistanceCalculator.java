package edu.calculate.distance;

import edu.calculate.distance.dao.CityDAO;
import edu.calculate.distance.dao.DistanceDAO;
import edu.calculate.distance.model.City;
import edu.calculate.distance.model.Distance;
import edu.calculate.distance.service.RequestData;
import edu.calculate.distance.service.ResponseData;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static edu.calculate.distance.CalculationType.CROWFLIGHT;
import static edu.calculate.distance.CalculationType.DISTANCE_MATRIX;
import static java.lang.Math.*;
import static java.util.Objects.nonNull;

public class DistanceCalculator {

    private static final int RADIUS_OF_EARTH = 6371;
    private DistanceDAO distanceDAO;
    private CityDAO cityDAO;

    public DistanceCalculator(DistanceDAO distanceDAO, CityDAO cityDAO) {
        this.distanceDAO = distanceDAO;
        this.cityDAO = cityDAO;
    }

    public List<ResponseData> process(List<RequestData> request) {
        List<ResponseData> response = new ArrayList<>();
        for (RequestData data : request) {
            try {
                checkArgument(nonNull(data.getType()), "unknown or null calculation type");
                checkArgument(nonNull(data.getFromCity()), "fromCity can't be null");
                checkArgument(nonNull(data.getToCity()), "toCity can't be null");
            } catch (IllegalArgumentException e) {
                response.add(new ResponseData(data.getType(), data.getFromCity(), data.getToCity(), null, e.getMessage()));
                continue;
            }
            switch (data.getType()) {
                case DISTANCE_MATRIX:
                    response.add(getDistanceFromDB(data));
                    break;
                case CROWFLIGHT:
                    response.add(calculateDistance(data));
                    break;
                case ALL:
                    response.add(getDistanceFromDB(data));
                    response.add(calculateDistance(data));
                    break;
            }
        }
        return response;
    }

    private ResponseData calculateDistance(RequestData request) {
        String fromCityName = request.getFromCity();
        String toCityName = request.getToCity();
        City fromCity;
        City toCity;
        try {
            fromCity = cityDAO.getCityByName(fromCityName);
        } catch (NoResultException e) {
            return new ResponseData(CROWFLIGHT, fromCityName, toCityName, null, "coordinates of city: " + fromCityName + " not contain in database");
        }
        try {
            toCity = cityDAO.getCityByName(toCityName);
        } catch (NoResultException e) {
            return new ResponseData(CROWFLIGHT, fromCityName, toCityName, null, "coordinates of city: " + toCityName + " not contain in database");
        }
        double latitudeFrom = toRadians(fromCity.getLatitude());
        double longitudeFrom = toRadians(fromCity.getLongitude());
        double latitudeTo = toRadians(toCity.getLatitude());
        double longitudeTo = toRadians(toCity.getLongitude());
        double angularLengthOfOrthodromy = acos(sin(latitudeFrom) * sin(latitudeTo) + cos(latitudeFrom) * cos(latitudeTo) * cos(longitudeTo - longitudeFrom));
        int lengthOfOrthodromy = (int) Math.round(angularLengthOfOrthodromy * RADIUS_OF_EARTH);
        return new ResponseData(CROWFLIGHT, fromCityName, toCityName, lengthOfOrthodromy, null);
    }

    private ResponseData getDistanceFromDB(RequestData request) {
        String fromCityName = request.getFromCity();
        String toCityName = request.getToCity();
        City cityByName;
        City cityByName1;
        try {
            cityByName = cityDAO.getCityByName(fromCityName);
            cityByName1 = cityDAO.getCityByName(toCityName);
        } catch (NoResultException e) {
            return new ResponseData(DISTANCE_MATRIX, fromCityName, toCityName, null, "this cities not contain in database");
        }
        Distance distance;
        try {
            distance = distanceDAO.getDistance(cityByName.getId(), cityByName1.getId());
        } catch (NoResultException e) {
            return new ResponseData(DISTANCE_MATRIX, fromCityName, toCityName, null, "distance between this cities not contain in database");
        }
        return new ResponseData(DISTANCE_MATRIX, fromCityName, toCityName, distance.getDistance(), null);
    }
}

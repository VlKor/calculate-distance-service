package edu.calculate.distance;

import com.google.common.collect.ImmutableList;
import edu.calculate.distance.dao.CityDAO;
import edu.calculate.distance.dao.DistanceDAO;
import edu.calculate.distance.model.City;
import edu.calculate.distance.model.Distance;
import edu.calculate.distance.service.RequestData;
import edu.calculate.distance.service.ResponseData;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.persistence.NoResultException;
import java.util.List;

import static edu.calculate.distance.CalculationType.CROWFLIGHT;
import static edu.calculate.distance.CalculationType.DISTANCE_MATRIX;
import static org.mockito.Mockito.when;

public class DistanceCalculatorTest {
    @Mock
    private DistanceDAO distanceDAO;
    @Mock
    private CityDAO cityDAO;
    private DistanceCalculator distanceCalculator;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        distanceCalculator = new DistanceCalculator(distanceDAO, cityDAO);
    }

    @DataProvider(name = "success")
    public static Object[][] successData() {
        return new Object[][]{
                {
                        ImmutableList.of(new RequestData(DISTANCE_MATRIX, "Detroit", "Jacksonville")),
                        ImmutableList.of(new ResponseData(DISTANCE_MATRIX, "Detroit", "Jacksonville", 123456, null))
                },
                {
                        ImmutableList.of(new RequestData(DISTANCE_MATRIX, "Charlotte", "Indianapolis")),
                        ImmutableList.of(new ResponseData(DISTANCE_MATRIX, "Charlotte", "Indianapolis", 789012, null))
                },
                {
                        ImmutableList.of(new RequestData(CROWFLIGHT, "Charlotte", "Indianapolis")),
                        ImmutableList.of(new ResponseData(CROWFLIGHT, "Charlotte", "Indianapolis", 1902, null))
                }
        };
    }

    @DataProvider(name = "error")
    public static Object[][] errorData() {
        return new Object[][]{
                {
                        ImmutableList.of(new RequestData(null, "Detroit", "Jacksonville")),
                        ImmutableList.of(new ResponseData(null, "Detroit", "Jacksonville", null, "unknown or null calculation type"))
                },
                {
                        ImmutableList.of(new RequestData(CROWFLIGHT, "Detroit", "Jacksonville")),
                        ImmutableList.of(new ResponseData(CROWFLIGHT, "Detroit", "Jacksonville", null, "coordinates of city: Detroit not contain in database"))
                },
                {
                        ImmutableList.of(new RequestData(DISTANCE_MATRIX, "Detroit", "Jacksonville")),
                        ImmutableList.of(new ResponseData(DISTANCE_MATRIX, "Detroit", "Jacksonville", null, "this cities not contain in database"))
                }
        };
    }

    @Test(dataProvider = "success")
    public void calculateDistanceTestSuccess(List<RequestData> request, List<ResponseData> expectedResponse) {
        when(cityDAO.getCityByName(request.get(0).getFromCity())).thenReturn(new City(1, request.get(0).getFromCity(), 32.7766642, -96.79698789999999));
        when(cityDAO.getCityByName(request.get(0).getToCity())).thenReturn(new City(2, request.get(0).getToCity(), 32.715738, -117.1610838));
        when(distanceDAO.getDistance(1, 2)).thenReturn(new Distance(1, 1, 2, expectedResponse.get(0).getDistance()));

        List<ResponseData> actualResponse = distanceCalculator.process(request);
        Assert.assertEquals(actualResponse, expectedResponse);
    }

    @Test(dataProvider = "error")
    public void calculateDistanceTestError(List<RequestData> request, List<ResponseData> expectedResponse) {
        when(cityDAO.getCityByName(request.get(0).getFromCity())).thenThrow(new NoResultException());
        when(cityDAO.getCityByName(request.get(0).getToCity())).thenReturn(new City(2, request.get(0).getToCity(), 32.715738, -117.1610838));
        when(distanceDAO.getDistance(1, 2)).thenReturn(new Distance(1, 1, 2, expectedResponse.get(0).getDistance()));

        List<ResponseData> actualResponse = distanceCalculator.process(request);
        Assert.assertEquals(actualResponse, expectedResponse);
    }
}

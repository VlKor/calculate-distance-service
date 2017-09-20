package edu.calculate.distance.service;

import edu.calculate.distance.DistanceCalculator;
import edu.calculate.distance.dao.CityDAO;
import edu.calculate.distance.dao.DistanceDAO;
import edu.calculate.distance.model.*;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;


@Path("/")
public class ServiceApi {

    private CityDAO cityDAO;
    private DistanceDAO distanceDAO;
    private DistanceCalculator distanceCalculator;

    public ServiceApi() {
        this.cityDAO = new CityDAO();
        this.distanceDAO = new DistanceDAO();
        this.distanceCalculator = new DistanceCalculator(distanceDAO, cityDAO);
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("city")
    public Cities getAllCities() {
        return cityDAO.getCities();
    }

    @POST
    @Path("calculation")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public List<ResponseData> calculateDistance(List<RequestData> request) {
        checkArgument(!request.isEmpty(), "input data in request can't be empty");
        return distanceCalculator.process(request);
    }

    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(MultipartFormDataInput input) throws IOException {
        for (InputPart inputPart : input.getParts()) {
            try {
                DataToUpload dataToUpload = inputPart.getBody(DataToUpload.class, null);
                for (City city : dataToUpload.getCities()) {
                    cityDAO.updateOrCreate(city);
                }
                for (DistanceDTO distance : dataToUpload.getDistances()) {
                    Integer fromCityId = cityDAO.getCityByName(distance.getFromCity()).getId();
                    Integer toCityId = cityDAO.getCityByName(distance.getToCity()).getId();
                    distance.setFromCityId(fromCityId);
                    distance.setToCityId(toCityId);
                    distanceDAO.updateOrCreate(distance);
                }
            } catch (IOException e) {
                throw e;
            }
        }
        return Response.ok().build();
    }
}

package com.pikaso.rest.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pikaso.entity.City;
import com.pikaso.entity.District;
import com.pikaso.entity.Region;
import com.pikaso.rest.service.ApiService;
import com.pikaso.rest.service.IApiService;

@Path("/")
public class ApiController {
    private IApiService apiService = new ApiService();

    @GET
    @Path("/regions")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getRegions() {
        List<Region> regions = apiService.getRegions();
        return Response.status(200).entity(regions.toString()).build();
    }

    @GET
    @Path("/districts")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getDistricts(@QueryParam("id") String regionId) {
        List<District> districts = apiService.getDistricts(Integer.parseInt(regionId));
        return Response.status(200).entity(districts.toString()).build();
    }

    @GET
    @Path("/cities")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getCities(@QueryParam("id") String districtId) {
        List<City> cities = apiService.getCities(Integer.parseInt(districtId));
        return Response.status(200).entity(cities.toString()).build();
    }

    @GET
    @Path("/postcode")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getPostcode(@QueryParam("id") Integer cityId) {
        City city = apiService.getCity(cityId);
        return Response.status(200).entity(city.toString()).build();
    }
    
    @GET
    @Path("/region")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getRegion(@QueryParam("id") Integer regionId) {
        Region region = apiService.getRegion(regionId);
        return Response.status(200).entity(region.toString()).build();
    }
    
    @GET
    @Path("/district")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getDistrict(@QueryParam("id") Integer districtId) {
        District district = apiService.getDistrict(districtId);
        return Response.status(200).entity(district.toString()).build();
    }
    
    @GET
    @Path("/city")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getCity(@QueryParam("id") Integer cityId) {
        return getPostcode(cityId);//same as '/postcode?id={cityId}'
    }

}

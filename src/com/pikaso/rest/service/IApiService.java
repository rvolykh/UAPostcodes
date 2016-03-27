package com.pikaso.rest.service;

import java.util.List;

import com.pikaso.rest.entity.City;
import com.pikaso.rest.entity.District;
import com.pikaso.rest.entity.Region;

public interface IApiService {
    List<City> getCities(int districtId);
    List<District> getDistricts(Region region);
    List<Region> getRegions();
    City getCity(int id);
}

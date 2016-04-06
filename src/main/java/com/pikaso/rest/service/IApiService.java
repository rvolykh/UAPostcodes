package com.pikaso.rest.service;

import java.util.List;

import com.pikaso.entity.City;
import com.pikaso.entity.District;
import com.pikaso.entity.Region;

public interface IApiService {
    List<City> getCities(String districtId);
    List<District> getDistricts(String regionId);
    List<Region> getRegions();
    City getCity(String id);
    Region getRegion(String id);
    District getDistrict(String id);
}

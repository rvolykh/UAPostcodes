package com.pikaso.rest.service;


import java.util.Arrays;
import java.util.List;

import com.pikaso.rest.dao.CityDao;
import com.pikaso.rest.dao.DistrictDao;
import com.pikaso.rest.dao.RegionDao;
import com.pikaso.rest.entity.City;
import com.pikaso.rest.entity.District;
import com.pikaso.rest.entity.Region;

public class ApiService implements IApiService{
    
    private CityDao cityDao = new CityDao();
    private DistrictDao districtDao = new DistrictDao();
    private RegionDao regiontDao = new RegionDao();

    @Override
    public List<City> getCities(int districtId) {
        List<City> cities = cityDao.getByFieldName("districtID", String.valueOf(districtId));
        return cities;
    }

    @Override
    public List<District> getDistricts(Region region) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Region> getRegions() {
        return regiontDao.getAll();
    }

    @Override
    public City getCity(int id) {
        City city = cityDao.getById(id);
        return city;
    }

}

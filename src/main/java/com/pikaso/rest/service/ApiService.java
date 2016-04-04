package com.pikaso.rest.service;

import java.util.List;

import com.pikaso.entity.City;
import com.pikaso.entity.District;
import com.pikaso.entity.Region;
import com.pikaso.rest.dao.CityDao;
import com.pikaso.rest.dao.DistrictDao;
import com.pikaso.rest.dao.RegionDao;

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
    public List<District> getDistricts(int regionId) {
        List<District> districts = districtDao.getByFieldName("regionID", String.valueOf(regionId));
        return districts;
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
    
    @Override
    public Region getRegion(int id) {
        return regiontDao.getById(id);
    }
    
    @Override
    public District getDistrict(int id) {
        return districtDao.getById(id);
    }


}

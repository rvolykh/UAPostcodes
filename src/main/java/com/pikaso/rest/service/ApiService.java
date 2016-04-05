package com.pikaso.rest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pikaso.entity.City;
import com.pikaso.entity.District;
import com.pikaso.entity.Region;
import com.pikaso.rest.dao.CityDao;
import com.pikaso.rest.dao.DistrictDao;
import com.pikaso.rest.dao.RegionDao;

public class ApiService implements IApiService{
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiService.class);
    
    private CityDao cityDao = new CityDao();
    private DistrictDao districtDao = new DistrictDao();
    private RegionDao regiontDao = new RegionDao();

    @Override
    public List<City> getCities(int districtId) {
        List<City> cities = cityDao.getByFieldName("districtID", String.valueOf(districtId));
        LOGGER.info(cities.size()+" cities returned from DistrictID="+districtId);
        return cities;
    }

    @Override
    public List<District> getDistricts(int regionId) {
        List<District> districts = districtDao.getByFieldName("regionID", String.valueOf(regionId));
        LOGGER.info(districts.size()+" districts returned from RegionID="+regionId);
        return districts;
    }

    @Override
    public List<Region> getRegions() {
        return regiontDao.getAll();
    }

    @Override
    public City getCity(int id) {
        City city = cityDao.getById(id);
        LOGGER.info("By CityID="+id+" returned "+city.getName());
        return city;
    }
    
    @Override
    public Region getRegion(int id) {
        Region region = regiontDao.getById(id);
        LOGGER.info("By RegionID="+id+" returned "+region.getName());
        return region;
    }
    
    @Override
    public District getDistrict(int id) {
        District district = districtDao.getById(id);
        LOGGER.info("By DistrictID="+id+" returned "+district.getName());
        return district;
    }


}

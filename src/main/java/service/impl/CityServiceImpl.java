package service.impl;


import entity.City;
import repository.CityRepository;
import service.CityService;

import java.util.Random;


public class CityServiceImpl extends BaseEntityServiceImpl<CityRepository, City, Integer> implements CityService {

    public CityServiceImpl(CityRepository repository) {
        super(repository);
    }


}

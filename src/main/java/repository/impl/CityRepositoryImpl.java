package repository.impl;

import entity.City;
import entity.City;
import entity.City_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repository.CityRepository;

import java.util.List;

public class CityRepositoryImpl extends BaseEntityRepositoryImpl<City,Integer> implements CityRepository {
    public CityRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<City> getEntityClass() {
        return City.class;
    }

    @Override
    public String getUniqueFieldName() {
        return City_.NAME;
    }


}

package service.impl;


import entity.University;
import repository.UniversityRepository;
import service.UniversityService;


public class UniversityServiceImpl extends BaseEntityServiceImpl<UniversityRepository, University, Integer> implements UniversityService {

    public UniversityServiceImpl(UniversityRepository repository) {
        super(repository);
    }



}

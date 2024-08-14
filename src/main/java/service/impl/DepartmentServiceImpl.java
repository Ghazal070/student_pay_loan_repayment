package service.impl;

import repository.DepartmentRepository;
import service.DepartmentService;

public class DepartmentServiceImpl extends BaseEntityServiceImpl<DepartmentRepository,
        Department,Integer> implements DepartmentService {
    public DepartmentServiceImpl(DepartmentRepository repository) {
        super(repository);
    }
}

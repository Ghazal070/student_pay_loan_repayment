package repository.impl;

import entity.Department_;
import jakarta.persistence.EntityManager;
import repository.DepartmentRepository;

public class DepartmentRepositoryImpl extends BaseEntityRepositoryImpl<Department,Integer> implements DepartmentRepository {
    public DepartmentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Department> getEntityClass() {
        return Department.class;
    }

    @Override
    public String getUniqueFieldName() {
        return Department_.UNIQ_ID;
    }
}

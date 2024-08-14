package repository.impl;

import entity.Staff_;
import jakarta.persistence.EntityManager;
import repository.StaffRepository;

public class StaffRepositoryImpl extends PersonRepositoryImpl<Staff> implements StaffRepository{
    public StaffRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Staff> getEntityClass() {
        return Staff.class;
    }

    @Override
    public String getUniqueFieldName() {
        return Staff_.UNIQ_ID;
    }
}

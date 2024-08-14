package service;

public interface StaffService extends PersonService<Staff>{
    double paySlip();
    Staff update(String newStringStaff, Staff existEntity);
}

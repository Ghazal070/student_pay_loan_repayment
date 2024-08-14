package service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.StaffUpdate;
import dto.StudentUpdate;
import repository.StaffRepository;
import service.StaffService;
import util.AuthHolder;

public class StaffServiceImpl extends PersonServiceImpl<StaffRepository,Staff> implements StaffService {

    private final AuthHolder authHolder;
    private final ObjectMapper objectMapper;
    public StaffServiceImpl(StaffRepository repository, AuthHolder authHolder, ObjectMapper objectMapper) {
        super(repository);
        this.authHolder = authHolder;
        this.objectMapper = objectMapper;
    }

    @Override
    public double paySlip() {
        Staff staffByTokenId = repository.findByUsername(authHolder.tokenName);
        return staffByTokenId.getBaseSalary();
    }
    @Override
    public Staff update(String newStringStaff, Staff existEntity) {
        StaffUpdate staffUpdate;
        try {
            staffUpdate = objectMapper.readValue(newStringStaff, StaffUpdate.class);
            if (staffUpdate.firstName()!=null && !staffUpdate.firstName().isEmpty()){
                existEntity.setFirstName(staffUpdate.firstName());
            }
            if (staffUpdate.lastName()!=null && !staffUpdate.lastName().isEmpty()){
                existEntity.setLastName(staffUpdate.lastName());
            }
            if (staffUpdate.username()!=null && !staffUpdate.username().isEmpty()){
                existEntity.setUsername(staffUpdate.username());
            }
            if (staffUpdate.baseSalary()!=null && staffUpdate.baseSalary()!=0){
                existEntity.setBaseSalary(staffUpdate.baseSalary());
            }


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        return repository.update(existEntity);

    }
}

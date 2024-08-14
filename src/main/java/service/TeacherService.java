package service;

public interface TeacherService extends PersonService<Teacher>{
    Teacher update(String newStringStudent, Teacher existEntity);
    Integer paySlipTuition(Term term);
    Integer paySlipScienceCommittee(Term term);
    Integer getSumUnit(Term term, Teacher teacher);
}

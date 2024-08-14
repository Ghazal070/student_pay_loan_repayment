package menu.subStaffMenu;

import entity.Student;
import entity.Student_;
import menu.util.Input;
import menu.util.Massage;
import service.DepartmentService;
import service.StudentService;
import util.AuthHolder;

import java.util.List;

public class SubStaffStudentMenu {
    private final Input INPUT;
    private final Massage MASSAGE;
    private final StudentService studentService;
    private final AuthHolder authholder;
    private final DepartmentService departmentService;


    public SubStaffStudentMenu(Input INPUT, Massage MASSAGE, StudentService studentService, AuthHolder authholder, DepartmentService departmentService) {
        this.INPUT = INPUT;
        this.MASSAGE = MASSAGE;
        this.studentService = studentService;
        this.authholder = authholder;
        this.departmentService = departmentService;
    }


    public void show() {
        subStaffStudentMenu:
        while (true) {
            System.out.println("""
                    Enter one of the following options
                    1- Register Student Information  
                    2- Remove Student Information
                    3- Edit Student Information
                    4- Previous Menu
                    5- Exit
                    """);
            switch (INPUT.scanner.next()) {
                case "1": {
                    System.out.println(MASSAGE.getInputMassage(Student_.USERNAME));
                    String username = INPUT.scanner.next();
                    System.out.println(MASSAGE.getInputMassage(Student_.PASSWORD));
                    String password = INPUT.scanner.next();
                    System.out.println(MASSAGE.getInputMassage(Student_.FIRST_NAME));
                    String firstName = INPUT.scanner.next();
                    System.out.println(MASSAGE.getInputMassage(Student_.LAST_NAME));
                    String lastName = INPUT.scanner.next();
                    System.out.println(MASSAGE.getInputMassage(Student_.ENTRY_YEAR));
                    Integer entryYear = INPUT.scanner.nextInt();
                    System.out.println(MASSAGE.getInputMassage(Student_.UNIQ_ID));
                    String studentNumber = INPUT.scanner.next();
                    Student saveStudent = studentService.save(Student.builder()
                            .username(username)
                            .password(password)
                            .uniqId(studentNumber)
                            .entryYear(entryYear)
                            .firstName(firstName)
                            .lastName(lastName)
                            .build());
                    List<Department> departments = departmentService.loadAll();
                    System.out.println("Please choose one departmentCode: ");
                    departments.forEach(System.out::println);
                    String departmentCode = INPUT.scanner.next();
                    List<Department> departmentList = departments
                            .stream()
                            .filter(
                                    a -> a.getUniqId().equals(departmentCode)
                            )
                            .toList();
                    //  Department departmentByUniqId = departmentService.findByUniqId(departmentCode);
                    Department department=null;
                    if (departmentList!=null && !departmentList.isEmpty()) department = departmentList.get(0);
                    if (department != null) {
                        saveStudent.setDepartment(department);
                        studentService.update(saveStudent);
                        System.out.println(MASSAGE.getSuccessfulMassage(authholder.tokenName));
                    } else System.out.println(MASSAGE.getFailMassage(authholder.tokenName));
                    break;
                }
                case "2": {
                    System.out.println(MASSAGE.getInputMassage(Student_.UNIQ_ID));
                    String studentNumber = INPUT.scanner.next();
                    Boolean deleteByPersonId = studentService.deleteByUniqId(studentNumber);
                    if (deleteByPersonId) {
                        System.out.println(MASSAGE.getSuccessfulMassage(authholder.tokenName));
                    } else {
                        System.out.println(MASSAGE.getFailMassage(authholder.tokenName));
                    }
                    break;

                }
                case "3": {
                    Student byPersonId = null;
                    System.out.println(MASSAGE.getInputMassage(Student_.UNIQ_ID));
                    String studentNumber = INPUT.scanner.next();
                    byPersonId = studentService.findByUniqId(studentNumber);
                    if (byPersonId != null) {
                        System.out.println("Entity Propertise: ");
                        System.out.println(byPersonId);
                        System.out.println(MASSAGE.getInputMassage(" in this Pattern: {\"username\" : \"newUsername\",\"uniqIdDepartment\" : \"1\", \"firstName\": \"newFirstName\"}"));
                        //todo check pattern
                        String newStringStudent = INPUT.scanner.next();
                        Student updateStudent = studentService.update(newStringStudent,byPersonId);
                        if (updateStudent != null) {
                            System.out.println(MASSAGE.getSuccessfulMassage(authholder.tokenName));
                        }
                    } else {
                        System.out.println(MASSAGE.getFailMassage(authholder.tokenName));
                    }
                    break;
                }
                case "4": {
                    break subStaffStudentMenu;
                }

                case "5": {
                    System.exit(0);
                }
                default:
                    System.out.println(MASSAGE.getInvalidMassage());
            }

        }
    }
}

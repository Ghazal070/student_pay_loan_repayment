package menu.subStaffMenu;

import entity.Teacher_;
import entity.enumration.Universitytype;
import menu.util.Input;
import menu.util.Massage;
import service.TeacherService;
import util.AuthHolder;

public class SubStaffTeacherMenu {
    private final Input INPUT;
    private final Massage MASSAGE;
    private final TeacherService teacherService;
    private final AuthHolder authholder;


    public SubStaffTeacherMenu(Input INPUT, Massage MASSAGE, TeacherService teacherService, AuthHolder authholder) {
        this.INPUT = INPUT;
        this.MASSAGE = MASSAGE;
        this.teacherService = teacherService;
        this.authholder = authholder;
    }


    public void show() {
        subStaffTeacherMenu:
        while (true) {
            System.out.println("""
                    Enter one of the following options
                    1- Register Teacher Information  
                    2- Remove Teacher Information
                    3- Edit Teacher Information
                    4- Previous Menu
                    5- Exit
                    """);
            switch (INPUT.scanner.next()) {
                case "1": {
                    System.out.println(MASSAGE.getInputMassage(Teacher_.USERNAME));
                    String username = INPUT.scanner.next();
                    System.out.println(MASSAGE.getInputMassage(Teacher_.PASSWORD));
                    String password = INPUT.scanner.next();
                    System.out.println(MASSAGE.getInputMassage(Teacher_.FIRST_NAME));
                    String firstName = INPUT.scanner.next();
                    System.out.println(MASSAGE.getInputMassage(Teacher_.LAST_NAME));
                    String lastName = INPUT.scanner.next();
                    System.out.println(MASSAGE.getInputMassage(Teacher_.UNIQ_ID));
                    String teacherNumber = INPUT.scanner.next();
                    System.out.println(MASSAGE.getInputMassage(Teacher_.TEACHER_TYPE + "Please use Tuition OR ScienceCommittee"));
                    String teacherType = INPUT.scanner.next();
                    System.out.println(MASSAGE.getInputMassage(Teacher_.BASE_SALARY));
                    int baseSalary = INPUT.scanner.nextInt();
                    Teacher teacher = teacherService.save(Teacher.builder()
                            .username(username)
                            .password(password)
                            .firstName(firstName)
                            .lastName(lastName)
                            .uniqId(teacherNumber)
                            .teacherType(Universitytype.valueOf(teacherType))
                            .baseSalary(baseSalary)
                            .build());
                    if (teacher!=null)System.out.println(MASSAGE.getSuccessfulMassage(authholder.tokenName));
                    else MASSAGE.getFailMassage(authholder.tokenName);
                    break;
                }
                case "2": {
                    System.out.println(MASSAGE.getInputMassage(Teacher_.UNIQ_ID));
                    String teacherNumber = INPUT.scanner.next();
                    Boolean deleteByPersonId = teacherService.deleteByUniqId(teacherNumber);
                    if (deleteByPersonId) {
                        System.out.println(MASSAGE.getSuccessfulMassage(authholder.tokenName));
                    } else {
                        MASSAGE.getFailMassage(authholder.tokenName);
                    }
                    break;

                }
                case "3": {
                    Teacher byPersonId = null;
                    System.out.println(MASSAGE.getInputMassage(Teacher_.UNIQ_ID));
                    String teacherNumber = INPUT.scanner.next();
                    byPersonId = teacherService.findByUniqId(teacherNumber);
                    if (byPersonId != null) {
                        System.out.println("Entity Propertise: ");
                        System.out.println(byPersonId);
                        System.out.println(MASSAGE.getInputMassage(" in this Pattern: {\"username\" : \"newUsername\",\"teacherType\" : \"Tuition OR ScienceCommittee\", \"firstName\": \"newFirstName\"}"));
                        //todo check pattern
                        String newStringTeacher = INPUT.scanner.next();
                        Teacher updateTeacher = teacherService.update(newStringTeacher,byPersonId);
                        if (updateTeacher != null) {
                            System.out.println(MASSAGE.getSuccessfulMassage(authholder.tokenName));
                        }
                    } else {
                        System.out.println(MASSAGE.getFailMassage(authholder.tokenName));
                    }
                    break;
                }
                case "4": {
                    break subStaffTeacherMenu;
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

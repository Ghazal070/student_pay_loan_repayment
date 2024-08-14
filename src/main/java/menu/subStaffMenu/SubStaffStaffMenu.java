package menu.subStaffMenu;

import entity.Student_;
import entity.Staff_;
import menu.util.Input;
import menu.util.Massage;
import service.StaffService;
import util.AuthHolder;

public class SubStaffStaffMenu {
    private final Input INPUT;
    private final Massage MASSAGE;
    private final StaffService staffService;
    private final AuthHolder authholder;


    public SubStaffStaffMenu(Input INPUT, Massage MASSAGE, StaffService staffService, AuthHolder authholder) {
        this.INPUT = INPUT;
        this.MASSAGE = MASSAGE;
        this.staffService = staffService;
        this.authholder = authholder;
    }


    public void show() {
        subStaffStaffMenu:
        while (true) {
            System.out.println("""
                    Enter one of the following options
                    1- Register Staff Information  
                    2- Remove Staff Information
                    3- Edit Staff Information
                    4- Previous Menu
                    5- Exit
                    """);
            switch (INPUT.scanner.next()) {
                case "1": {
                    System.out.println(MASSAGE.getInputMassage(Staff_.USERNAME));
                    String username = INPUT.scanner.next();
                    System.out.println(MASSAGE.getInputMassage(Staff_.PASSWORD));
                    String password = INPUT.scanner.next();
                    System.out.println(MASSAGE.getInputMassage(Staff_.FIRST_NAME));
                    String firstName = INPUT.scanner.next();
                    System.out.println(MASSAGE.getInputMassage(Staff_.LAST_NAME));
                    String lastName = INPUT.scanner.next();
                    System.out.println(MASSAGE.getInputMassage(Staff_.UNIQ_ID));
                    String staffNumber = INPUT.scanner.next();
                    System.out.println(MASSAGE.getInputMassage(Staff_.BASE_SALARY));
                    double baseSalary = INPUT.scanner.nextDouble();
                    Staff staff = staffService.save(Staff.builder()
                            .username(username)
                            .password(password)
                            .firstName(firstName)
                            .lastName(lastName)
                            .uniqId(staffNumber)
                            .baseSalary(baseSalary)
                            .build());
                    if (staff!=null) System.out.println(MASSAGE.getSuccessfulMassage(authholder.tokenName));
                    else MASSAGE.getFailMassage(authholder.tokenName);
                    break;
                }
                case "2": {
                    System.out.println(MASSAGE.getInputMassage(Staff_.UNIQ_ID));
                    String staffNumber = INPUT.scanner.next();
                    Boolean deleteByPersonId = staffService.deleteByUniqId(staffNumber);
                    if (deleteByPersonId) {
                        System.out.println(MASSAGE.getSuccessfulMassage(authholder.tokenName));
                    } else {
                        System.out.println(MASSAGE.getFailMassage(authholder.tokenName));
                    }
                    break;

                }
                case "3": {
                    Staff byPersonId;
                    System.out.println(MASSAGE.getInputMassage(Student_.UNIQ_ID));
                    String staffNumber = INPUT.scanner.next();
                    byPersonId = staffService.findByUniqId(staffNumber);
                    if (byPersonId != null) {
                        System.out.println("Entity Propertise: ");
                        System.out.println(byPersonId);
                        System.out.println(MASSAGE.getInputMassage(" in this Pattern: {\"username\" : \"newUsername\",\"baseSalary\" : \"1000\", \"firstName\": \"newFirstName\"}"));
                        //todo check pattern
                        String newStringStaff = INPUT.scanner.next();
                        Staff updateStudent = staffService.update(newStringStaff,byPersonId);
                        if (updateStudent != null) {
                            System.out.println(MASSAGE.getSuccessfulMassage(authholder.tokenName));
                        }
                    } else {
                        System.out.println(MASSAGE.getFailMassage(authholder.tokenName));
                    }
                    break;
                }
                case "4": {
                    break subStaffStaffMenu;
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

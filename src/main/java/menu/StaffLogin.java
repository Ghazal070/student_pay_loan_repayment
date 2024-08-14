package menu;

import menu.subStaffMenu.SubStaffStaffMenu;
import menu.subStaffMenu.SubStaffStudentMenu;
import menu.subStaffMenu.SubStaffTeacherMenu;
import menu.subStaffMenu.SubStaffCourseMenu;
import menu.util.Input;
import menu.util.Massage;
import service.StaffService;
import util.AuthHolder;

public class StaffLogin {
    private final Input INPUT;
    private final Massage MASSAGE;
    private final StaffService staffService;
    private final SubStaffStudentMenu subStaffStudentMenu;
    private final SubStaffTeacherMenu subStaffTeacherMenu;
    private final SubStaffStaffMenu subStaffStaffMenu;
    private final SubStaffCourseMenu subStaffCourseMenu;
    private final AuthHolder authHolder;


    public StaffLogin(Input INPUT, Massage MASSAGE, StaffService staffService, SubStaffStudentMenu subStaffStudentMenu, SubStaffTeacherMenu subStaffTeacherMenu, SubStaffStaffMenu subStaffStaffMenu, SubStaffCourseMenu subStaffCourseMenu, AuthHolder authHolder) {
        this.INPUT = INPUT;
        this.MASSAGE = MASSAGE;
        this.staffService = staffService;
        this.subStaffStudentMenu = subStaffStudentMenu;

        this.subStaffTeacherMenu = subStaffTeacherMenu;
        this.subStaffStaffMenu = subStaffStaffMenu;
        this.subStaffCourseMenu = subStaffCourseMenu;
        this.authHolder = authHolder;
    }

    public void show(){
        staffLoginMenu:
        while (true){
            System.out.println("""
                    Enter one of the following options
                    1- Register Remove Edit Student Information  
                    2- Register Remove Edit Teacher Information
                    3- Register Remove Edit Staff Information
                    4- Register Remove Edit Course Information
                    5- View the pay slip
                    6- Previous Menu
                    7- Exit
                    """);
            switch (INPUT.scanner.next()){
                case "1":{
                    subStaffStudentMenu.show();
                    break;}
                case "2":{
                    subStaffTeacherMenu.show();
                    break;
                }
                case "3":{
                    subStaffStaffMenu.show();
                    break;}
                case "4":{
                    subStaffCourseMenu.show();
                    break;
                }
                case "5":{
                    if (authHolder.tokenName.equals("admin")) System.out.println(
                            MASSAGE.getFailMassage(authHolder.tokenName)
                            );
                    else System.out.println(MASSAGE.getPayMassage(authHolder.tokenName) +
                            staffService.paySlip()
                    );
                    break;
                }
                case "6":{break staffLoginMenu;}
                case "7":{System.exit(0);}
                default: System.out.println(MASSAGE.getInvalidMassage());
            }

        }
    }
}

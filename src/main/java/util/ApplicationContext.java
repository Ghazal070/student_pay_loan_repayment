package util;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import menu.Menu;
import menu.StaffLogin;
import menu.StudentLogin;
import menu.TeacherLogin;
import menu.subStaffMenu.SubStaffCourseMenu;
import menu.subStaffMenu.SubStaffStaffMenu;
import menu.subStaffMenu.SubStaffStudentMenu;
import menu.subStaffMenu.SubStaffTeacherMenu;
import menu.util.Input;
import menu.util.Massage;
import repository.impl.DepartmentRepositoryImpl;
import repository.impl.StaffRepositoryImpl;
import repository.impl.*;
import service.*;
import repository.*;
import service.impl.*;
import service.impl.StudentServiceImpl;


public class ApplicationContext {
    private static ApplicationContext INSTANCE;
    private Menu menu;
    private EntityManagerFactory emf;
    private EntityManager em;


    private ApplicationContext() {
        getEntityManager();
        AuthHolder authHolder = new AuthHolder();
        Input input = new Input();
        Massage massage = new Massage();
        ObjectMapper objectMapper = new ObjectMapper();
        StudentRepository studentRepository = new StudentRepositoryImpl(em);
        DepartmentRepository departmentRepository = new DepartmentRepositoryImpl(em);
        DepartmentService departmentService = new DepartmentServiceImpl(departmentRepository);
        CourseTeacherRepository courseTeacherRepository = new CourseTeacherRepositoryImpl(em);
        StudentService studentService = new StudentServiceImpl(studentRepository, departmentService, objectMapper);
        StaffRepository staffRepository = new StaffRepositoryImpl(em);
        StaffService staffService = new StaffServiceImpl(staffRepository, authHolder, objectMapper);
        CourseRepository courseRepository = new CourseRepositoryImpl(em);
        TeacherRepository teacherRepository = new TeacherRepositoryImpl(em);
        TeacherService teacherService = new TeacherServiceImpl(teacherRepository, objectMapper, authHolder);
        TermRepository termRepository = new TermRepositoryImpl(em);
        TermService termService = new TermServiceImpl(termRepository);
        CourseService courseService = new CourseServiceImpl(courseRepository, departmentService, objectMapper);
        CourseTeacherService courseTeacherService = new CourseTeacherServiceImpl(courseTeacherRepository, departmentService, studentService, objectMapper, teacherService, termService, courseService);
        CourseStudentRepository courseStudentRepository = new CourseStudentRepositoryImpl(em);
        CourseStudentService courseStudentService = new CourseStudentServiceImpl(courseStudentRepository, termService, studentService, courseService);
        SubStaffStudentMenu subStaffStudentMenu = new SubStaffStudentMenu(input, massage, studentService, authHolder, departmentService);
        SubStaffStaffMenu subStaffStaffMenu = new SubStaffStaffMenu(input, massage, staffService, authHolder);
        SubStaffCourseMenu subStaffCourseMenu = new SubStaffCourseMenu(input, massage, courseService, authHolder, departmentService, teacherService, termService, courseTeacherService);
        SubStaffTeacherMenu subStaffTeacherMenu = new SubStaffTeacherMenu(input, massage, teacherService, authHolder);
        StudentLogin studentLogin = new StudentLogin(input, massage, studentService, authHolder, courseStudentService, courseTeacherService, termService);
        TeacherLogin teacherLogin = new TeacherLogin(input, massage, teacherService, authHolder, courseStudentService, courseTeacherService, termService);
        StaffLogin staffLogin = new StaffLogin(input, massage, staffService, subStaffStudentMenu, subStaffTeacherMenu, subStaffStaffMenu, subStaffCourseMenu, authHolder);
        menu = new Menu(input, staffLogin, authHolder, massage, studentLogin, studentService, teacherService, staffService, teacherLogin);

    }

    public static ApplicationContext getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ApplicationContext();
        }
        return INSTANCE;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("default");
        }
        return emf;
    }

    public EntityManager getEntityManager() {
        if (em == null) {
            em = getEntityManagerFactory().createEntityManager();
        }
        return em;
    }

    public Menu getMenu() {
        return menu;
    }

}

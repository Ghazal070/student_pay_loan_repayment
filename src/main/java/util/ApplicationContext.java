package util;



import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import menu.Menu;
import menu.util.Input;
import menu.util.Massage;
import repository.impl.*;
import service.*;
import repository.*;
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
        StudentRepository studentRepository = new StudentRepositoryImpl(em);
        StudentService studentService = new StudentServiceImpl(studentRepository);
        menu = new Menu(input,studentService);

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

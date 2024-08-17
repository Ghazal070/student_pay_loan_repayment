package util;



import entity.City;
import entity.Term;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import menu.Menu;
import menu.RegisterLoanMenu;
import menu.Signin;
import menu.Signup;
import menu.util.Input;
import menu.util.Message;
import repository.impl.*;
import service.*;
import repository.*;
import service.impl.CityServiceImpl;
import service.impl.LoanServiceImpl;
import service.impl.StudentServiceImpl;
import service.impl.TermServiceImpl;


public class ApplicationContext {
    private static ApplicationContext INSTANCE;
    private Menu menu;
    private EntityManagerFactory emf;
    private EntityManager em;


    private ApplicationContext() {
        getEntityManager();
        AuthHolder authHolder = new AuthHolder();
        Input input = new Input();
        Message message = new Message();
        StudentRepository studentRepository = new StudentRepositoryImpl(em);
        CityRepository cityRepository = new CityRepositoryImpl(em);
        TermRepository termRepository =new TermRepositoryImpl(em);
        TermService termService =new TermServiceImpl(termRepository);
        CityService cityService = new CityServiceImpl(cityRepository);
        StudentService studentService = new StudentServiceImpl(studentRepository);
        LoanRepository loanRepository =new LoanRepositoryImpl(em);
        LoanService loanService =new LoanServiceImpl(loanRepository);
        RegisterLoanMenu registerLoanMenu =new RegisterLoanMenu(input,message,studentService,authHolder,loanService, termService);
        Signin signin =new Signin(input,message,studentService,authHolder, registerLoanMenu);
        Signup signup =new Signup(input,studentService,message, signin, authHolder, cityService);
        menu = new Menu(input, message, signup, signin, studentService, authHolder);

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
    public void createTerm(){
        TermRepository termRepository = new TermRepositoryImpl(em);
        TermService termService=new TermServiceImpl(termRepository);
        termService.save(
                Term.builder().title("1402-1").build()
        );
        termService.save(
                Term.builder().title("1402-2").build()
        );
        termService.save(
                Term.builder().title("1403-1").build()
        );
    }
    public void createCity(){
        CityRepository cityRepository = new CityRepositoryImpl(em);
        CityService cityService=new CityServiceImpl(cityRepository);
        cityService.save(
                City.builder().name("Tehran").build()
        );
        cityService.save(
                City.builder().name("Gilan").isBigCity(true).build()
        );
        cityService.save(
                City.builder().name("Isfahan").isBigCity(true).build()
        );
        cityService.save(
                City.builder().name("Natanz").build()
        );
    }

}

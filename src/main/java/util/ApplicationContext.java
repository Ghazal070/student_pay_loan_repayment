package util;



import entity.Bank;
import entity.City;
import entity.Term;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import menu.*;
import menu.util.Input;
import menu.util.Message;
import repository.impl.*;
import service.*;
import repository.*;
import service.impl.*;


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
        BankRepository bankRepository = new BankRepositoryImpl(em);
        BankService  bankService = new BankServiceImpl(bankRepository);
        TermService termService =new TermServiceImpl(termRepository);
        EducationLoanRepository educationLoanRepository =new EducationLoanRepositoryImpl(em,authHolder);
        CityService cityService = new CityServiceImpl(cityRepository);
        StudentService studentService = new StudentServiceImpl(studentRepository, authHolder);
        LoanRepository loanRepository =new LoanRepositoryImpl(em, authHolder);
        LoanService loanService =new LoanServiceImpl(loanRepository);
        TuitionLoanRepository tuitionLoanRepository = new TuitionLoanRepositoryImpl(em,authHolder);
        TuitionLoanService tuitionLoanService =new TuitionLoanServiceImpl(tuitionLoanRepository,termService,loanService, authHolder, studentService);
        CreditCardRepository creditCardRepository = new CreditCardRepositoryImpl(em);
        CreditCardService creditCardService =new CreditCardServiceImpl(creditCardRepository);
        EducationLoanService educationLoanService =new EducationLoanServiceImpl(educationLoanRepository,termService,loanService);
        HousingLoanRepository housingLoanRepository =new HousingLoanRepositoryImpl(em,authHolder);
        InstallmentRepository installmentRepository = new InstallmentRepositoryImpl(em, authHolder);
        InstallmentService installmentService =new InstallmentServiceImpl(installmentRepository, studentService);
        HousingLoanService housingLoanService =new HousingLoanServiceImpl(housingLoanRepository, termService, loanService, authHolder, studentService);
        RegisterLoanMenu registerLoanMenu =new RegisterLoanMenu(input,message,studentService,authHolder,loanService, termService,
                educationLoanService, bankService, creditCardService, tuitionLoanService, housingLoanService, installmentService);
        PayInstallmentMenu payInstallmentMenu = new PayInstallmentMenu(input, message);
        RepaymentMenu repaymentMenu = new RepaymentMenu(input,message, installmentService, payInstallmentMenu);
        Signin signin =new Signin(input,message,studentService,authHolder, registerLoanMenu, repaymentMenu);
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
    public void createBank(){
        BankRepository bankRepository =new BankRepositoryImpl(em);
        BankService bankService = new BankServiceImpl(bankRepository);
        bankRepository.save(
                Bank.builder().name("Melli").build()
        );
        bankRepository.save(
                Bank.builder().name("Maskan").build()
        );
        bankRepository.save(
                Bank.builder().name("Tejarat").build()
        );
        bankRepository.save(
                Bank.builder().name("Refah").build()
        );
    }

}

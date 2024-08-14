import util.ApplicationContext;

public class App {
    public static void main(String[] args) {

       ApplicationContext.getINSTANCE().getMenu().show();
//        EntityManager entityManager = ApplicationContext.getINSTANCE().getEntityManager();
//        entityManager.getTransaction().begin();
//        Person build = Person.builder().username("admin").password("admin").build();
//        entityManager.persist(build);
//        entityManager.getTransaction().commit();


    }
}

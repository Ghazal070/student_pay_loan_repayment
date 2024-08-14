package repository;

public interface PersonRepository<T extends Person> extends BaseEntityRepository<T,Integer>{
    T login(String username,String password);
    T findByUsername(String username);

}

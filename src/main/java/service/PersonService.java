package service;

public interface PersonService<T extends Person> extends BaseEntityService<T, Integer> {

    public T login(String username, String password);
    T findByUsername(String username);

}

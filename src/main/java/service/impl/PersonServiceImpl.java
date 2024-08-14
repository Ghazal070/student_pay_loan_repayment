package service.impl;

import repository.PersonRepository;
import service.PersonService;

public class PersonServiceImpl<U extends PersonRepository<T>
        ,T extends Person> extends BaseEntityServiceImpl<U,T,Integer> implements PersonService<T> {
    public PersonServiceImpl(U repository) {
        super(repository);
    }


    @Override
    public T findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public T login(String username, String password) {
        if (!username.isBlank() && !password.isBlank()) return repository.login(username,password);
        return null;
    }
}

package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repository.PersonRepository;

import java.util.List;

import static javax.swing.UIManager.get;

public abstract class PersonRepositoryImpl<T extends Person> extends BaseEntityRepositoryImpl<T, Integer> implements PersonRepository<T> {
    public PersonRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public T login(String username, String password) {
        String query = """
                from %s p where p.username= ?1 and p.password= ?2
                """.formatted(getEntityClass().getName());
        TypedQuery<T> typedQuery = entityManager.createQuery(query, getEntityClass());
        typedQuery.setParameter(1, username);
        typedQuery.setParameter(2, password);
        List<T> resultList = typedQuery.getResultList();
        if(!resultList.isEmpty()) return resultList.get(0);
        return null;
    }

    @Override
    public T findByUsername(String username) {
        String query = """
                from %s p where p.username= ?1
                """.formatted(getEntityClass().getName());
        TypedQuery<T> typedQuery = entityManager.createQuery(query, getEntityClass());
        typedQuery.setParameter(1, username);
        List<T> resultList = typedQuery.getResultList();
        if(!resultList.isEmpty()) return resultList.get(0);
        return null;
    }
//    @Override
//    public Optional<T> findByUsername(String username) {
//        String query = """
//            from %s p where p.username = :username
//            """.formatted(getEntityClass().getName());
//        TypedQuery<T> typedQuery = entityManager.createQuery(query, getEntityClass());
//        typedQuery.setParameter("username", username);
//        List<T> resultList = typedQuery.getResultList();
//        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
//    }
}
package repository.impl;

import entity.CreditCard;
import entity.CreditCard_;
import jakarta.persistence.EntityManager;
import repository.CreditCardRepository;

public class CreditCardRepositoryImpl extends BaseEntityRepositoryImpl<CreditCard,Integer> implements CreditCardRepository {
    public CreditCardRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<CreditCard> getEntityClass() {
        return CreditCard.class;
    }

    @Override
    public String getUniqueFieldName() {
        return CreditCard_.CREDIT_CARD_NUMBER;
    }


}

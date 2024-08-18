package service.impl;


import entity.CreditCard;
import repository.CreditCardRepository;
import service.CreditCardService;


public class CreditCardServiceImpl extends BaseEntityServiceImpl<CreditCardRepository, CreditCard, Integer> implements CreditCardService {

    public CreditCardServiceImpl(CreditCardRepository repository) {
        super(repository);
    }



}

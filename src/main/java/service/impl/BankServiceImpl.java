package service.impl;


import entity.Bank;
import repository.BankRepository;
import service.BankService;


public class BankServiceImpl extends BaseEntityServiceImpl<BankRepository, Bank, Integer> implements BankService {

    public BankServiceImpl(BankRepository repository) {
        super(repository);
    }



}

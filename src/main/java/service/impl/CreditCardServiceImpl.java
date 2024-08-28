package service.impl;


import entity.CreditCard;
import repository.CreditCardRepository;
import service.CreditCardService;

import java.time.LocalDate;


public class CreditCardServiceImpl extends BaseEntityServiceImpl<CreditCardRepository, CreditCard, Integer> implements CreditCardService {

    public CreditCardServiceImpl(CreditCardRepository repository) {
        super(repository);
    }

    @Override
    public LocalDate convertDateMiladiToShamsi(LocalDate currentDate) {
        int year = currentDate.getYear();
        int monthValue = currentDate.getMonthValue();
        int dayOfMonth = currentDate.getDayOfMonth();
        if (monthValue>0 && monthValue<3 || (monthValue==3 && dayOfMonth<=21)){
            year -=622;
        }
        else year -= 621;
        //todo monthAndDays
        return LocalDate.of(year,monthValue,dayOfMonth);
    }
}

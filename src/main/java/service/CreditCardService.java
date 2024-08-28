package service;

import entity.CreditCard;
import entity.Term;

import java.time.LocalDate;

public interface CreditCardService extends BaseEntityService<CreditCard,Integer> {
    LocalDate convertDateMiladiToShamsi(LocalDate currentDate);
}

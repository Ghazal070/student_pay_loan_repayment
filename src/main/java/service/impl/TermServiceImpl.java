package service.impl;


import entity.Term;
import repository.TermRepository;
import service.TermService;

import java.util.Random;


public class TermServiceImpl extends BaseEntityServiceImpl<TermRepository, Term, Integer> implements TermService {

    public TermServiceImpl(TermRepository repository) {
        super(repository);
    }



}

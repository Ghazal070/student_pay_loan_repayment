package service.impl;

import repository.TermRepository;
import service.TermService;

public class TermServiceImpl extends BaseEntityServiceImpl<TermRepository, Term,Integer> implements TermService {
    public TermServiceImpl(TermRepository repository) {
        super(repository);
    }
}

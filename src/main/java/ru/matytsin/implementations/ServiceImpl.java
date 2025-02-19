package ru.matytsin.implementations;

import ru.matytsin.annotition.AutoInjectable;
import ru.matytsin.interfaces.Repository;
import ru.matytsin.interfaces.Service;

public class ServiceImpl implements Service {
    @AutoInjectable
    private Repository repository;

    public ServiceImpl() {
    }

    public ServiceImpl(Repository repository) {
        this.repository = repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}

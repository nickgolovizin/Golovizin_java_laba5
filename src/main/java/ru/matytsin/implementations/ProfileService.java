package ru.matytsin.implementations;

import ru.matytsin.annotition.AutoInjectable;
import ru.matytsin.interfaces.Repository;
import ru.matytsin.interfaces.Service;

public class ProfileService implements Service {
    @AutoInjectable
    private Repository repository;

    public ProfileService() {
    }

    public ProfileService(Repository repository) {
        this.repository = repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}

package ru.matytsin.implementations;

import ru.matytsin.annotition.AutoInjectable;
import ru.matytsin.interfaces.Service;

public class MicroServices {
    @AutoInjectable
    private Service service1;

    @AutoInjectable
    private Service service2;

    @AutoInjectable
    private Service service3;

    public MicroServices(Service service1, Service service2, Service service3) {
        this.service1 = service1;
        this.service2 = service2;
        this.service3 = service3;
    }

    public MicroServices() {
    }

    public void setService1(Service service1) {
        this.service1 = service1;
    }

    public void setService2(Service service2) {
        this.service2 = service2;
    }

    public void setService3(Service service3) {
        this.service3 = service3;
    }
}

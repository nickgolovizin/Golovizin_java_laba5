package ru.matytsin.runner;

import ru.matytsin.implementations.MicroServices;
import ru.matytsin.injector.Injector;

public class InjectorRunner {
    public static void main(String[] args) {
        try {
            MicroServices ms = (new Injector()).injector(new MicroServices());

            System.out.println(ms);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}

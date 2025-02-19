package ru.matytsin.test_classes;


import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.matytsin.annotition.AutoInjectable;

@NoArgsConstructor
public class MyClass {
    @AutoInjectable
    @Setter
    private InterfaceTest interfaceTest;
}

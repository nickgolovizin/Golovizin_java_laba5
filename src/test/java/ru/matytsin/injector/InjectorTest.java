package ru.matytsin.injector;

import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.matytsin.annotition.AutoInjectable;
import ru.matytsin.implementations.MicroServices;
import ru.matytsin.test_classes.InterfaceImpl;
import ru.matytsin.test_classes.InterfaceTest;
import ru.matytsin.test_classes.MyClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.Properties;


class InjectorTest {

    @Test
    public void testFieldsFinder() throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {
        @NoArgsConstructor
        class MyClass {
            @AutoInjectable
            private String a;
            private Integer x;
            @AutoInjectable
            private List<String> z;
        }

        Injector injector = new Injector();
        Method checkFields = injector.getClass().getDeclaredMethod("checkFields", Object.class);
        checkFields.setAccessible(true);
        Optional<List<Field>> fields = (Optional<List<Field>>) checkFields.invoke(injector, new MyClass());

        List<String> names = fields.get().stream().map(field -> (field.getName())).toList();

        Assertions.assertIterableEquals(names, List.of("a", "z"));
    }


    @Test
    public void testInjection() throws Exception {
        MyClass injectedObj = (new Injector()).injector(new MyClass());

        InterfaceTest interfaceTest = new InterfaceImpl();
        MyClass obj = new MyClass();
        obj.setInterfaceTest(interfaceTest);

        Assertions.assertInstanceOf(obj.getClass(), injectedObj);
    }


    @Test
    public void testProp() throws IOException, ClassNotFoundException, NoSuchMethodException,
            Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/application.properties"));
        String x = properties.getProperty("ru.matytsin.interfaces.Controller");
        var obj = Class.forName(x);
        var entity = obj.getConstructor().newInstance();
    }

    @Test
    public void InjectingTest() throws Exception {
        MicroServices ms = (new Injector()).injector(new MicroServices());
    }

}
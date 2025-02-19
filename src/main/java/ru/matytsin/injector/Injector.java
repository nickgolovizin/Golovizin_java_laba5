package ru.matytsin.injector;

import ru.matytsin.annotition.AutoInjectable;
import ru.matytsin.exceptions.PropertiesFileError;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;


/**
 * Класс предназначен для внедрения зависимостей в объекты классов.
 * Инициализация полей происходит с помощью сеттеров,
 * исходя из данных в properties-файле
 */
public class Injector {

    /**
     * Путь к файлу properties файлу
     */
    private static final String PROPERTY_PATH = "src/main/resources/application.properties";


    /**
     * Приватный метод проверяет наличие полей, помеченных аннотацией.
     * При их наличие возвращает список нужных полей, иначе - пустой Optional
     *
     * @param object объект типа Т
     * @param <T> тип объекта
     * @return список полей, помеченных аннотацией AutoInjectable
     */
    private <T> Optional<List<Field>> checkFields(T object) {
        Field[] objectFields = object.getClass().getDeclaredFields();
        List<Field> annotatedFields = new ArrayList<>();

        for (Field field : objectFields) {
            if (field.getAnnotation(AutoInjectable.class) != null)
                annotatedFields.add(field);
        }

        return annotatedFields.isEmpty() ? Optional.empty() : Optional.of(annotatedFields);
    }

    public Injector() {
    }


    /**
     * Метод рекурсивно внедряющий зависимости в объект класса,
     * используя данные из properties файла
     *
     * @param object объект типа Т
     * @param <T> тип объекта
     * @return объект с проинициализированными полями
     * @throws Exception
     */
    public <T> T injector(T object) throws Exception {
        Optional<List<Field>> annotatedFields = checkFields(object);

        if (annotatedFields.isEmpty())
            return object;

        List<Field> fields = annotatedFields.get();

        Properties properties = new Properties();
        properties.load(new FileInputStream(PROPERTY_PATH));

        for (Field field : fields) {
            String classTypeName = field.getDeclaringClass().getCanonicalName() + "." + field.getName();

            String implementationClassName = properties.getProperty(classTypeName);

            if (implementationClassName == null)
                throw new PropertiesFileError("Pair : " + classTypeName + ", "
                        + implementationClassName + " does not exist in "
                        + PROPERTY_PATH);

            var implemetationClass = Class.forName(implementationClassName);
            var implementationClassObject = implemetationClass.getConstructor().newInstance();

            injector(implementationClassObject);


            field.setAccessible(true);
            field.set(object, implementationClassObject);
        }

        return object;
    }
}

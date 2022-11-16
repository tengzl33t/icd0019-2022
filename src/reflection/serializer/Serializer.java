package reflection.serializer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Serializer {

    public String serialize(Object instance) {
        Field[] fields = instance.getClass().getDeclaredFields();

        List<String> pairs = new ArrayList<>();
        for (Field field : fields) {

            Stored annotation = field.getAnnotation(Stored.class);

            if (annotation == null){
                continue;
            }

            String fieldName = annotation.value().equals("") ? field.getName() : annotation.value();

            String pair = fieldName + ":" + getFieldValue(instance, field);

            pairs.add(pair);
        }


        return String.join("|", pairs);
    }

    private String getFieldValue(Object instance, Field field) {
        try {
            field.setAccessible(true);
            String result = field.get(instance).toString();

            // encoding
            result = result.replaceAll("%", "%25").
                    replaceAll("\\|", "%7c").
                    replaceAll(":", "%3a");

            return result;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private String getValuesFromSerialized(String input, String fieldName){
        for (String pair : input.split("\\|")) {
            String[] keyAndValue = pair.split(":");
            if (keyAndValue[0].equals(fieldName)){

                String result = keyAndValue[1];

                // decoding
                result = result.
                        replaceAll("%25", "%").
                        replaceAll("%7c", "|").
                        replaceAll("%3a", ":");

                return result;
            }
        }
        throw new RuntimeException("something is wrong at getValuesFromSerialized()?");
    }

    private void setClassField(Field field, Object instance, Object value) {
        try {
            field.setAccessible(true);
            field.set(instance, value);

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T deserialize(String inputString, Class<T> clazz) {

        T instance;

        try {
            instance = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            Stored annotation = field.getAnnotation(Stored.class);

            if (annotation == null){
                continue;
            }

            String fieldName = annotation.value().equals("") ? field.getName() : annotation.value();

            String value = getValuesFromSerialized(inputString, fieldName);

            if (field.getType() == int.class){
                setClassField(field, instance, Integer.parseInt(value));
            }else{
                setClassField(field, instance, value);
            }
        }

        return instance;
    }

}

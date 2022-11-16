package reflection.tester;
import java.lang.reflect.Method;
import java.util.*;

public class TestRunner {

    Map<String, String> tests = new HashMap<>();

    public void runTests(List<String> testClassNames) {
        for (String testClassName : testClassNames) {
            try {
                Class<?> aClass = Class.forName(testClassName);
                Object instance = aClass.getDeclaredConstructor().newInstance();
                Method[] methods = aClass.getDeclaredMethods();

                for (Method method : methods) {
                    if (!method.isAnnotationPresent(MyTest.class)){
                        continue;
                    }

                    Class<? extends Throwable> expectedException = method.getAnnotation(MyTest.class).expected();

                    if (expectedException.isAssignableFrom(currentException(method, instance))){
                        tests.put(method.getName(), "OK");
                    }else{
                        tests.put(method.getName(), "FAILED");
                    }
                }


            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    private Class<? extends Throwable> currentException(Method method, Object instance){
        try{
            method.invoke(instance);
        }catch (java.lang.reflect.InvocationTargetException e){
            return e.getTargetException().getClass();
        }catch (IllegalAccessException e){
            throw new RuntimeException("wtf?");
        }
        return MyTest.None.class;
    }

    public String getResult() {
        String result = "";

        for (String key : tests.keySet()) {
            result += key + "()" + " - " + tests.get(key) + "\n";
        }

        return result;
    }
}

import Annotations.InitMethod;
import myapp.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class MyAppTest {
    Main main = new Main();

    @BeforeEach
    public void setMain(){
        main.setAnnotation(InitMethod.class);
        main.setXx(1);
    }

    @Test
    public void testIOException() {
        assertThrows(IOException.class, () -> {
            System.out.println("-------1-----------");
            main.Main("/myapp.property", "bootstrapClass");

        });
    }
    @Test
    public void testClassNotFoundException() {
        assertThrows(ClassNotFoundException.class, () -> {
            System.out.println("-------2-----------");
            main.Main("/myapp.properties", "bootstrapClasses");

        });
    }
    @Test
    public void testInvocationTargetException() {
        assertThrows(InvocationTargetException.class, () -> {
            //main.Main("/myapp.properties","bootstrapClass");

            Class mainClass = Class.forName("myapp.Main");
            Object User = mainClass.getDeclaredConstructor().newInstance();

            Field mainField = mainClass.getDeclaredField("annotation");

            mainField.setAccessible(true);

            mainField.set(User, Test.class);

            Method method = mainClass.getMethod("Main", String.class, String.class);
            System.out.println("-------4-----------");
            method.invoke(User, "/myapp.properties", "bootstrapClass");
        });
    }
    @Test
    public void testNoSuchMethodException() {
        assertThrows(NoSuchMethodException.class, () -> {
            main.setAnnotation(Test.class);
            main.Main("/myapp.properties", "bootstrapClass");

        });
    }
    @Test
    public void testIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class,()->{
            System.out.println("-------5-----------");
            main.setXx(-1);
            main.Main("/myapp.properties","bootstrapClass");

        });
    }
}

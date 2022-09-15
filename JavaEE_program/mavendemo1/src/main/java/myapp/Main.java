package myapp;
import Annotations.InitMethod;
import apps.MyClass;

import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Properties props = new Properties();


        try (InputStream input =
                     Main.class.getResourceAsStream("/myapp.properties")) {
            if (input == null) {
                return;
            }
            props.load(input);
            System.out.println(props.getProperty("bootstrapClass"));
            Class myAppClass = Class.forName("apps."+props.getProperty("bootstrapClass"));
            Method[] methods = myAppClass.getMethods();
            Object User = myAppClass.getDeclaredConstructor().newInstance();

            for(Method method:methods){
                if(method.isAnnotationPresent(InitMethod.class)){
                    System.out.println(method.getName());
                    method.invoke(User);
                }
            }




        } catch (IOException e) {
            System.out.println("Load properties error!");
        }  catch (ClassNotFoundException e) {
            System.out.println("Load Class error!");
        } catch (InvocationTargetException e) {
            System.out.println("InvocationTargetException");
        } catch (IllegalAccessException e) {
            System.out.println("IllegalAccessException");
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }


    }

}
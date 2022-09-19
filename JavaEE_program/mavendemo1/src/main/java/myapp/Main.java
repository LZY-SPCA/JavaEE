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
    //public String properties = "/myapp.properties";
    Class annotation = InitMethod.class;
    int xx=1;
    public void Main(String properties,String className) throws IOException,ClassNotFoundException,NoSuchMethodException,IllegalArgumentException {

        Properties props = new Properties();


        try (InputStream input =
                     Main.class.getResourceAsStream(properties)) {
            if (input == null) {
                throw new IOException();
            }
            props.load(input);

            Class myAppClass = Class.forName("apps."+props.getProperty(className));

            Method[] methods = myAppClass.getMethods();
            Object User = myAppClass.getDeclaredConstructor().newInstance();
            boolean flag = false;
            for(Method method:methods){
                if(method.isAnnotationPresent(annotation)){
                    System.out.println(method.getName());
                    method.invoke(User,xx);
                    flag = true;
                }
            }
            if(flag == false){
                throw new NoSuchMethodException();
            }




        }
        catch (IllegalAccessException e) {
            System.out.println("IllegalAccessException");
        } catch (InstantiationException e) {
            System.out.println("123");
        } catch (InvocationTargetException e) {
            System.out.println("456");
        }


    }
    public void setAnnotation(Class inputAnnotation){
        annotation = inputAnnotation;
    }

    public void setXx(int inputxx){
        xx = inputxx;
    }

}
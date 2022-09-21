package whu.edu;

import java.util.HashMap;
import java.util.Map;

public class MyApplicationContext {
     static Map<String,Object>  beans = new HashMap<String,Object>();
     public static void insert(BeanDefinition beanDefinition, Object object){
         beans.put(beanDefinition.id,object);
     }
}

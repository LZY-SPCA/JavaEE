package whu.edu;

import java.util.*;

public class BeanDefinition {
    String id;
    String className;
    boolean hasProperty;
    Map<String,String> property=new HashMap<String,String>();



    public BeanDefinition(String id,String className,boolean hasProperty){
        this.id= id;
        this.className=className;
        this.hasProperty=hasProperty;

    }
    public void setProperty(String name,String ref){
        property.put(name,ref);
    }

}

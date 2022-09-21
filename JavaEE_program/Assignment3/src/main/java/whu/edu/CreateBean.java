package whu.edu;

import java.lang.reflect.InvocationTargetException;

public class CreateBean {
    BeanDefinition beanDefinition;
    public CreateBean(BeanDefinition beanDefinition)  {
        this.beanDefinition=beanDefinition;
    }

    public void create() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class myBean = Class.forName(beanDefinition.className);
        Object bean = myBean.getConstructor().newInstance();
        MyApplicationContext.insert(beanDefinition,bean);

    }

}

package whu.edu;

import java.lang.reflect.InvocationTargetException;

public class CreateBean {
    BeanDefinition beanDefinition;
    public CreateBean(BeanDefinition beanDefinition)  {
        this.beanDefinition=beanDefinition;
    }

    public void create() throws  IOCExcptions {
        System.out.println("create");
        Class myBean;
        Object bean;
        try{
            myBean = Class.forName(beanDefinition.className);
            bean = myBean.getConstructor().newInstance();
        } catch (ClassNotFoundException | InstantiationException e) {
            throw new IOCExcptions(IOCExcptions.ErrorType.CLASS_NOT_FOUND,"Class not found");
        } catch (NoSuchMethodException e) {
            throw new IOCExcptions(IOCExcptions.ErrorType.METHOD_CALL_ERROR,"No Such Method");
        } catch (InvocationTargetException e) {
            throw new IOCExcptions(IOCExcptions.ErrorType.INVOCATION_ERROR,"Invocation error");
        } catch (IllegalAccessException e) {
            throw new IOCExcptions(IOCExcptions.ErrorType.ILLEGAL_ACCESS,"Can't access");
        }


        MyApplicationContext.insert(beanDefinition,bean);

    }

}

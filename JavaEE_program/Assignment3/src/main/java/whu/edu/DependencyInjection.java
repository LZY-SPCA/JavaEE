package whu.edu;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class DependencyInjection {
    BeanDefinition beanDefinition;
    public DependencyInjection(BeanDefinition beanDefinition){
        this.beanDefinition=beanDefinition;
    }
    public static void injection(BeanDefinition beanDefinition) throws  IOCExcptions {
        Iterator<Map.Entry<String, String>> it = beanDefinition.property.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, String> entry = it.next();
            String injectName = entry.getKey();
            String target = entry.getValue();
            Object targetClass = null;
            //首字母大写
            injectName = injectName.substring(0, 1).toUpperCase() + injectName.substring(1);
            injectName = "set" + injectName;

            Iterator<Map.Entry<String, Object>> targetBeans = MyApplicationContext.beans.entrySet().iterator();
            System.out.println(MyApplicationContext.beans.size());
            while(targetBeans.hasNext()){
                Map.Entry<String, Object> targetBean = targetBeans.next();
                System.out.println(target);
                if(targetBean.getKey().equals(target)){
                    targetClass=targetBean.getValue();
                    break;
                }
            }

            if(targetClass==null){
                System.out.println("null");
                throw new IOCExcptions(IOCExcptions.ErrorType.CLASS_NOT_FOUND,"No such class");
            }
            Iterator<Map.Entry<String, Object>> beanClasses = MyApplicationContext.beans.entrySet().iterator();
            while(beanClasses.hasNext()){
                Map.Entry<String, Object> beanClass = beanClasses.next();
                if(beanDefinition.id==beanClass.getKey()){
                    try {
                        Method method = beanClass.getValue().getClass().getMethod(injectName, targetClass.getClass());
                        method.invoke(beanClass.getValue(), targetClass);
                    }catch (NoSuchMethodException e) {
                        throw new IOCExcptions(IOCExcptions.ErrorType.METHOD_CALL_ERROR,"No such method");
                    } catch (InvocationTargetException e) {
                        throw new IOCExcptions(IOCExcptions.ErrorType.INVOCATION_ERROR,"Invocation error");
                    } catch (IllegalAccessException e) {
                        throw new IOCExcptions(IOCExcptions.ErrorType.ILLEGAL_ACCESS,"Can't access");
                    }
                }
            }
        }

    }
}

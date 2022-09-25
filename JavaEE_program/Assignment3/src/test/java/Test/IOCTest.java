package Test;

import whu.edu.*;
import org.junit.jupiter.api.Test;
import whu.edu.IOCExcptions;

import static org.junit.jupiter.api.Assertions.*;

public class IOCTest {

    //不存在的xml文件
    @Test
    public void testLoadXML(){
        IOCExcptions excption = assertThrows(IOCExcptions.class,()->{
            XMLReader.ReadXML("src/1.xml");
        });
        assertEquals(IOCExcptions.ErrorType.FILE_READ_ERROR,excption.getErrorType());
    }

    //不合法的xml文件
    @Test
    public void testIllegalXML(){
        IOCExcptions excption = assertThrows(IOCExcptions.class,()->{
            XMLReader.ReadXML("src/abc.xml");
        });
        assertEquals(IOCExcptions.ErrorType.FILE_READ_ERROR,excption.getErrorType());
    }

    //注入失败测试
    @Test
    public void targetClassNotFound(){
        BeanDefinition beanDefinition = new BeanDefinition("bookDaoService","TargetClass.BookDaoService",true);
        beanDefinition.setProperty("bookDao","bookDao");
        BeanDefinition beanDefinition1= new BeanDefinition("bookDao","TargetClass.BookDao",false);
        CreateBean createBean = new CreateBean(beanDefinition);
        CreateBean createBean1 = new CreateBean(beanDefinition1);
        MyApplicationContext.delete(beanDefinition1);
        IOCExcptions excption = assertThrows(IOCExcptions.class,()->{
            DependencyInjection.injection(beanDefinition);
        });
        assertEquals(IOCExcptions.ErrorType.CLASS_NOT_FOUND,excption.getErrorType());
    }

    @Test
    public void ID_Repeat(){
        IOCExcptions excption = assertThrows(IOCExcptions.class,()->{
            XMLReader.ReadXML("src/ID_Repeat.xml");
        });
        assertEquals(IOCExcptions.ErrorType.ID_REPEAT,excption.getErrorType());
    }

    @Test
    public void createClassNotFound(){
        BeanDefinition beanDefinition = new BeanDefinition("bookDaoService","TargetClass.Book",true);
        IOCExcptions excption = assertThrows(IOCExcptions.class,()->{
            CreateBean createBean = new CreateBean(beanDefinition);
            createBean.create();
        });
        assertEquals(IOCExcptions.ErrorType.CLASS_NOT_FOUND,excption.getErrorType());
    }

    @Test
    public void constructorNotFound(){
        BeanDefinition beanDefinition = new BeanDefinition("bookDaoService","Test.TestBook",true);
        IOCExcptions excption = assertThrows(IOCExcptions.class,()->{
            CreateBean createBean = new CreateBean(beanDefinition);
            createBean.create();
        });
        assertEquals(IOCExcptions.ErrorType.METHOD_CALL_ERROR,excption.getErrorType());
    }

    @Test
    public void constructorAccessFailed(){
        BeanDefinition beanDefinition = new BeanDefinition("bookDaoService","Test.TestIllegalAccess",true);
        IOCExcptions excption = assertThrows(IOCExcptions.class,()->{
            CreateBean createBean = new CreateBean(beanDefinition);
            createBean.create();
        });
        assertEquals(IOCExcptions.ErrorType.METHOD_CALL_ERROR,excption.getErrorType());
    }
}

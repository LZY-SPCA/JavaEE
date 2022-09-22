package whu.edu;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class XMLReader {


    public static void ReadXML() throws DocumentException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("src/Spring.xml"));
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        for (Element element2 : elements)
        {
            List<Element> propertyElements = element2.elements();

            if(propertyElements.isEmpty()==true){
                BeanDefinition beanDefinition = new BeanDefinition(element2.attributeValue("id"),element2.attributeValue("class"),false);
                CreateBean createBean = new CreateBean(beanDefinition);
                createBean.create();


                System.out.println(beanDefinition);
            }
            else{
                BeanDefinition beanDefinition = new BeanDefinition(element2.attributeValue("id"),element2.attributeValue("class"),true);
                for(Element prop:propertyElements){
                    beanDefinition.setProperty(prop.attributeValue("name"),prop.attributeValue("ref"));
                }
                System.out.println(beanDefinition.property.size());
                CreateBean createBean = new CreateBean(beanDefinition);
                createBean.create();
                DependencyInjection.injection(beanDefinition);
                System.out.println(beanDefinition);
            }



        }

    }
}

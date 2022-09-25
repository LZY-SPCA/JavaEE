package whu.edu;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class XMLReader {


    public static void ReadXML(String fileName) throws IOCExcptions {
        Document document;
        SAXReader reader = new SAXReader();
        List<BeanDefinition> beanDefinitionList =new ArrayList<BeanDefinition>();
        try {
            document = reader.read(new File(fileName));
        }catch (DocumentException e) {
            throw new IOCExcptions(IOCExcptions.ErrorType.FILE_READ_ERROR,"找不到XML文件");
        }
        Element rootElement = document.getRootElement();
        if(!rootElement.getName().equals("beans")){
            System.out.println(rootElement.getName());
            throw new IOCExcptions(IOCExcptions.ErrorType.FILE_READ_ERROR,"不合法");
        }
        List<Element> elements = rootElement.elements();
        for (Element element2 : elements)
        {
            List<Element> propertyElements = element2.elements();

            if(propertyElements.isEmpty()==true){
                BeanDefinition beanDefinition = new BeanDefinition(element2.attributeValue("id"),element2.attributeValue("class"),false);
                if(!beanDefinitionList.isEmpty()) {
                    for (BeanDefinition beanDefinition1 : beanDefinitionList) {
                        if(beanDefinition1.id.equals(beanDefinition.id)){
                            throw new IOCExcptions(IOCExcptions.ErrorType.ID_REPEAT,"ID重复");
                        }
                    }
                }
                beanDefinitionList.add(beanDefinition);
                CreateBean createBean = new CreateBean(beanDefinition);
                createBean.create();


                System.out.println(beanDefinition);
            }
            else{
                BeanDefinition beanDefinition = new BeanDefinition(element2.attributeValue("id"),element2.attributeValue("class"),true);
                if(!beanDefinitionList.isEmpty()) {
                    for (BeanDefinition beanDefinition1 : beanDefinitionList) {
                        if(beanDefinition1.id.equals(beanDefinition.id)){
                            throw new IOCExcptions(IOCExcptions.ErrorType.ID_REPEAT,"ID重复");
                        }
                    }
                }
                beanDefinitionList.add(beanDefinition);
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

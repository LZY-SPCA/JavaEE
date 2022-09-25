import org.dom4j.DocumentException;
import whu.edu.IOCExcptions;
import whu.edu.XMLReader;

import java.lang.reflect.InvocationTargetException;

public class AppEntrance {
    public static void main(String[] args) throws IOCExcptions, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        XMLReader.ReadXML("src/Spring.xml");
    }
}

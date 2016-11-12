package Spring;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
 
// Bean����ʵ����
public class ClassPathXmlApplicationContext implements BeanFactory {
 
    private Map<string , object=""> beans = new HashMap<string, object=""> ();
 
    public ClassPathXmlApplicationContext() throws Exception {
        SAXBuilder sb = new SAXBuilder();
        Document doc = sb.build(this.getClass().getClassLoader().getResourceAsStream("beans.xml")); // �����ĵ�����
        Element root = doc.getRootElement(); // ��ȡ��Ԫ��HD
        List list = root.getChildren("bean"); // ȡ����Ϊbean������Ԫ��
        for (int i = 0; i < list.size(); i++) {
            Element element = (Element) list.get(i);
            String id = element.getAttributeValue("id");
            String clazz = element.getAttributeValue("class");
            Object beanObj = Class.forName(clazz).newInstance(); // �����ȡ����
            beans.put(id, beanObj); // ���������Bean����
 
            for (Element propertyElement : (List<element>) element.getChildren("property")) { 
                String name = propertyElement.getAttributeValue("name"); // name="userDao"
                String bean = propertyElement.getAttributeValue("bean"); // bean="userDao"
                Object injectObject = beans.get(bean); // ��Bean�����л�ȡUserDao
                String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1); // setUserDao
                Method method = beanObj.getClass().getMethod(methodName, injectObject.getClass());
                method.invoke(beanObj, injectObject); // setע��UserDao����
            }
        }
    }
 
    public Object getBean(String id) {
        return beans.get(id);
    }
 
}

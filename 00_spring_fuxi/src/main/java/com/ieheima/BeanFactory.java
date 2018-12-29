package com.ieheima;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * @Author ：王伟彬
 * @Date ：Created in 21:23 2018/12/28
 * @Description：解耦工具类
 * @Version: $
 */
public class BeanFactory {

    //接受一个id来读读取配置文件中的信息并且返回一个对象
    public static Object getBean(String id){
        try {
            //1.获取document对象
            Document document = new SAXReader().read(BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml"));
            //2.调用 SelectSingleNode
            Element element = (Element) document.selectSingleNode("//bean['" + id + "']");
            //3.获取class属性
            String aClass = element.attributeValue("class");
            //4.通过反射
            Object o = Class.forName(aClass).newInstance();
            return o;
        } catch (Exception e) {
             throw new RuntimeException("创建bean失败");
        }
    }
}

package com.haothink.dom4j;



import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import org.apache.commons.io.IOUtils;
import org.dom4j.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Created by wanghao on 2019-11-01
 * mail:wanghaotime@qq.com
 **/
public class XmlParserDemo {


    public static void main(String[] args) throws IOException, DocumentException {

        InputStream inputStream = XmlParserDemo.class.getClassLoader().getResourceAsStream("request_demo.xml");

        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer, StandardCharsets.UTF_8.name());
        String xml = writer.toString();
        Map<String, Object> map = xml2Map(xml);
        System.out.println(JSONObject.toJSONString(map));

        System.out.println(map2Xml(map,"Application"));
    }


    public static String map2Xml(Map<String, Object> map,String rootElement){

        Document doc = DocumentHelper.createDocument();
        Element body = DocumentHelper.createElement(rootElement);
        doc.add(body);
        buildMap2xmlBody(body, map);
        doc.setXMLEncoding("UTF-8");
        return doc.asXML();
    }



    public static Map<String, Object> xml2Map(String xml) throws DocumentException {
        if (xml == null || "".equals(xml)) {
            return null;
        }
        Document doc = DocumentHelper.parseText(xml);
        Element root = doc.getRootElement();
        return Dom2Map(root);
    }

    public static Multimap<String, Object> xml2MutiValueMap(String xml) throws DocumentException {
        if (xml == null || "".equals(xml)) {
            return null;
        }
        Document doc = DocumentHelper.parseText(xml);
        Element root = doc.getRootElement();
        return Dom2MutiValueMap(root);
    }






    private static void buildMap2xmlBody(Element body,Map<String, Object> map){
        if(null == map || map.isEmpty()){
            return;
        }

        for(Map.Entry<String, Object> entry : map.entrySet()){
            Object value = entry.getValue();
            String key = entry.getKey();
            Element element = DocumentHelper.createElement(key);
            if(value instanceof Map){
                Map<String,Object> valueMap = (Map<String,Object>)value;
                buildMap2xmlBody(element,valueMap);
                body.add(element);
            }else{
                Attribute attribute = DocumentHelper.createAttribute(body,key,value.toString());
                body.add(attribute);
            }
        }
    }


    private static Map<String, Object> Dom2Map(Element root) {

        Map<String, Object> map = new IdentityHashMap<>();

        for (Iterator iterator = root.elementIterator(); iterator.hasNext(); ) {
            Map<String, Object> eleMap = new IdentityHashMap<>();
            Element e = (Element) iterator.next();
            List attributeList = e.attributes();
            for (Object o : attributeList) {
                if (o instanceof Attribute) {
                    Attribute attribute = (Attribute) o;
                    eleMap.put(attribute.getName(), attribute.getValue());
                }
            }
            List elements = e.elements();
            if (elements.size() > 0) {
                //将子元素继续执行
                for(Object o : elements) {
                    Element childEle = (Element)o;
                    eleMap.put(childEle.getName(), Dom2Map(childEle));
                }
            }
            map.put(e.getName(), eleMap);
        }
        //将当前标签的属性装载到子标签同级别的map当中
        List attributeList = root.attributes();
        for (Object o : attributeList) {
            if (o instanceof Attribute) {
                Attribute attribute = (Attribute) o;
                map.put(attribute.getName(), attribute.getValue());
            }
        }
        return map;
    }


    private static Multimap<String, Object> Dom2MutiValueMap(Element root) {

        Multimap<String, Object> map = LinkedHashMultimap.create();

        for (Iterator iterator = root.elementIterator(); iterator.hasNext(); ) {
            Multimap<String, Object> eleMap = LinkedHashMultimap.create();
            Element e = (Element) iterator.next();
            List attributeList = e.attributes();
            for (Object o : attributeList) {
                if (o instanceof Attribute) {
                    Attribute attribute = (Attribute) o;
                    eleMap.put(attribute.getName(), attribute.getValue());
                }
            }
            List elements = e.elements();
            if (elements.size() > 0) {
                //将子元素继续执行
                for(Object o : elements) {
                    Element childEle = (Element)o;
                    eleMap.put(childEle.getName(), Dom2Map(childEle));
                }
            }
            map.put(e.getName(), eleMap);
        }
        //将当前标签的属性装载到子标签同级别的map当中
        List attributeList = root.attributes();
        for (Object o : attributeList) {
            if (o instanceof Attribute) {
                Attribute attribute = (Attribute) o;
                map.put(attribute.getName(), attribute.getValue());
            }
        }

        return map;
    }

}

package com.haothink.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.haothink.dom4j.XmlParserDemo;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import de.odysseus.staxon.json.JsonXMLConfig;
import de.odysseus.staxon.json.JsonXMLConfigBuilder;
import de.odysseus.staxon.json.JsonXMLInputFactory;
import de.odysseus.staxon.json.JsonXMLOutputFactory;
import de.odysseus.staxon.xml.util.PrettyXMLEventWriter;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.JsonNode;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wanghao on 2019-10-22
 * mail:wanghaotime@qq.com
 **/
public class JsonInterCngXmlUtil {


    private static final String XML_HEADER_SIGN = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    private static final String RIGHT_ANGLE_BRACKET = ">";

    public static void main(String[] args) throws IOException {

        InputStream inputStream = XmlParserDemo.class.getClassLoader().getResourceAsStream("response_demo2.xml");

        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer, StandardCharsets.UTF_8.name());
        String xml = writer.toString();
        String json = xml2json(xml);

        Object blazeResponseObj = Configuration.defaultConfiguration().jsonProvider().parse(json);

        Map<String,Object> invokeStatus = JsonPath.read(blazeResponseObj,"$.Application.ScoreResults");
        System.out.println(invokeStatus.get("SubScoreResult").getClass());
    }


    public static String json2xml(String json) {
        StringReader input = new StringReader(json);
        StringWriter output = new StringWriter();
        JsonXMLConfig config = new JsonXMLConfigBuilder().multiplePI(false).repairingNamespaces(false).build();
        try {
            XMLEventReader reader = new JsonXMLInputFactory(config).createXMLEventReader(input);
            XMLEventWriter writer = XMLOutputFactory.newInstance().createXMLEventWriter(output);
            writer = new PrettyXMLEventWriter(writer);
            writer.add(reader);
            reader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return output.toString();
    }

    public static String json2xmlRemoveHeaderSign(String json) {
        String xmlStr = json2xml(json);
        if (null != xmlStr && xmlStr.startsWith(XML_HEADER_SIGN)) {// remove <?xml version="1.0" encoding="UTF-8"?>
            return "<xml>"+xmlStr.substring(xmlStr.indexOf(RIGHT_ANGLE_BRACKET)+1) + "</xml>";
        }
        return xmlStr;
    }

    public static Map json2Map(String json){

        JSONObject rootObject = JSONObject.parseObject(json);
        Map<String,Object> rootMap = new LinkedHashMap<>(rootObject.size());
        json2Map(rootObject,rootMap);
        return rootMap;
    }

    /**
     *
     * 递归遍历jsonObject下的元素
     *
     */
    private static void json2Map(JSONObject rootObject,Map<String,Object> rootMap){

        for(Map.Entry<String, Object> entry : rootObject.entrySet()){

            String key = entry.getKey();
            Object value = entry.getValue();
            if(value instanceof JSONObject){
                JSONObject childObject = (JSONObject)value;
                Map<String,Object> childMap = new LinkedHashMap<>(childObject.size());
                json2Map(childObject,childMap);
                rootMap.put(key,childMap);
            }else if(value instanceof String){
                rootMap.put(key,value);
            }else if(value instanceof JSONArray){
                //value是jsonArray的情况暂时忽略
            }else{
                rootMap.put(key,value);
            }

        }
    }


    private static JSONObject json2Object(String json){

        JSONObject object = new JSONObject();
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            for(Map.Entry<String, Object> entry : jsonObject.entrySet()){
                String key = entry.getKey();
                Object value = entry.getValue();
                if(value instanceof JSONObject){
                    JSONObject childObject = (JSONObject)value;
                    Map<String,Object> childMap = new LinkedHashMap<>(childObject.size());
                    json2Map(childObject,childMap);
                    object.put(key,childMap);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return object;

    }


    public static Map xml2Map(String xml){

       String jsonStr = xml2json(xml);
        System.out.println(jsonStr);
       return json2Map(jsonStr);
    }

    public static String xml2json(String xml) {
        StringReader input = new StringReader(xml);
        StringWriter output = new StringWriter();
        JsonXMLConfig config = new JsonXMLConfigBuilder().autoArray(true).autoPrimitive(true).prettyPrint(true).build();
        try {
            XMLEventReader reader = XMLInputFactory.newInstance().createXMLEventReader(input);
            XMLEventWriter writer = new JsonXMLOutputFactory(config).createXMLEventWriter(output);
            writer.add(reader);
            reader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return output.toString();
    }




    public static String json2xmlReplaceBlank(String json) {
        String str = json2xml(json);
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

}

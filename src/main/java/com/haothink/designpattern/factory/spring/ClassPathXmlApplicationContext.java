package com.haothink.designpattern.factory.spring;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

@SuppressWarnings("unchecked")
public class ClassPathXmlApplicationContext implements ApplicationContext{
	private final Map<String,Object> container = new ConcurrentHashMap<>();
	private Document document = null;
	
	public ClassPathXmlApplicationContext() {
	}

	public ClassPathXmlApplicationContext(String configLocation) {

		SAXReader saxReader = new SAXReader();
		InputStream in = this.getClass().getResourceAsStream(configLocation);
		try {
			this.document = saxReader.read(in);
			saveBeanMsg();
		} catch (Exception e) {

			e.printStackTrace();
		}


	}
	@Override
	public Object getBean(String id) {
		if(id==null||id=="")
			throw new RuntimeException("id����Ϊ��");
		Object obj = container.get(id);
		if(obj == null)
			throw new RuntimeException("ID������");
		return obj;
	}
	/*1.��ȡxml�ļ�bean�ڵ������ID��
	 * class��ʵ��ȫ�����浽map������
	 * 
	 * */
	private void saveBeanMsg() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
	
		Element root = document.getRootElement();
		//��һ��,�����ӱ�ǩ���ƻ�ȡ���е�bean��ǩ
		
		List<Element> list = root.elements("bean");
		
		for(Element bean : list) {
			//��ȡbean��ǩ������
			String pid = bean.attributeValue("id");
			String clazz= bean.attributeValue("class");
			System.out.println(pid);
			System.out.println(clazz);
			//��ȡclass��ʵ������
			Object obj = getObjectByClazz(clazz);
			container.put(pid, obj);
			
		}
	}
	@SuppressWarnings({ "rawtypes" })
	private Object getObjectByClazz(String clazz) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		
		Class cl = Class.forName(clazz);
		
		return cl.newInstance();
	} 


	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}



}

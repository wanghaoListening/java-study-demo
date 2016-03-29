package com.haothink.designpattern.interceptor;
/*
 * 责任链模式
 * 
 * */
public class MainTest {
	
	public static void main(String[] args) {
		Request request = new Request();
		Response response = new Response();
		request.setMessage("<html></html>");
		response.setMessage("响应开始了");
		
		FilterChain fc = new FilterChain();
		HtmlFilter hf = new HtmlFilter();
		WordFilter wf = new WordFilter();
		fc.addFilter(hf)
		.addFilter(wf);
		fc.doFilter(request, response, fc);		
		System.out.println(request.getMessage());
		System.out.println(response.getMessage());	
		
	}
}

package com.haothink.designpattern.interceptor;
/*
 *
 * 
 * */
public class MainTest {
	
	public static void main(String[] args) {
		Request request = new Request();
		Response response = new Response();
		request.setMessage("<html></html>");
		response.setMessage("hello");
		
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

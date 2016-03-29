package com.haothink.designpattern.interceptor;

public class WordFilter implements Filter {
	@Override
	public void doFilter(Request request, Response response ,FilterChain fc) {
			String msg = request.getMessage().replaceAll("ÖÐ¹²", "..requestWordFilter");
			request.setMessage(msg);
			fc.doFilter(request, response, fc);
			String str = response.getMessage()+"responseWord";
			response.setMessage(str);
	}

}

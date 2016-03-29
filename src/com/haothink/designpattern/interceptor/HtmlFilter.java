package com.haothink.designpattern.interceptor;

public class HtmlFilter implements Filter {
	@Override
	public void doFilter(Request request, Response response,FilterChain fc) {
		
			String msg = request.getMessage()
			.replaceAll("<", "(requestHtml")
			.replaceAll(">", ")requestHtml");
			request.setMessage(msg);
			fc.doFilter(request, response, fc);
			String str = response.getMessage()+"responseHtml";
			response.setMessage(str);

	}

}

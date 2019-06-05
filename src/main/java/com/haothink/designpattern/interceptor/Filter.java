package com.haothink.designpattern.interceptor;

public interface Filter {
	void doFilter(Request request,Response response,FilterChain fc);
}

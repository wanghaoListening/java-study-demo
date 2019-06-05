package com.haothink.designpattern.interceptor;

import java.util.ArrayList;
import java.util.List;

public class FilterChain implements Filter{
	
	private List<Filter> filters = new ArrayList<Filter>();
	private int index;
	public FilterChain addFilter(Filter filter){
		
		this.filters.add(filter);
		return this;
	}

	@Override
	public void doFilter(Request request, Response response,FilterChain fc ) {
		if(filters.size()==index){return;}
		Filter filter = filters.get(index);
		index++;
		filter.doFilter(request, response, fc);
		
	}
	
	
}

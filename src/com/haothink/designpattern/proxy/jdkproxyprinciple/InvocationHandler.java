package com.haothink.designpattern.proxy.jdkproxyprinciple;

import java.lang.reflect.Method;

public interface InvocationHandler {

		public void invoke(Object o,Method m);
}

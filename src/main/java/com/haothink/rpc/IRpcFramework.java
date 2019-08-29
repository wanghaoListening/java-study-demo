package com.haothink.rpc;

/**
 * @author wanghao
 * @date 2019年08月29日 13:38
 * description:
 */
public interface IRpcFramework {

    void export(final Object service, int port) throws Exception;

    <T> T refer(final Class<T> interfaceClass, final String host, final int port) throws Exception;
}

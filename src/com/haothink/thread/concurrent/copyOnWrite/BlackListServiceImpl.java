package com.haothink.thread.concurrent.copyOnWrite;

import java.util.Map;
/**
 * CopyOnWrite��Ӧ�ó���
 *
 *	CopyOnWrite�����������ڶ���д�ٵĲ������������������������������Ʒ��Ŀ�ķ��ʺ͸��³�����
 *	����������һ��������վ���û��������վ���������У�����ؼ����������ݣ�����ĳЩ�ؼ��ֲ�����������
 *	��Щ���ܱ������Ĺؼ��ֻᱻ����һ�����������У�������ÿ�����ϸ���һ�Ρ����û�����ʱ��
 *	���鵱ǰ�ؼ����ڲ��ں��������У�����ڣ�����ʾ����������ʵ�ִ������£�
 * */
public class BlackListServiceImpl {

    private static CopyOnWriteMap<String, Boolean> blackListMap = new CopyOnWriteMap<String, Boolean>(1000);

    public static boolean isBlackList(String id) {
        return blackListMap.get(id) == null ? false : true;
    }

    public static void addBlackList(String id) {
        blackListMap.put(id, Boolean.TRUE);
    }

    /**
     * ������Ӻ�����
     *
     * @param ids
     */
    public static void addBlackList(Map<String,Boolean> ids) {
        blackListMap.putAll(ids);
    }
}
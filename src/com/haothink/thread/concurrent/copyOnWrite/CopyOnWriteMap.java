package com.haothink.thread.concurrent.copyOnWrite;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 * JDK�в�û���ṩCopyOnWriteMap�����ǿ���
 * �ο�CopyOnWriteArrayList��ʵ��һ���������������£�
 * CopyOnWrite������дʱ���Ƶ�������ͨ�׵�����ǵ�������һ���������Ԫ�ص�ʱ��
 * ��ֱ������ǰ������ӣ������Ƚ���ǰ��������Copy�����Ƴ�һ���µ�������Ȼ���µ�����
 * �����Ԫ�أ������Ԫ��֮���ٽ�ԭ����������ָ���µ��������������ĺô������ǿ��Զ�
 * CopyOnWrite�������в����Ķ���������Ҫ��������Ϊ��ǰ������������κ�Ԫ�ء�
 * ����CopyOnWrite����Ҳ��һ�ֶ�д�����˼�룬����д��ͬ��������
 * ==ʹ��CopyOnWriteע������
 *�������ݿ���������ʵ����Ҫ����ʼ��CopyOnWriteMap�Ĵ�С������дʱCopyOnWriteMap���ݵĿ�����
 *ʹ��������ӡ���Ϊÿ����ӣ�����ÿ�ζ�����и��ƣ����Լ�����Ӵ��������Լ��������ĸ��ƴ�������ʹ������������addBlackList������
 *
 *===CopyOnWrite��ȱ��
 *
 *	CopyOnWrite�����кܶ��ŵ㣬����ͬʱҲ�����������⣬���ڴ�ռ�����������һ�������⡣
 *	�����ڿ�����ʱ����Ҫע��һ�¡��ڴ�ռ������
 *	
 *
 *	��ΪCopyOnWrite��дʱ���ƻ��ƣ������ڽ���д������ʱ���ڴ����ͬʱפ������������ڴ棬
 *�ɵĶ������д��Ķ���ע��:�ڸ��Ƶ�ʱ��ֻ�Ǹ�������������ã�ֻ����д��ʱ��ᴴ���¶�����ӵ���
 *��������������Ķ�����ʹ�ã����������ݶ����ڴ棩�������Щ����ռ�õ��ڴ�Ƚϴ󣬱���˵200M
 *���ң���ô��д��100M���ݽ�ȥ���ڴ�ͻ�ռ��300M����ô���ʱ����п������Ƶ����Yong GC��Full GC��
 *֮ǰ����ϵͳ��ʹ����һ����������ÿ��ʹ��CopyOnWrite���Ƹ��´���������ÿ��15���Full GC��
 *Ӧ����Ӧʱ��Ҳ��֮�䳤��
 *
 *����ڴ�ռ�����⣬����ͨ��ѹ�������е�Ԫ�صķ��������ٴ������ڴ����ģ����磬���Ԫ��ȫ��
 *10���Ƶ����֣����Կ��ǰ���ѹ����36���ƻ�64���ơ����߲�ʹ��CopyOnWrite��������ʹ������
 *�Ĳ�����������ConcurrentHashMap��
 *����һ��������
 *
 *	CopyOnWrite����ֻ�ܱ�֤���ݵ�����һ���ԣ����ܱ�֤���ݵ�ʵʱһ���ԡ����������ϣ��д
 *��ĵ����ݣ������ܶ������벻Ҫʹ��CopyOnWrite������
 * */
public class CopyOnWriteMap<K, V> implements Map<K, V>, Cloneable {
	//��volatile������map��֤�����ܼ�ʱͬ�����µ����ڴ���ȥ��
    private volatile Map<K, V> internalMap;

    public CopyOnWriteMap() {
        internalMap = new HashMap<K, V>();
    }
    
    public CopyOnWriteMap(int capacity) {
        internalMap = new HashMap<K, V>(capacity);
    }
      
    public V put(K key, V value) {

        synchronized (this) {
            Map<K, V> newMap = new HashMap<K, V>(internalMap);
            V val = newMap.put(key, value);
            internalMap = newMap;
            return val;
        }
    }

    public V get(Object key) {
        return internalMap.get(key);
    }

    public void putAll(Map<? extends K, ? extends V> newData) {
        synchronized (this) {
            Map<K, V> newMap = new HashMap<K, V>(internalMap);
            newMap.putAll(newData);
            internalMap = newMap;
        }
    }

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}
}

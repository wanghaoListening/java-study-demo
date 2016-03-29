package com.haothink.ThreadDemo.concurrent.copyOnWrite;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 * JDK中并没有提供CopyOnWriteMap，我们可以
 * 参考CopyOnWriteArrayList来实现一个，基本代码如下：
 * CopyOnWrite容器即写时复制的容器。通俗的理解是当我们往一个容器添加元素的时候，
 * 不直接往当前容器添加，而是先将当前容器进行Copy，复制出一个新的容器，然后新的容器
 * 里添加元素，添加完元素之后，再将原容器的引用指向新的容器。这样做的好处是我们可以对
 * CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。
 * 所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器。
 * ==使用CopyOnWrite注意事项
 *减少扩容开销。根据实际需要，初始化CopyOnWriteMap的大小，避免写时CopyOnWriteMap扩容的开销。
 *使用批量添加。因为每次添加，容器每次都会进行复制，所以减少添加次数，可以减少容器的复制次数。如使用上面代码里的addBlackList方法。
 *
 *===CopyOnWrite的缺点
 *
 *	CopyOnWrite容器有很多优点，但是同时也存在两个问题，即内存占用问题和数据一致性问题。
 *	所以在开发的时候需要注意一下。内存占用问题
 *	
 *
 *	因为CopyOnWrite的写时复制机制，所以在进行写操作的时候，内存里会同时驻扎两个对象的内存，
 *旧的对象和新写入的对象（注意:在复制的时候只是复制容器里的引用，只是在写的时候会创建新对象添加到新
 *容器里，而旧容器的对象还在使用，所以有两份对象内存）。如果这些对象占用的内存比较大，比如说200M
 *左右，那么再写入100M数据进去，内存就会占用300M，那么这个时候很有可能造成频繁的Yong GC和Full GC。
 *之前我们系统中使用了一个服务由于每晚使用CopyOnWrite机制更新大对象，造成了每晚15秒的Full GC，
 *应用响应时间也随之变长。
 *
 *针对内存占用问题，可以通过压缩容器中的元素的方法来减少大对象的内存消耗，比如，如果元素全是
 *10进制的数字，可以考虑把它压缩成36进制或64进制。或者不使用CopyOnWrite容器，而使用其他
 *的并发容器，如ConcurrentHashMap。
 *数据一致性问题
 *
 *	CopyOnWrite容器只能保证数据的最终一致性，不能保证数据的实时一致性。所以如果你希望写
 *入的的数据，马上能读到，请不要使用CopyOnWrite容器。
 * */
public class CopyOnWriteMap<K, V> implements Map<K, V>, Cloneable {
	//用volatile来修饰map保证数据能及时同步更新到主内存中去。
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

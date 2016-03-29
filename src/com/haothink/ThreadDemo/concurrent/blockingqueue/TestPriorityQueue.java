package com.haothink.ThreadDemo.concurrent.blockingqueue;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
/**
 * PriorityBlockingQueue里面存储的对象必须是实现Comparable接口。
 * 队列通过这个接口的compare方法确定对象的priority。
 *	规则是：当前和其他对象比较，如果compare方法返回负数，那么在队列里面的优先级就比较搞。 
 * */
public class TestPriorityQueue {  
	   
    static Random r=new Random(47);  
      
    @SuppressWarnings("rawtypes")
	public static void main(String args[]) throws InterruptedException{  
        final PriorityBlockingQueue q=new PriorityBlockingQueue();  
        ExecutorService se=Executors.newCachedThreadPool();  
        //execute producer  
        se.execute(new Runnable(){  
            @SuppressWarnings("unchecked")
			public void run() {  
                int i=0;  
                for(int j=0;j<=20;j++){ 
                    q.put(new PriorityEntity(r.nextInt(10),i++));  
                    try {  
                        TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));  
                    } catch (InterruptedException e) {  
                        // TODO Auto-generated catch block  
                        e.printStackTrace();  
                    }  
                }  
            }  
        }); 
        TimeUnit.MILLISECONDS.sleep(10000); 
        //execute consumer  
        se.execute(new Runnable(){  
            public void run() {  
                while(true){  
                    try {  
                        System.out.println("take== "+q.take()+" left:== ["+q.toString()+"]");  
                        try {  
                            TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));  
                        } catch (InterruptedException e) {  
                            // TODO Auto-generated catch block  
                            e.printStackTrace();  
                        }  
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
        });  
        try {  
            TimeUnit.SECONDS.sleep(5);  
        } catch (InterruptedException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        System.out.println("shutdown"); 
        TimeUnit.MILLISECONDS.sleep(7000); 
        
    }  
 
}  
 
class PriorityEntity implements Comparable<PriorityEntity> {  
    private static int count=0;  
    private int id=count++;  
    private int priority;  
    private int index=0;  
 
    public PriorityEntity(int priority,int index) {  
        this.priority = priority;  
        this.index=index;  
    }  
      
    public String toString(){  
        return id+"* [index="+index+" priority="+priority+"]";  
    }  
 
//    //数字小，优先级高  
//    public int compareTo(PriorityEntity o) {  
//        return this.priority > o.priority ? 1 
//                : this.priority < o.priority ? -1 : 0;  
//    }  
 
    //数字大，优先级高  
  public int compareTo(PriorityEntity o) {  
      return this.priority < o.priority ? 1  
              : this.priority > o.priority ? -1 : 0;  
  }  
} 

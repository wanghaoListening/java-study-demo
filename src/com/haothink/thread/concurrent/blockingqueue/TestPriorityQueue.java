package com.haothink.thread.concurrent.blockingqueue;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
/**
 * PriorityBlockingQueue����洢�Ķ��������ʵ��Comparable�ӿڡ�
 * ����ͨ������ӿڵ�compare����ȷ�������priority��
 *	�����ǣ���ǰ����������Ƚϣ����compare�������ظ�������ô�ڶ�����������ȼ��ͱȽϸ㡣 
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
 
//    //����С�����ȼ���  
//    public int compareTo(PriorityEntity o) {  
//        return this.priority > o.priority ? 1 
//                : this.priority < o.priority ? -1 : 0;  
//    }  
 
    //���ִ����ȼ���  
  public int compareTo(PriorityEntity o) {  
      return this.priority < o.priority ? 1  
              : this.priority > o.priority ? -1 : 0;  
  }  
} 

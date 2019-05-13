package com.haothink.thread.concurrent.countdownlatch;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
/**
 * Ӧ�ó���
	��ĳ�������У�����һ�����͵����񣬳�����Ҫ����ö�������ȥִ�У�ֻ�е�����������ִ�����ʱ��
	����ִ����������ʱ�򣬾Ϳ���ѡ��CyclicBarrier
        ʵ������
           ������Ҫͳ��ȫ����ҵ�����ݡ����и�ʡ�����ݿ��Ƕ����ģ�Ҳ����˵��ʡ�ֿ⡣
           ����ͳ�Ƶ��������ܴ�ͳ�ƹ���Ҳ�Ƚ�����Ϊ��������ܣ����ټ��㡣���ǲ�ȡ�����ķ�ʽ��
           ����߳�ͬʱ�����ʡ���ݣ�����ٻ���ͳ�ơ�������CyclicBarrier�ͷǳ����á������룺

 * */

/**  
 * ��ʡ���ݶ������ֿ�悮��Ϊ����߼������ܣ�ͳ��ʱ����ÿ��ʡ��һ���߳��ȼ��㵥ʡ����������ܡ�  
 *   
 * @author  
 *   
 */  
public class CyclicBarrierTest2 {   
  
    // private ConcurrentHashMap result = new ConcurrentHashMap();   
  
    public static void main(String[] args) {   
    	TotalServiceImpl totalService = new TotalServiceImpl();   
        CyclicBarrier barrier = new CyclicBarrier(5,   
                new TotalTask(totalService));   
  
        // ʵ��ϵͳ�ǲ������ʡ����code���б�Ȼ��ѭ����ÿ��code����һ���̡߳�   
        new BillTask(new BillServiceImpl(), barrier, "����").start();   
        new BillTask(new BillServiceImpl(), barrier, "�Ϻ�").start();   
        new BillTask(new BillServiceImpl(), barrier, "����").start();   
        new BillTask(new BillServiceImpl(), barrier, "�Ĵ�").start();   
        new BillTask(new BillServiceImpl(), barrier, "������").start();   
  
    }   
}   
  
/**  
 * �����񣺻�������  
 */  
class TotalTask implements Runnable {   
    private TotalServiceImpl totalService;   
  
    TotalTask(TotalServiceImpl totalService) {   
        this.totalService = totalService;   
    }   
  
    public void run() {   
        // ��ȡ�ڴ��и�ʡ�����ݻ��ܣ������ԡ�   
        totalService.count();   
        System.out.println("=======================================");   
        System.out.println("��ʼȫ������");   
    }   
}   
  
/**  
 * �����񣺼Ʒ�����  
 */  
class BillTask extends Thread {   
    // �Ʒѷ���   
    private BillServiceImpl billService;   
    private CyclicBarrier barrier;   
    // ���룬��ʡ������࣬��ʡ���ݿ������   
    private String code;   
  
    BillTask(BillServiceImpl billService, CyclicBarrier barrier, String code) {   
        this.billService = billService;   
        this.barrier = barrier;   
        this.code = code;   
    }   
  
    public void run() {   
        System.out.println("��ʼ����--" + code + "ʡ--���ݣ�");   
        billService.bill(code);   
        // ��bill������������ڴ棬��ConcurrentHashMap,vector��,������   
        System.out.println(code + "ʡ�Ѿ��������,��֪ͨ����Service��");   
        try {   
            // ֪ͨbarrier�Ѿ����   
            barrier.await();   
        } catch (InterruptedException e) {   
            e.printStackTrace();   
        } catch (BrokenBarrierException e) {   
            e.printStackTrace();   
        }   
    }   
  
}

class TotalServiceImpl{

	public void count() {
		
		
	}
	
}

class BillServiceImpl{

	public void bill(String code) {
		
		
	}
	
}


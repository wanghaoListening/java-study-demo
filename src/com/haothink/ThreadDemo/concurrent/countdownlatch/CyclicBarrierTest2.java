package com.haothink.ThreadDemo.concurrent.countdownlatch;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
/**
 * 应用场景
	在某种需求中，比如一个大型的任务，常常需要分配好多子任务去执行，只有当所有子任务都执行完成时候，
	才能执行主任务，这时候，就可以选择CyclicBarrier
        实例分析
           我们需要统计全国的业务数据。其中各省的数据库是独立的，也就是说按省分库。
           并且统计的数据量很大，统计过程也比较慢。为了提高性能，快速计算。我们采取并发的方式，
           多个线程同时计算各省数据，最后再汇总统计。在这里CyclicBarrier就非常有用。看代码：

 * */

/**  
 * 各省数据独立，分库存偖。为了提高计算性能，统计时采用每个省开一个线程先计算单省结果，最后汇总。  
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
  
        // 实际系统是查出所有省编码code的列表，然后循环，每个code生成一个线程。   
        new BillTask(new BillServiceImpl(), barrier, "北京").start();   
        new BillTask(new BillServiceImpl(), barrier, "上海").start();   
        new BillTask(new BillServiceImpl(), barrier, "广西").start();   
        new BillTask(new BillServiceImpl(), barrier, "四川").start();   
        new BillTask(new BillServiceImpl(), barrier, "黑龙江").start();   
  
    }   
}   
  
/**  
 * 主任务：汇总任务  
 */  
class TotalTask implements Runnable {   
    private TotalServiceImpl totalService;   
  
    TotalTask(TotalServiceImpl totalService) {   
        this.totalService = totalService;   
    }   
  
    public void run() {   
        // 读取内存中各省的数据汇总，过程略。   
        totalService.count();   
        System.out.println("=======================================");   
        System.out.println("开始全国汇总");   
    }   
}   
  
/**  
 * 子任务：计费任务  
 */  
class BillTask extends Thread {   
    // 计费服务   
    private BillServiceImpl billService;   
    private CyclicBarrier barrier;   
    // 代码，按省代码分类，各省数据库独立。   
    private String code;   
  
    BillTask(BillServiceImpl billService, CyclicBarrier barrier, String code) {   
        this.billService = billService;   
        this.barrier = barrier;   
        this.code = code;   
    }   
  
    public void run() {   
        System.out.println("开始计算--" + code + "省--数据！");   
        billService.bill(code);   
        // 把bill方法结果存入内存，如ConcurrentHashMap,vector等,代码略   
        System.out.println(code + "省已经计算完成,并通知汇总Service！");   
        try {   
            // 通知barrier已经完成   
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


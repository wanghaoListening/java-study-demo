package com.haothink.thread.concurrent.countdownlatch;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest2 {   

  
    public static void main(String[] args) {   
    	TotalServiceImpl totalService = new TotalServiceImpl();   
        CyclicBarrier barrier = new CyclicBarrier(5,   
                new TotalTask(totalService));   

        new BillTask(new BillServiceImpl(), barrier, "1").start();
        new BillTask(new BillServiceImpl(), barrier, "2").start();
        new BillTask(new BillServiceImpl(), barrier, "3").start();
        new BillTask(new BillServiceImpl(), barrier, "4").start();
        new BillTask(new BillServiceImpl(), barrier, "5").start();
  
    }   
}   
  
/**  
 *
 */  
class TotalTask implements Runnable {   
    private TotalServiceImpl totalService;   
  
    TotalTask(TotalServiceImpl totalService) {   
        this.totalService = totalService;   
    }   

    @Override
    public void run() {
        totalService.count();   
        System.out.println("=======================================");   

    }   
}   
  
/**  
 *
 */  
class BillTask extends Thread {   

    private BillServiceImpl billService;   
    private CyclicBarrier barrier;   

    private String code;   
  
    BillTask(BillServiceImpl billService, CyclicBarrier barrier, String code) {   
        this.billService = billService;   
        this.barrier = barrier;   
        this.code = code;   
    }   

    @Override
    public void run() {   
        System.out.println(code);
        billService.bill(code);   

        System.out.println(code);
        try {   

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


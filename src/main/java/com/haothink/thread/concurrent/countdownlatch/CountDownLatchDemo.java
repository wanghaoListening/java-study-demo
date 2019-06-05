package com.haothink.thread.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.haothink.thread.concurrent.blockingqueue.Player;
 
  public class CountDownLatchDemo {
     private static final int PLAYER_AMOUNT = 5;
     public CountDownLatchDemo() {
         // TODO Auto-generated constructor stub    
      }
     /**
      * @param args
      */
     public static void main(String[] args) {
         // TODO Auto-generated method stub

         CountDownLatch begin = new CountDownLatch(1);

         CountDownLatch end = new CountDownLatch(PLAYER_AMOUNT);
         Player[] plays = new Player[PLAYER_AMOUNT];
         
         for(int i=0;i<PLAYER_AMOUNT;i++)
             plays[i] = new Player(i+1,begin,end);
         

         ExecutorService exe = Executors.newFixedThreadPool(PLAYER_AMOUNT);
         for(Player p:plays)
             exe.execute(p);
         System.out.println("Race begins!");
         begin.countDown();
         try{

             end.await();
         }catch (InterruptedException e) {
             // TODO: handle exception
             e.printStackTrace();
         }finally{
             System.out.println("Race ends!");
         }
         exe.shutdown();
     }
 }
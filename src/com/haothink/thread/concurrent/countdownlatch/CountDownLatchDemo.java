package com.haothink.thread.concurrent.countdownlatch;

/**

CountDownLatch����һ��ͬ��������,����ʱ����int����,�ò������Ǽ������ĳ�ʼֵ��
ÿ����һ��countDown()��������������1,����������0 ʱ��
await()�����������������ִ�У�CountDownLatch������д��
��һ����������������������������0ʱ�����ض����¼��������������ԣ�
���������̵߳ȴ����̵߳Ľ�����������һ��ģ���˶�Ա���������Ӽ���˵����
*/

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
         //����ÿλ�˶�Ա��CountDownLatch��1�󼴽�������
         CountDownLatch begin = new CountDownLatch(1);
         //�������������������˶�Ա������������
         CountDownLatch end = new CountDownLatch(PLAYER_AMOUNT);
         Player[] plays = new Player[PLAYER_AMOUNT];
         
         for(int i=0;i<PLAYER_AMOUNT;i++)
             plays[i] = new Player(i+1,begin,end);
         
         //�����ض����̳߳أ���СΪ5
         // ����һ�������ù̶��߳������̳߳أ��Թ�����޽���з�ʽ��������Щ�̡߳�
         ExecutorService exe = Executors.newFixedThreadPool(PLAYER_AMOUNT);
         for(Player p:plays)
             exe.execute(p);            //�����߳�
         System.out.println("Race begins!");
         begin.countDown();
         try{
        	 //ʹ��ǰ�߳�������������������֮ǰһֱ�ȴ��������̱߳��жϡ�
             end.await();            //�ȴ�end״̬��Ϊ0����Ϊ��������
         }catch (InterruptedException e) {
             // TODO: handle exception
             e.printStackTrace();
         }finally{
             System.out.println("Race ends!");
         }
         exe.shutdown();
     }
 }
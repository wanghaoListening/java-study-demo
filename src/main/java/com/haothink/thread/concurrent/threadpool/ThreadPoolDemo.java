package com.haothink.thread.concurrent.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * java.uitl.concurrent.ThreadPoolExecutor�����̳߳�������ĵ�һ����
 * ��ArrayBlockingQueue��PriorityBlockingQueueʹ�ý��٣�
* һ��ʹ��LinkedBlockingQueue��Synchronous���̳߳ص��ŶӲ�����BlockingQueue�йء�
*     handler����ʾ���ܾ���������ʱ�Ĳ��ԣ�����������ȡֵ��

	ThreadPoolExecutor.AbortPolicy:���������׳�RejectedExecutionException�쳣�� 
	ThreadPoolExecutor.DiscardPolicy��Ҳ�Ƕ������񣬵��ǲ��׳��쳣�� 
	ThreadPoolExecutor.DiscardOldestPolicy������������ǰ�������Ȼ�����³���ִ�������ظ��˹��̣�
	ThreadPoolExecutor.CallerRunsPolicy���ɵ����̴߳�������� 
	1.public class ThreadPoolExecutor extends AbstractExecutorService
	2.public abstract class AbstractExecutorService implements ExecutorService
	3.public interface ExecutorService extends Executor {
	public interface Executor {
    void execute(Runnable command);
	}
	������ThreadPoolExecutor�����м����ǳ���Ҫ�ķ���
	
		execute()
		submit()
		shutdown()
		shutdownNow()
*1�����ȣ�Ҫ���corePoolSize��maximumPoolSize�ĺ��壻
*
*����2����Σ�Ҫ֪��Worker��������ʲô���õģ�
*
*����3��Ҫ֪�������ύ���̳߳�֮��Ĵ�����ԣ������ܽ�һ����Ҫ��4�㣺
*
*   �����ǰ�̳߳��е��߳���ĿС��corePoolSize����ÿ��һ�����񣬾ͻᴴ��һ���߳�ȥִ���������
*    �����ǰ�̳߳��е��߳���Ŀ>=corePoolSize����ÿ��һ�����񣬻᳢�Խ�����ӵ����񻺴���е��У�
*    ����ӳɹ�����������ȴ������߳̽���ȡ��ȥִ�У������ʧ�ܣ�һ����˵�����񻺴������������
*    ��᳢�Դ����µ��߳�ȥִ���������
*    �����ǰ�̳߳��е��߳���Ŀ�ﵽmaximumPoolSize������ȡ����ܾ����Խ��д���
*    ����̳߳��е��߳��������� corePoolSizeʱ�����ĳ�߳̿���ʱ�䳬��keepAliveTime��
*    �߳̽�����ֹ��ֱ���̳߳��е��߳���Ŀ������corePoolSize���������Ϊ���ĳ��е��߳����ô��ʱ�䣬
*   ��ô���ĳ��е��߳̿���ʱ�䳬��keepAliveTime���߳�Ҳ�ᱻ��ֹ��
*	5.����ܾ�����

�������̳߳ص����񻺴�������������̳߳��е��߳���Ŀ�ﵽmaximumPoolSize����������������ͻ��ȡ����ܾ����ԣ�
ͨ�����������ֲ��ԣ�
ThreadPoolExecutor.AbortPolicy:���������׳�RejectedExecutionException�쳣��
ThreadPoolExecutor.DiscardPolicy��Ҳ�Ƕ������񣬵��ǲ��׳��쳣��
ThreadPoolExecutor.DiscardOldestPolicy������������ǰ�������Ȼ�����³���ִ�������ظ��˹��̣�
ThreadPoolExecutor.CallerRunsPolicy���ɵ����̴߳�������� 
*
 * */
public class ThreadPoolDemo {
	
	 public static void main(String[] args) {   
         ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                 new ArrayBlockingQueue<Runnable>(5));
          
         for(int i=0;i<15;i++){
             MyTask myTask = new MyTask(i);
             executor.execute(myTask);
             System.out.println("�̳߳����߳���Ŀ��"+executor.getPoolSize()+"�������еȴ�ִ�е�������Ŀ��"+
             executor.getQueue().size()+"����ִ������������Ŀ��"+executor.getCompletedTaskCount());
         }
         executor.shutdown();
     }
}
 
 
class MyTask implements Runnable {
    private int taskNum;
     
    public MyTask(int num) {
        this.taskNum = num;
    }
     
    @SuppressWarnings("static-access")
	@Override
    public void run() {
        System.out.println("����ִ��task "+taskNum);
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task "+taskNum+"ִ�����");
    }
}
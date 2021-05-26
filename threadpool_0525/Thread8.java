package threadpool_0525;

import java.util.Random;

/**
 * ClassName:Thread8
 * Package:threadpool_0525
 * Description:
 *
 * @Author:HP
 * @date:2021/5/25 21:18
 */
public class Thread8 {
    static class MyBlockingQueue {
        private int[] val;
        private int fir;
        private int lat;
        private int size;

        public MyBlockingQueue (int initial) {
            //初始化变量
            val = new int[initial];
            fir = 0;
            lat = 0;
            size = 0;
        }

        public void offer (int value) throws InterruptedException {
            //判断边界值
            synchronized (this) {
                if(size == val.length) {
                    this.wait();
                }
                val[lat++] = value;
                size++;

                if(lat == val.length) lat = 0;

                //尝试唤醒消费者
                this.notify();
            }

        }

        //查询方法
        public int poll () throws InterruptedException {
            int res = 0;
            synchronized (this) {
                if(size == 0) {
                    this.wait();
                }
                res = val[fir++];
                size--;

                if(fir == val.length) fir = 0;

                this.notify();
            }
            return res;
        }

    }

    public static void main(String[] args) {
        MyBlockingQueue queue = new MyBlockingQueue(100);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    int num = new Random().nextInt(10);
                    System.out.println(num);
                    try {
                        queue.offer(num);
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    int res = 0;
                    try {
                        res = queue.poll();
                        System.out.println(res);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        t2.start();
    }
}

package threadpool_0525;

/**
 * ClassName:ThreadPool7
 * Package:threadpool_0525
 * Description:
 *
 * @Author:HP
 * @date:2021/5/25 21:01
 */
public class ThreadPool7 {
    static class Singleton {
        public Singleton() {
        }
        private static volatile Singleton singleton = null;

        public static Singleton getInstance() {
            if(singleton == null) {
                synchronized (Singleton.class) {
                    if(singleton == null) {
                        singleton = new Singleton();
                    }
                }
            }
            return singleton;
        }
    }

    private static Singleton s1 = null;
    private static Singleton s2 = null;
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                s1 = Singleton.getInstance();
            }
        });
        t1.start();
        s2 = Singleton.getInstance();
        t1.join();
        System.out.println(s1 == s2);
    }
}

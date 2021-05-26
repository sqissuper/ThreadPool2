package threadpool_0525;

/**
 * ClassName:ThreadPool1
 * Package:threadpool_0525
 * Description:
 *
 * @Author:HP
 * @date:2021/5/25 18:51
 */
public class ThreadPool5 {
    static class Singleton {
        //创建私有的构造函数
        private Singleton() {

        }

        //定义私有变量
        private static Singleton singleton = null;

        //提供公共的获取实例的方法
        public static Singleton getInstance() {
            if(singleton == null) {
                synchronized(Singleton.class) {
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
//        //创建第一个对象
//        Singleton s1 = Singleton.getInstance();
//
//        //创建第二个对象
//        Singleton s2 = Singleton.getInstance();
//        System.out.println(s1 == s2);

    }
}

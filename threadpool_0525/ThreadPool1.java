package threadpool_0525;

/**
 * ClassName:ThreadPool1
 * Package:threadpool_0525
 * Description:
 *
 * @Author:HP
 * @date:2021/5/25 18:51
 */
public class ThreadPool1 {
    static class Singleton {
        //创建私有的构造函数
        private Singleton() {

        }

        //定义私有变量
        private static Singleton singleton = new Singleton();

        //提供公共的获取实例的方法
        public static Singleton getInstance() {
            return singleton;
        }
    }

    public static void main(String[] args) {
        Singleton singleton = new Singleton();
        System.out.println(singleton);
    }
}

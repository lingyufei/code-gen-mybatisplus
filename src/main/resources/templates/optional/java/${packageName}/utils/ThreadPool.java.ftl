package ${packageName}.utils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
* @author LYF_
* @create 2022-09-24 16:01
*/
public class ThreadPool {
    public static final Executor MY_THREAD_POOL = new ThreadPoolExecutor(4, 8, 5,
            TimeUnit.MINUTES, new ArrayBlockingQueue<>(100000), new ThreadFactory() {
        final AtomicInteger count = new AtomicInteger(0);
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("MY_THREAD_" + count.getAndAdd(1));
            return thread;
        }
    },new ThreadPoolExecutor.CallerRunsPolicy());
}

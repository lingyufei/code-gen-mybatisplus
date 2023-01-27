package ${packageName}.utils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
* 线程池
* @author ${author}
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

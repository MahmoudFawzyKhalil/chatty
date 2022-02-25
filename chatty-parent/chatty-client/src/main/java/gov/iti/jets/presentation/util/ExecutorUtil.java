package gov.iti.jets.presentation.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorUtil {
    private static final ExecutorUtil INSTANCE = new ExecutorUtil();
    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    private ExecutorUtil() {

    }

    public static ExecutorUtil getInstance() {
        return INSTANCE;
    }

    public <T> Future<T> submit(Callable<T> callable) {
        return executorService.submit(callable);
    }

    public void execute(Runnable runnable) {
        executorService.execute(runnable);
    }

    public void shutDown(){
        executorService.shutdown();
    }

}

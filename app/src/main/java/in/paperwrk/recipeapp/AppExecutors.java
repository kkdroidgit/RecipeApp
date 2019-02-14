package in.paperwrk.recipeapp;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutors {

    private static AppExecutors instance;

    public static AppExecutors getInstance() {
        if (instance == null) instance = new AppExecutors();
        return instance;
    }

    // schedule threads to run after a delay
    private final ScheduledExecutorService service = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService getService() {
        return service;
    }
}

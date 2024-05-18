import dataStore.DataStoreImpl;
import handler.TaskRunner;
import models.OneTimeTask;
import models.RecurringTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        DataStoreImpl dataStore = DataStoreImpl.getInstance(100);
        RecurringTask recurringTask = new RecurringTask(System.currentTimeMillis() + 5000, 10000);
        OneTimeTask oneTimeTask = new OneTimeTask(System.currentTimeMillis() + 3000);
        dataStore.addTask(oneTimeTask);
        dataStore.addTask(recurringTask);
        TaskRunner taskRunner = new TaskRunner(dataStore);
        for (int i = 0; i < 10; i++) {
            executor.submit(taskRunner::runTask);
        }

        System.out.println("Hello world!");
    }
}
package handler;

import dataStore.IDataStore;
import models.ScheduledTask;

import java.util.Objects;

public class TaskRunner {
    private final IDataStore dataStore;
    public TaskRunner(IDataStore dataStore){
        this.dataStore = dataStore;
    }

    public void runTask() {
        ScheduledTask scheduledTask = null;
        while (true){
            while (dataStore.isEmpty()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            synchronized (this){
                scheduledTask = dataStore.peekTask();
                if (Objects.nonNull(scheduledTask) && scheduledTask.getScheduleTime() <= System.currentTimeMillis()){
                    if (scheduledTask.isRecurring()){
                        dataStore.addTask(scheduledTask.nextTask());
                    }
                    scheduledTask.execute();
                    dataStore.pollTask();
                    this.notifyAll();
                }
            }
        }


    }
}

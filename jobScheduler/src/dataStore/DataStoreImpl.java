package dataStore;

import models.ScheduledTask;

import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.PriorityBlockingQueue;

public class DataStoreImpl implements IDataStore{
    private static DataStoreImpl queueStore;
    private static PriorityBlockingQueue<ScheduledTask> taskPriorityQueue;

    private static final Object lock = new Object();
    private DataStoreImpl(int queueSize) {
        taskPriorityQueue = new PriorityBlockingQueue<>(queueSize, Comparator.comparing(ScheduledTask::getScheduleTime));
    }

    public static DataStoreImpl getInstance(int queueSize){
        if(Objects.isNull(queueStore)){
            synchronized (lock){
                if(Objects.isNull(taskPriorityQueue)){
                    queueStore = new DataStoreImpl(queueSize);
                }
            }
        }

        return queueStore;
    }


    @Override
    public void addTask(ScheduledTask task) {
        taskPriorityQueue.add(task);
    }

    @Override
    public ScheduledTask peekTask() {
        if (taskPriorityQueue.isEmpty()) return null;
        return taskPriorityQueue.peek();
    }

    @Override
    public ScheduledTask pollTask() {
        if (taskPriorityQueue.isEmpty()) return null;
        return taskPriorityQueue.poll();
    }

    @Override
    public boolean isEmpty() {
        return taskPriorityQueue.isEmpty();
    }
}

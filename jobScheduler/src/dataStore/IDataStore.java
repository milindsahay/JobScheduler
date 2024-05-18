package dataStore;

import models.ScheduledTask;

public interface IDataStore {
    void addTask(ScheduledTask task);

    ScheduledTask peekTask();

    ScheduledTask pollTask();

    boolean isEmpty();
}

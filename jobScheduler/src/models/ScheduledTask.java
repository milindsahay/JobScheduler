package models;

public abstract class ScheduledTask {
    public abstract void execute();
    public abstract long getScheduleTime();
    public abstract boolean isRecurring();

    public abstract ScheduledTask nextTask();

}

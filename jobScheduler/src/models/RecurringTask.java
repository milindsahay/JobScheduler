package models;

public class RecurringTask extends ScheduledTask{
    private final long scheduleInterval;
    private final long scheduleTime;
    public RecurringTask(long scheduleTime, long scheduleInterval){
        this.scheduleInterval = scheduleInterval;
        this.scheduleTime = scheduleTime;
    }
    @Override
    public void execute() {
        System.out.println("Executing recurring task at " + System.currentTimeMillis());
    }

    @Override
    public long getScheduleTime() {
        return scheduleTime;
    }

    @Override
    public boolean isRecurring() {
        return true;
    }

    @Override
    public ScheduledTask nextTask() {
        return new RecurringTask(scheduleTime + scheduleInterval, scheduleInterval);
    }
}

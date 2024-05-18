package models;

public class OneTimeTask extends ScheduledTask{

    private final long scheduleTime;

    public OneTimeTask(long scheduleTime){
        this.scheduleTime = scheduleTime;
    }
    @Override
    public void execute() {
        System.out.println("Executing one time task at ->" + System.currentTimeMillis());
    }

    @Override
    public long getScheduleTime() {
        return scheduleTime;
    }

    @Override
    public boolean isRecurring() {
        return false;
    }

    @Override
    public ScheduledTask nextTask() {
        return null;
    }
}

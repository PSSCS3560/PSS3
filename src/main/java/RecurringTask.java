public class RecurringTask extends Task
{
    private long startDate;
    private long endDate;
    private long frequency;

    public RecurringTask(String name, String type, long startTime, double duration, long startDate, long endDate, long frequency)
    {
        super(name, type, startTime, duration);
        this.startDate = startDate;
        this.endDate = endDate;
        this.frequency = frequency;
    }
    public void setStartDate(int date)
    {
        startDate = date;
    }
    public void setEndDate(int date)
    {
        endDate = date;
    }
    public void setFrequency(int freq)
    {
        frequency = freq;
    }
    public long getStartDate()
    {
        return startDate;
    }
    public long getEndDate()
    {
        return endDate;
    }
    public long getFrequency()
    {
        return frequency;
    }
}

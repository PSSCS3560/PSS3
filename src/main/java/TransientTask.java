public class TransientTask extends Task
{
    private long date;

    public TransientTask(String name, String type, long startTime, double duration, long date)
    {
        super(name, type, startTime, duration);
        this.date = date;
    }
    public void setDate(long date)
    {
        this.date = date;
    }
    public long getDate()
    {
        return date;
    }
}

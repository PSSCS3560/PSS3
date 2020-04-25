public class AntiTask extends Task
{
    private long date;

    public AntiTask(String name, String type, long startTime, double duration, long date)
    {
        super(name, type, startTime, duration);
        this.date = date;
    }
    public void setDate(int date)
    {
        this.date = date;
    }
    public long getDate()
    {
        return date;
    }
}

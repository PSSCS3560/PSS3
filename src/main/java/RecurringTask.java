import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class RecurringTask extends Task
{
    private long startDate;
    private long endDate;
    private long frequency;
    public final String taskTypeString = "Recurring";
    public RecurringTask(String name, String type, long startTime, double duration, long startDate, long endDate, long frequency)
    {
        super(name, type, startTime, duration);
        this.startDate = startDate;
        this.endDate = endDate;
        this.frequency = frequency;
    }
    @Override
    public JSONObject toJSONObject()
    {
        JSONObject object = new JSONObject();
        object.put("Name", this.getName());
        object.put("Type", this.getType());
        object.put("StartDate", startDate);
        object.put("StartTime", this.getStartTime());
        object.put("Duration", this.getDuration());
        object.put("EndDate", endDate);
        object.put("Frequency", frequency);

        return object;
    }
    public void setStartDate(long date)
    {
        startDate = date;
    }
    public void setEndDate(long date)
    {
        endDate = date;
    }
    public void setFrequency(long freq)
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

    @Override
    public String toString()
    {
        return "Name: " + this.getName()
                + ", Type: " + this.getType()
                + ", StartDate: " + this.getStartDate()
                + ", StartTime: " + this.getStartTime()
                + ", Duration: " + this.getDuration()
                + ", EndDate " + this.getEndDate()
                + ", Frequency: " + this.getFrequency();
    }
    @Override
    public boolean ifInThatDate(long startDay, long endDay)
    {
        long start = startDate;
        if(endDay - startDay <= 1)
        {
            while(endDay >= start)
            {
                if(startDate == endDay)
                {
                    return true;
                }
                start += frequency;
            }
            return false;
        }
        if((startDay <= startDate && startDate <= endDay)||(startDay <= endDate && endDate <= endDay))
            return true;
        else return false;
    }
}

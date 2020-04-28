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
}

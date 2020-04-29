import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class AntiTask extends Task
{
    private long date;
    public final String taskTypeString = "Anti";
    public AntiTask(String name, String type, long startTime, double duration, long date)
    {
        super(name, type, startTime, duration);
        this.date = date;
    }
    @Override
    public JSONObject toJSONObject()
    {
        JSONObject object = new JSONObject();
        object.put("Name", this.getName());
        object.put("Type", this.getType());
        object.put("StartTime", this.getStartTime());
        object.put("Duration", this.getDuration());
        object.put("Date", date);
        return object;
    }
    public void setDate(int date)
    {
        this.date = date;
    }
    public long getDate()
    {
        return date;
    }
    @Override
    public String toString()
    {
        return "Name: " + this.getName()
                + ", Type: " + this.getType()
                + ", StartTime: " + this.getStartTime()
                + ", Duration: " + this.getDuration()
                + ", Date " + this.getDate();
    }
    @Override
    public boolean ifInThatDate(long startDay, long endDay)
    {
        if(date >=startDay && date <= endDay)
            return true;
        return false;
    }
}

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TransientTask extends Task
{
    private long date;
    public final String taskTypeString = "Transient";
    public TransientTask(String name, String type, long startTime, double duration, long date)
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
    public void setDate(long date)
    {
        this.date = date;
    }
    public long getDate()
    {
        return date;
    }
}

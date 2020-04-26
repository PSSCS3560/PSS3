import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class AntiTask extends Task
{
    private long date;
    public final String task = "Anti";
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
}

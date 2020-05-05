import org.json.simple.JSONObject;

public class AntiTask extends Task {
    public final String taskTypeString = "Anti";

    public AntiTask(String name, String type, double startTime, double duration, long date) {
        super(name, type, startTime, duration, date);
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        object.put("Name", this.getName());
        object.put("Type", this.getType());
        object.put("StartTime", this.getStartTime());
        object.put("Duration", this.getDuration());
        object.put("Date", this.getStartDate());
        return object;
    }

    public void setDate(int date) {
        this.setStartDate(date);
    }

    public long getDate() {
        return this.getStartDate();
    }

    @Override
    public String toString() {
        return "Name: " + this.getName()
                + ", Type: " + this.getType()
                + ", StartTime: " + this.getStartTime()
                + ", Duration: " + this.getDuration()
                + ", Date " + this.getDate();
    }

    @Override
    public boolean ifInThatDate(long startDay, long endDay) {
        if (this.getStartDate() >= startDay && this.getStartDate() <= endDay)
            return true;
        return false;
    }
}

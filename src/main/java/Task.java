import org.json.simple.JSONObject;

public class Task {
    public static void main(String[] args) {
        System.out.println("hello");
    }
    private String uuid;
    private String name;
    private String type;
    private double startTime;
    private double duration;
    private long startDate;
    public String taskTypeString = "NA";

    public Task(String name, String type, double startTime, double duration, long startDate) {
        this.name = name;
        this.type = type;
        this.startTime = startTime;
        this.duration = duration;
        this.startDate = startDate;
    }

    public JSONObject toJSONObject() {
        return null;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStartTime(double time) {
        startTime = time;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getStartTime() {
        return startTime;
    }

    public double getDuration() {
        return duration;
    }

    public String getUuid() {
        return uuid;
    }

    public String toString() {
        return "Name: " + this.getName()
                + "\nType: " + this.getType()
                + "\nStartTime: " + this.getStartTime()
                + "\nDuration: " + this.getDuration();
    }


}

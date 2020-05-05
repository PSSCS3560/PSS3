
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RecurringTask extends Task {
    private long endDate;
    private long frequency;
    public final String taskTypeString = "Recurring";

    public RecurringTask(String name, String type, double startTime, double duration, long startDate, long endDate, long frequency) {
        super(name, type, startTime, duration, startDate);
        this.endDate = endDate;
        this.frequency = frequency;
    }

    public RecurringTask(String name, String type, double startTime, double duration, LocalDate startDate) {
        super(name, type, startTime, duration, Long.parseLong(startDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"))));
    }

    public List<RecurringTask> generateRecurringTask() {
        List<RecurringTask> list = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate tempStartDate = LocalDate.parse(String.valueOf(this.getStartDate()), formatter);
        LocalDate tempEndDate = LocalDate.parse(String.valueOf(this.endDate), formatter);
        switch ((int) this.frequency) {
            case 1:
                while (true) {
                    if (tempStartDate.isBefore(tempEndDate) || tempStartDate.isEqual(tempEndDate)) {
                        list.add(new RecurringTask(getName(), getType(), getStartTime(), getDuration(), tempStartDate));
                        tempStartDate = tempStartDate.plusDays(1);
                    } else
                        break;
                }

                break;
            case 7:
                while (true) {
                    if (tempStartDate.isBefore(tempEndDate) || tempStartDate.isEqual(tempEndDate)) {
                        list.add(new RecurringTask(getName(), getType(), getStartTime(), getDuration(), tempStartDate));
                        tempStartDate = tempStartDate.plusWeeks(1);
                    } else
                        break;
                }
                break;
            case 30:
                while (true) {
                    if (tempStartDate.isBefore(tempEndDate) || tempStartDate.isEqual(tempEndDate)) {
                        list.add(new RecurringTask(getName(), getType(), getStartTime(), getDuration(), tempStartDate));
                        tempStartDate = tempStartDate.plusMonths(1);
                    } else
                        break;
                }
                break;
            default:
                System.out.println("Frequency can be either 1 (daily), 7 (Weekly) or 30 (Monthly)");
                System.exit(0);
        }
        return list;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        object.put("Name", this.getName());
        object.put("Type", this.getType());
        object.put("StartDate", this.getStartDate());
        object.put("StartTime", this.getStartTime());
        object.put("Duration", this.getDuration());
//        object.put("EndDate", endDate);
//        object.put("Frequency", frequency);

        return object;
    }

    public void setEndDate(long date) {
        endDate = date;
    }

    public void setFrequency(long freq) {
        frequency = freq;
    }

    public long getEndDate() {
        return endDate;
    }

    public long getFrequency() {
        return frequency;
    }

    @Override
    public String toString() {
        return "Name: " + this.getName()
                + ", Type: " + this.getType()
                + ", StartDate: " + this.getStartDate()
                + ", StartTime: " + this.getStartTime()
                + ", Duration: " + this.getDuration();
//                + ", EndDate " + this.getEndDate()
//                + ", Frequency: " + this.getFrequency();
    }

    @Override
    public boolean ifInThatDate(long startDay, long endDay) {
        long start = this.getStartDate();
        if (endDay - startDay <= 1) {
            while (endDay >= start && start <= endDate) {
                if (start == endDay) {
                    return true;
                }
                start += frequency;
            }
            return false;
        }
        if ((startDay <= this.getStartDate() && this.getStartDate() <= endDay) || (startDay <= endDate && endDate <= endDay))
            return true;
        else return false;
    }
}

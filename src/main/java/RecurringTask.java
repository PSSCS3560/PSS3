
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class RecurringTask extends Task
{
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
        String day1 = "2020 05 01";
        String day2 = "2020 06 01";
        LocalDate date1 = LocalDate.parse(day1,formatter);
        LocalDate date2 = LocalDate.parse(day2,formatter);
        System.out.println("Days: " + Period.between(date1,date2).getMonths());
//        System.out.println(date1.toString().replace("-",""));
//        System.out.println(Long.parseLong(date1.format(formatter)));
//        int Weeks = (int) ChronoUnit.WEEKS.between(date1,date2);
//        System.out.println("month " +  Period.between(date1,date2).getMonths());

    }
    private long endDate;
    private long frequency;
    private long startTimeStamp;
    private long endTimeStamp;
    public final String taskTypeString = "Recurring";
    public RecurringTask(String name, String type, long startTime, double duration, long startDate, long endDate, long frequency) {
        super(name, type, startTime, duration,startDate);
        this.endDate = endDate;
        this.frequency = frequency;
    }
    public RecurringTask(String name, String type, long startTime, double duration, LocalDate startDate)
    {
        super(name,type,startTime,duration,Long.parseLong(startDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"))));
    }
    public List<RecurringTask> generateRecurringTask()
    {
        List<RecurringTask> list = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate tempStartDate = LocalDate.parse(String.valueOf(this.getStartDate()),formatter);
        LocalDate tempEndDate = LocalDate.parse(String.valueOf(this.endDate),formatter);
//        int Days = (int) ChronoUnit.DAYS.between(tempStartDate,tempEndDate) + 1; // +1 to add the current Date as well
//        int Weeks = (int) ChronoUnit.WEEKS.between(tempStartDate,tempEndDate);
//        int Months = (int) ChronoUnit.MONTHS.between(tempStartDate,tempEndDate);
        switch((int) this.frequency)
        {
            case 1:
               while(true) {
                   if (tempStartDate.isBefore(tempEndDate) || tempStartDate.isEqual(tempEndDate)) {
                       list.add(new RecurringTask(getName(), getType(), getStartTime(), getDuration(), tempStartDate));
                       tempStartDate = tempStartDate.plusDays(1);
                   }
                   else
                       break;
               }

                break;
            case 7:
                while(true)
                {
                    if(tempStartDate.isBefore(tempEndDate) || tempStartDate.isEqual(tempEndDate))
                    {
                        list.add(new RecurringTask(getName(),getType(),getStartTime(),getDuration(),tempStartDate));
                        tempStartDate = tempStartDate.plusWeeks(1);
                    }
                    else
                        break;
                }
                break;
            case 30:
                while(true) {
                    if(tempStartDate.isBefore(tempEndDate) || tempStartDate.isEqual(tempEndDate))
                    {
                        list.add(new RecurringTask(getName(),getType(),getStartTime(),getDuration(),tempStartDate));
                        tempStartDate = tempStartDate.plusMonths(1);
                    }
                    else
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
    public JSONObject toJSONObject()
    {
        JSONObject object = new JSONObject();
        object.put("Name", this.getName());
        object.put("Type", this.getType());
        object.put("StartDate", this.getStartDate());
        object.put("StartTime", this.getStartTime());
        object.put("Duration", this.getDuration());
        object.put("EndDate", endDate);
        object.put("Frequency", frequency);

        return object;
    }
    public void setEndDate(long date)
    {
        endDate = date;
    }
    public void setFrequency(long freq)
    {
        frequency = freq;
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
                + ", Duration: " + this.getDuration();
//                + ", EndDate " + this.getEndDate()
//                + ", Frequency: " + this.getFrequency();
    }
    @Override
    public boolean ifInThatDate(long startDay, long endDay)
    {
        long start = this.getStartDate();
        if(endDay - startDay <= 1)
        {
            while(endDay >= start && start <= endDate)
            {
                if(start == endDay)
                {
                    return true;
                }
                start += frequency;
            }
            return false;
        }
        if((startDay <= this.getStartDate() && this.getStartDate() <= endDay)||(startDay <= endDate && endDate <= endDay))
            return true;
        else return false;
    }
}

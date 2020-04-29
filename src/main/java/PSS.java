import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class PSS
{
    private List<Task> schedule;

    Scanner scan;
    String fileName;
    SimpleDateFormat formatter;
    Date date;
    long today;
    public PSS(String file)
    {
        fileName = file;
        schedule = new ArrayList<>();
        scan = new Scanner(System.in);
        formatter = new SimpleDateFormat("yyyyMMdd");
        date = new Date();
        today = Long.parseLong(formatter.format(date));
    }
    public boolean checkConflict()
    {
        return false;
    }
    public void viewToday()
    {
        List<Task> list = new ArrayList<>();
        for(int i = 0; i < schedule.size(); i++)
        {
            if(schedule.get(i).ifInThatDate(today, today))
            {
                list.add(schedule.get(i));
            }

        }
        for(int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i));
        }
    }
    public void viewWeek()
    {

        List<Task> list = new ArrayList<>();
        for(int i = 0; i < schedule.size(); i++)
        {
            if(schedule.get(i).ifInThatDate(today, today + 7))
            {
                list.add(schedule.get(i));
            }

        }
        for(int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i));
        }
    }
    public void viewMonth()
    {
        System.out.println(today);
        List<Task> list = new ArrayList<>();
        for(int i = 0; i < schedule.size(); i++)
        {
            if(schedule.get(i).ifInThatDate(today, today + 100))
            {
                list.add(schedule.get(i));
            }

        }
        for(int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i));
        }
    }
    public void viewSchedule()
    {
        for(int i = 0; i < schedule.size(); i++)
        {
            System.out.println(schedule.get(i));
        }
    }

    public void rewriteToJSONFile()
    {
        JSONArray array = new JSONArray();
        for(int i = 0; i < schedule.size(); i++)
        {
            array.add(schedule.get(i).toJSONObject());
        }
        try(FileWriter f = new FileWriter(fileName))
        {
            f.write(array.toJSONString());
            f.flush();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void add()
    {
        System.out.println("What is the name of the Task? ");
        String name = scan.nextLine();
        System.out.println("What type of task is it? ");
        String type = scan.nextLine();
        System.out.println("What is the startTime? ");
        long startTime = scan.nextInt();
        System.out.println("What is the duration? ");
        int duration = scan.nextInt();

        switch(type)
        {
            case "Class":
            case "Study":
            case "Sleep":
            case "Exercise":
            case "Work":
            case "Meal":
                System.out.println("What is the startDate?-yyyyMMdd");
                long startDate = scan.nextLong();
                System.out.println("What is the endDate?-yyyyMMdd");
                long endDate = scan.nextLong();
                System.out.println("What is the frequency?");
                long frequency = scan.nextLong();
                if(checkConflict())
                {
                    System.out.println("Schedule Conflict");
                    System.exit(0);
                }
                RecurringTask task = new RecurringTask(name, type, startTime,  duration,  startDate,  endDate,  frequency);

                schedule.add(task);
                JSONObject a = new JSONObject();
                a.put("Name", name);
                a.put("Type", type);
                a.put("StartDate", startDate);
                a.put("StartTime", startTime);
                a.put("Duration", duration);
                a.put("EndDate", endDate);
                a.put("Frequency", frequency);
                try(FileWriter file = new FileWriter(fileName))
                {
                    file.write(a.toJSONString());
                    file.flush();

                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
                break;
            case "Visit":
            case "Shopping":
            case "Appointment":
                System.out.println("What is the date?-yyyyMMdd");
                long date = scan.nextLong();
                if(checkConflict())
                {
                    System.out.println("Schedule Conflict");
                    System.exit(0);
                }
                TransientTask transTask = new TransientTask(name, type,startTime, duration, date);

                schedule.add(transTask);
                JSONObject t = new JSONObject();
                t.put("Name", name);
                t.put("Type", type);
                t.put("StartTime", startTime);
                t.put("Duration", duration);
                t.put("Date", date);
                try(FileWriter file = new FileWriter(fileName))
                {
                    file.write(t.toJSONString());
                    file.flush();

                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
                break;
            case "Cancellation":
                //TODO we gotta  consider how to cancel an instance of a Recurring Task, for now we should add it into the schedule
                System.out.println("What is the date?");
                long cancelDate = scan.nextLong();
                if(checkConflict())
                {
                    System.out.println("Schedule Conflict");
                    System.exit(0);
                }
                AntiTask antiTask = new AntiTask(name, type, startTime, duration, cancelDate);
                schedule.add(antiTask);
                JSONObject anti = new JSONObject();
                anti.put("Name", name);
                anti.put("Type", type);
                anti.put("StartTime", startTime);
                anti.put("Duration", duration);
                anti.put("Date", cancelDate);
                try(FileWriter file = new FileWriter(fileName)){
                    file.write(anti.toJSONString());
                    file.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Chose invalid type");


        }

        for(int i = 0; i < schedule.size(); i++)
            System.out.println(schedule.get(i));
        this.rewriteToJSONFile();
        JSONObject addedTask = new JSONObject();
        addedTask.put("Name", name);
        addedTask.put("Type", type);
        addedTask.put("StartTime", startTime);
        addedTask.put("Duration", duration);
    }
    public void delete()
    {
        System.out.println("What is the task's name that you want to delete?");
        String name = scan.nextLine();
        for(int i = 0; i < schedule.size(); i++)
        {
            if(schedule.get(i).getName().equals(name))
            {
                schedule.remove(i);
            }
        }
        this.rewriteToJSONFile();


    }
    public void edit()
    {
        System.out.println("What is the task's name that you want to edit?");
        String name = scan.nextLine();
        for(int i = 0; i < schedule.size(); i++)
        {
            if(schedule.get(i).getName().equals(name))
            {
                schedule.remove(i);
            }
        }
        this.rewriteToJSONFile();
        this.add();

    }

    public void scanFromJSONFile() {
        JSONParser jsonParser = new JSONParser();
        try(FileReader reader  = new FileReader(fileName)) {
            Object obj = jsonParser.parse(reader);

            JSONArray values = (JSONArray) obj;
            for(Object object: values) {
                JSONObject test = (JSONObject) object;
                String name = (String) test.get("Name");
                String type = (String) test.get("Type");
                long startDate;
                long startTime = (long) test.get("StartTime");
                double duration = (double) test.get("Duration");
                String uuid;
                switch(type) {
                    case "Class":
                    case "Study":
                    case "Sleep":
                    case "Exercise":
                    case "Work":
                    case "Meal":
                        startDate = (long) test.get("StartDate");
                        long endDate = (long) test.get("EndDate");
                        long frequency = (long) test.get("Frequency");
                        RecurringTask newRTask = new RecurringTask(name, type, startTime, duration, startDate, endDate, frequency);

                        schedule.add(newRTask);
                        break;
                    case "Visit":
                    case "Shopping":
                    case "Appointment":
                        startDate = (long) test.get("Date");
                        TransientTask newTTask = new TransientTask(name, type, startTime, duration, startDate);

                        schedule.add(newTTask);
                        break;
                    case "Cancellation":
                        startDate = (long) test.get("Date");
                        AntiTask newATask = new AntiTask(name, type, startTime, duration, startDate);
                        schedule.add(newATask);
                        break;
                }
            }

            for(Task task: schedule) {
                System.out.println(task.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    }


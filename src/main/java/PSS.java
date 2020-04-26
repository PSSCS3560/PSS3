import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class PSS
{
    private List<Task> schedule;
//    private HashMap<String, Task> database;
    Scanner scan;
    String fileName;
    public PSS(String file)
    {
        fileName = file;
        schedule = new ArrayList<>();
        scan = new Scanner(System.in);
//        database = new HashMap<>();
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
        String uuid;
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
                RecurringTask task = new RecurringTask(name,type, startTime,  duration,  startDate,  endDate,  frequency);
//                do {
//                    int rng = (int) (Math.random() * 1000);
//                    uuid = Integer.toString(rng);
//                } while(database.containsKey(uuid));
//                database.put(uuid, task);
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
                System.out.println("What is the date?");
                long date = scan.nextLong();
                TransientTask transTask = new TransientTask(name, type,startTime, duration, date);
//                do {
//                    int rng = (int) (Math.random() * 1000);
//                    uuid = Integer.toString(rng);
//                } while(database.containsKey(uuid));
//                database.put(uuid, transTask);
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
            default:
                System.out.println("Chose invalid type");


        }
//        System.out.println(database.toString());
        System.out.println(schedule.toString());
        this.rewriteToJSONFile();
        JSONObject addedTask = new JSONObject();
        addedTask.put("Name", name);
        addedTask.put("Type", type);
        addedTask.put("StartTime", startTime);
        addedTask.put("Duration", duration);

        System.out.println("adding");
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
//        System.out.println("Successfully deleted!");

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
//        System.out.println("What is the name of the task you want to delete?");
//        String name = scan.nextLine();
//        Task t;
//        for(int i = 0; i < schedule.size(); i++)
//        {
//            if(schedule.get(i).getName().equals(name))
//            {
//                t = schedule.get(i);
//            }
//        }
//        System.out.println("What part of the task do you want to edit?");
//        String which = scan.nextLine();
//        System.out.println("New Response:");
//        if(t.task == "Anti" || t.task == "Transient")
//        {
//            switch(which)
//            {
//                case "Name":
//                    String response = scan.nextLine();
//                    t.setName(response);
//                    break;
//                case "Type":
//                    String response1 = scan.nextLine();
//                    t.setType(response1);
//                    break;
//                case "StartTime":
//                    long response2 = scan.nextLong();
//                    t.setStartTime(response2);
//                    break;
//                case "Duration":
//                    long response3 = scan.nextLong();
//                    t.setStartTime(response3);
//                    break;
//                case "Date":
//                    long response4 = scan.nextLong();
//                    t.setDate(response4);
//                    break;
//                default:
//                    System.out.println("Invalid type:");
//            }
//        }
//        else if(t.task == "Recurring")
//        {
//            switch(which)
//            {
//                case "Name":
//                    String response = scan.nextLine();
//                    t.setName(response);
//                    break;
//                case "Type":
//                    String response1 = scan.nextLine();
//                    t.setType(response1);
//                    break;
//                case "StartTime":
//                    long response2 = scan.nextLong();
//                    t.setStartTime(response2);
//                case "StartDate":
//                    long response3 = scan.nextLong();
//                    t.setStartDate(response3);
//                case "Duration":
//                    long response5 = scan.nextLong();
//                    t.setDuration(response5);
//                case "Frequency":
//                    long response6 = scan.nextLong();
//                    t.setDuration(response6);
//                case "EndDate":
//                    long response4 = scan.nextLong();
//                    t.setEndDate(response4);
//                    break;
//            }
//        }
//        else
//        {
//            System.out.println("Invalid Task");
//        }
//        System.out.println("Successfully edited");
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
//                        do {
//                            int rng = (int) (Math.random() * 1000);
//                            uuid = Integer.toString(rng);
//                        } while(database.containsKey(uuid));
//                        database.put(uuid, newRTask);
                        schedule.add(newRTask);
                        break;
                    case "Visit":
                    case "Shopping":
                    case "Appointment":
                        startDate = (long) test.get("Date");
                        TransientTask newTTask = new TransientTask(name, type, startTime, duration, startDate);
//                        do {
//                            int rng = (int) (Math.random() * 1000);
//                            uuid = Integer.toString(rng);
//                        } while(database.containsKey(uuid));
//                        database.put(uuid, newTTask);
                        schedule.add(newTTask);
                        break;
                    case "Cancellation":
                        startDate = (long) test.get("Date");
                        AntiTask newATask = new AntiTask(name, type, startTime, duration, startDate);
//                        do {
//                            int rng = (int) (Math.random() * 1000);
//                            uuid = Integer.toString(rng);
//                        } while(database.containsKey(uuid));
//                        //TODO remove instance of recurring task
//                        database.put(uuid, newATask);
                        schedule.add(newATask);
                        break;
                }
            }
//            System.out.println(database);
            for(Task task: schedule) {
                System.out.println(task.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void main(String args[])
    {
//        System.out.println("Welcome to PSS");
//        PSS schedule = new PSS();
//        int choice;
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Enter Choice: (1 for add), (2 for edit), (3 for delete)");
//        choice = scan.nextInt();
//        while(choice != 4) // there are more choices didnt add yet
//        {
//
//            switch(choice)
//            {
//                case 1: //adding method
//                    schedule.add();
//                    break;
//                case 2: // edit method
////                    schedule.edit();
//                    break;
//                case 3: // delete
////                    schedule.delete();
//                    break;
//                default:
//                    System.out.println("Exiting. Goodbye");
//                    System.exit(0);
//            }
//            System.out.println("Enter Choice: (1 for add), (2 for edit), (3 for delete)");
//            choice = scan.nextInt();

        }
    }


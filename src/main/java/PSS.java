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
    private HashMap<String, Task> database;
    Scanner scan;
    public PSS()
    {
        schedule = new ArrayList<>();
        scan = new Scanner(System.in);
        database = new HashMap<>();
    }

    public void add()
    {
        String name = scan.next();
        String type = scan.next();
        int startTime = scan.nextInt();
        int duration = scan.nextInt();

        JSONObject addedTask = new JSONObject();
        addedTask.put("name", name);
        addedTask.put("type", type);
        addedTask.put("startTime", startTime);
        addedTask.put("duration", duration);

        System.out.println("adding");
    }
    public void delete(Task task)
    {
        System.out.println("adding");
    }
    public void edit(Task task)
    {
        System.out.println("adding");
    }

    public void scanFromJSONFile(String fileName) {
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
                    case "Sleep:":
                    case "Exercise":
                    case "Work":
                    case "Meal":
                        startDate = (long) test.get("StartDate");
                        long endDate = (long) test.get("EndDate");
                        long frequency = (long) test.get("Frequency");
                        RecurringTask newRTask = new RecurringTask(name, type, startTime, duration, startDate, endDate, frequency);
                        do {
                            int rng = (int) (Math.random() * 1000);
                            uuid = Integer.toString(rng);
                        } while(database.containsKey(uuid));
                        database.put(uuid, newRTask);
                        schedule.add(newRTask);
                        break;
                    case "Visit":
                    case "Shopping":
                    case "Appointment":
                        startDate = (long) test.get("Date");
                        TransientTask newTTask = new TransientTask(name, type, startTime, duration, startDate);
                        do {
                            int rng = (int) (Math.random() * 1000);
                            uuid = Integer.toString(rng);
                        } while(database.containsKey(uuid));
                        database.put(uuid, newTTask);
                        schedule.add(newTTask);
                        break;
                    case "Cancellation":
                        startDate = (long) test.get("Date");
                        AntiTask newATask = new AntiTask(name, type, startTime, duration, startDate);
                        do {
                            int rng = (int) (Math.random() * 1000);
                            uuid = Integer.toString(rng);
                        } while(database.containsKey(uuid));
                        //TODO remove instance of recurring task
                        database.put(uuid, newATask);
                        schedule.add(newATask);
                        break;
                }
            }
            System.out.println(database);
            for(Task task: schedule) {
                System.out.println(task.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void main(String args[])
    {
        System.out.println("Welcome to PSS");
        PSS schedule = new PSS();
        int choice;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Choice: (1 for add), (2 for edit), (3 for delete)");
        choice = scan.nextInt();
        while(choice != 4) // there are more choices didnt add yet
        {

            switch(choice)
            {
                case 1: //adding method
                    schedule.add();
                    break;
                case 2: // edit method
//                    schedule.edit();
                    break;
                case 3: // delete
//                    schedule.delete();
                    break;
                default:
                    System.out.println("Exiting. Goodbye");
                    System.exit(0);
            }
            System.out.println("Enter Choice: (1 for add), (2 for edit), (3 for delete)");
            choice = scan.nextInt();

        }
    }
}

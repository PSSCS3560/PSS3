import java.io.FileReader;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

public class PSS {

    private List<Task> schedule;

    Scanner scan;
    DateTimeFormatter formatter;
    Date date;
    LocalDate today;

    public PSS() {
        schedule = new ArrayList<>();
        scan = new Scanner(System.in);
        formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        date = new Date();
        today = LocalDate.now();
    }

    public boolean checkConflict(Task other) {
        double otherStartTime = other.getStartTime();
        double otherEndTime = other.getStartTime() + other.getDuration();
        for (Task task : schedule) {
            double startTimeTask = task.getStartTime();
            double endTimeTask = task.getDuration() + startTimeTask;


            if ((other.getStartTime() >= startTimeTask && other.getStartTime() <= endTimeTask && other.getStartDate() == task.getStartDate()) || (startTimeTask >= otherStartTime && startTimeTask <= otherEndTime && other.getStartDate() == task.getStartDate()))
                return true;
        }
        return false;
    }

    public List<Task> viewToday() {

        List<Task> list = new ArrayList<>();
        long today = Long.parseLong(LocalDate.now().format(formatter));
        for (int i = 0; i < schedule.size(); i++) {
            if (schedule.get(i).getStartDate() == today) {
                list.add(schedule.get(i));
            }
        }
        Collections.sort(list, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                if (o1.getStartDate() < o2.getStartDate())
                    return -1;
                else if (o1.getStartDate() == o2.getStartDate()) {
                    if (o1.getStartTime() < o2.getStartDate())
                        return -1;
                }
                return 1;
            }
        });
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        if (list.isEmpty())
            System.out.println("You have no schedule for Today!");
        return list;

    }

    public List<Task> viewWeek() {
//        LocalDate today = LocalDate.of(2020,5,12);

        long monday = Long.parseLong(today.with(previousOrSame(MONDAY)).format(formatter));
        long sunday = Long.parseLong(today.with(nextOrSame(SUNDAY)).format(formatter));


        List<Task> list = new ArrayList<>();
        for (int i = 0; i < schedule.size(); i++) {
            if(schedule.get(i).getStartDate() >= monday && schedule.get(i).getStartDate() <= sunday)
            {
                list.add(schedule.get(i));
            }
        }
        Collections.sort(list, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                if (o1.getStartDate() < o2.getStartDate())
                    return -1;
                else if (o1.getStartDate() == o2.getStartDate()) {
                    if (o1.getStartTime() < o2.getStartDate())
                        return -1;
                }
                return 1;
            }
        });

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        if (list.isEmpty())
            System.out.println("You have no schedule for this week!");
        return list;
    }

    public List<Task> viewMonth() {

        List<Task> list = new ArrayList<>();
        long firstDayOfMonth = Long.parseLong(today.withDayOfMonth(1).format(formatter));
        for (int i = 0; i < schedule.size(); i++) {
            if(schedule.get(i).getStartDate() >= firstDayOfMonth && schedule.get(i).getStartDate() <= firstDayOfMonth+today.lengthOfMonth() -1)
            {
                list.add(schedule.get(i));
            }
        }
        Collections.sort(list, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                if (o1.getStartDate() < o2.getStartDate())
                    return -1;
                else if (o1.getStartDate() == o2.getStartDate()) {
                    if (o1.getStartTime() < o2.getStartDate())
                        return -1;
                }
                return 1;
            }
        });
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        if (list.isEmpty())
            System.out.println("You have no schedule for this month!");
        return list;
    }

    public void viewSchedule() {
        for (int i = 0; i < schedule.size(); i++) {
            System.out.println(schedule.get(i));
        }
    }
    public void rewriteToJSONFile(String fileName,JSONArray array)
    {
        try (FileWriter f = new FileWriter(fileName)) {
            f.write(array.toJSONString());
            f.flush();
        } catch (IOException e) {
            System.out.println("File Not Found!");
        }
    }

    public void writeToday(String filename)
    {
        List<Task> tasks= viewToday();
        JSONArray array = new JSONArray();
        for(Task a : tasks)
        {
            array.add(a.toJSONObject());
        }
        rewriteToJSONFile(filename, array);
    }
    public void writeWeek(String filename)
    {
        List<Task> tasks= viewWeek();
        JSONArray array = new JSONArray();
        for(Task a : tasks)
        {
            array.add(a.toJSONObject());
        }
        rewriteToJSONFile(filename, array);
    }
    public void writeMonth(String filename)
    {
        List<Task> tasks= viewMonth();
        JSONArray array = new JSONArray();
        for(Task a : tasks)
        {
            array.add(a.toJSONObject());
        }
        rewriteToJSONFile(filename, array);
    }

    //TODO Need to make sure that Recurring Instances have the same format as Transient Task
    public void rewriteToJSONFile(String fileName) {
        JSONArray array = new JSONArray();
        for (int i = 0; i < schedule.size(); i++) {
            array.add(schedule.get(i).toJSONObject());
        }
        try (FileWriter f = new FileWriter(fileName)) {
            f.write(array.toJSONString());
            f.flush();
        } catch (IOException e) {
            System.out.println("File Not Found!");
        }
    }

    public void add() {
        System.out.println("What is the name of the Task? ");
        String name = scan.nextLine();
        System.out.println("What type of task is it? ");
        String type = scan.nextLine();
        System.out.println("What is the startTime? ");
        double startTime = Double.parseDouble(scan.nextLine());
        System.out.println("What is the duration? ");
        double duration = Double.parseDouble(scan.nextLine());

        switch (type) {
            case "Class":
            case "Study":
            case "Sleep":
            case "Exercise":
            case "Work":
            case "Meal":
                System.out.println("What is the startDate?-yyyyMMdd");
                long startDate = Long.parseLong(scan.nextLine());
                System.out.println("What is the endDate?-yyyyMMdd");
                long endDate = Long.parseLong(scan.nextLine());
                System.out.println("What is the frequency?");
                long frequency = Long.parseLong(scan.nextLine());
                RecurringTask task = new RecurringTask(name, type, startTime, duration, startDate, endDate, frequency);
                if (checkConflict(task)) {
                    System.out.println("You have a schedule conflict!");
                    break;
                }
                schedule.add(task);
                break;
            case "Visit":
            case "Shopping":
            case "Appointment":
                System.out.println("What is the date?-yyyyMMdd");
                long date = Long.parseLong(scan.nextLine());
                TransientTask transTask = new TransientTask(name, type, startTime, duration, date);
                if (checkConflict(transTask)) {
                    System.out.println("You have a schedule conflict! ");
                    break;
                }
                schedule.add(transTask);
                break;
            case "Cancellation":
                //TODO we gotta  consider how to cancel an instance of a Recurring Task, for now we should add it into the schedule
                System.out.println("What is the date?");
                long cancelDate = Long.parseLong(scan.nextLine());
                boolean existConflict = false;
                for (Task eachTask : schedule) {
                    if (eachTask.getStartDate() == cancelDate && eachTask.getDuration() == duration && eachTask.getStartTime() == startTime) {
                        existConflict = true;
                        schedule.remove(schedule.indexOf(eachTask));
                        break;
                    }
                }
                if (!existConflict) {
                    System.out.println("There is no Recurring Task that take place at your given input!");
                    break;
                }
                break;
            default:
                System.out.println("Please try again! You have entered Invalid Task Type!");


        }
        //sort the schedule
        Collections.sort(schedule, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                if (o1.getStartDate() < o2.getStartDate())
                    return -1;
                else if (o1.getStartDate() == o2.getStartDate()) {
                    if (o1.getStartTime() < o2.getStartDate())
                        return -1;
                }
                return 1;
            }
        });

        for (int i = 0; i < schedule.size(); i++)
            System.out.println(schedule.get(i));
    }

    public void delete() {
        System.out.println("What is the task's name that you want to delete?");
        String name = scan.nextLine();
        for (int i = 0; i < schedule.size(); i++) {
            if (schedule.get(i).getName().equals(name)) {
                schedule.remove(i);
            }
        }


    }

    public void edit() {
        System.out.println("What is the task's name that you want to edit?");
        String name = scan.nextLine();
        for (int i = 0; i < schedule.size(); i++) {
            if (schedule.get(i).getName().equals(name)) {
                schedule.remove(i);
            }
        }
        this.add();

    }

    public void scanFromJSONFile(String fileName) {
        List<Task> tempSchedule = new ArrayList<>(schedule);
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(fileName)) {
            Object obj = jsonParser.parse(reader);

            JSONArray values = (JSONArray) obj;
            boolean isConflict = false;
            for (Object object : values) {
//                System.out.println("here");
                JSONObject test = (JSONObject) object;
                String name = (String) test.get("Name");
                String type = (String) test.get("Type");
                long startDate;
                double startTime = Double.parseDouble(test.get("StartTime").toString());
                double duration = Double.parseDouble(test.get("Duration").toString());
                ;
                switch (type) {
                    case "Class":
                    case "Study":
                    case "Sleep":
                    case "Exercise":
                    case "Work":
                    case "Meal":
                        startDate = (long) test.get("StartDate");
                        long endDate = (long) test.get("EndDate");
                        long frequency = (long) test.get("Frequency");
                        List<RecurringTask> newRTask = new RecurringTask(name, type, startTime, duration, startDate, endDate, frequency).generateRecurringTask();
                        for (RecurringTask task : newRTask) {
                            if (checkConflict(task)) {
                                isConflict = true;
                                System.out.println("Conflict Occurred! Reading file failed! ");
                                break;
                            }
                            tempSchedule.add(task);
                        }

                        break;
                    case "Visit":
                    case "Shopping":
                    case "Appointment":
                        startDate = (long) test.get("Date");
                        TransientTask newTTask = new TransientTask(name, type, startTime, duration, startDate);
                        if (checkConflict(newTTask)) {
                            isConflict = true;
                            System.out.println("Conflict Occurred! Reading file failed! ");
                            break;
                        }
                        tempSchedule.add(newTTask);
                        break;
                    case "Cancellation":
                        startDate = (long) test.get("Date");
                        //Iterate through all the schedule to see if there is a schedule conflict
                        for (Task a : tempSchedule) {
                            //check if the date , duration and startTime are the same
                            if (a.getStartDate() == startDate && a.getDuration() == duration && a.getStartTime() == startTime) {
                                tempSchedule.remove(tempSchedule.indexOf(a));
                                break;
                            }
                        }
                        break;
                    default:
                        isConflict = true;
                        break;
                }
            }

            if (!isConflict) {
                Collections.sort(tempSchedule, new Comparator<Task>() {
                    @Override
                    public int compare(Task o1, Task o2) {
                        if (o1.getStartDate() < o2.getStartDate())
                            return -1;
                        else if (o1.getStartDate() == o2.getStartDate()) {
                            if (o1.getStartTime() < o2.getStartDate())
                                return -1;
                        }
                        return 1;
                    }
                });
                schedule = new ArrayList<>(tempSchedule);
            } else {
                System.out.println("Please adjust your Schedule JSON and Read the file again!");
            }


        } catch (Exception e) {
            System.out.println("File Not Found!\n");
            e.printStackTrace();
        }

    }

}


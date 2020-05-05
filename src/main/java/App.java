import org.json.JSONArray;
import org.json.JSONObject;
import java.util.*;
/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println("Welcome to PSS");
        PSS schedule = new PSS("Set1.json");
        schedule.scanFromJSONFile();
        schedule.viewSchedule();
        int choice;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Choice:\n1: Add\n2: Edit\n3: Delete\n4: View Today\n5: View Week\n6: View Month\n7: View All");
        choice = scan.nextInt();
        while(true) // there are more choices didnt add yet
        {

            switch (choice) {
                case 1: //adding method
                    scan.nextLine();
                    schedule.add();
                    break;
                case 2: // edit method
                    schedule.edit();
                    break;
                case 3: // delete
                    schedule.delete();
                    break;
                case 4:
                    schedule.viewToday();
                    break;
                case 5:
                    schedule.viewWeek();
                    break;
                case 6:
                    schedule.viewMonth();
                    break;
                case 7: schedule.viewSchedule();
                default:
                    System.out.println("Exiting. Goodbye");
                    System.exit(0);
            }
            System.out.println("Enter Choice:\n1: Add\n2: Edit\n3: Delete\n4: View Today\n5: View Week\n6: View Month\n7: View All");
            choice = scan.nextInt();
        }
    }
}

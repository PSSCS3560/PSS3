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
        int choice;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Choice: (1 for add), (2 for edit), (3 for delete)");
        choice = scan.nextInt();
        while(choice != 4) // there are more choices didnt add yet
        {

            switch (choice) {
                case 1: //adding method
                    schedule.add();
                    break;
                case 2: // edit method
                    schedule.edit();
                    break;
                case 3: // delete
                    schedule.delete();
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

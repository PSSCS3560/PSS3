
import java.util.*;

//TODO Left over task: WriteToday, WriteWeek, WriteMonth
//TODO we also need to fix rewriteToJSONFile: make sure to add recurring instance like transient task instead of recurring class

public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to PSS");
        PSS schedule = new PSS();
        Scanner scan = new Scanner(System.in);
        int choice;
        while (true) {

            System.out.println("1: Add\n2: Edit\n3: Delete\n4: View Today\n5: View Week\n6: View Month\n7: View All\n" +
                    "8: Read Schedules from file\n9: Write Schedules to File\n10: Exit\nEnter Choice:");
            choice = Integer.parseInt(scan.nextLine());
            System.out.println();
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
                case 4:
                    schedule.viewToday();
                    break;
                case 5:
                    schedule.viewWeek();
                    break;
                case 6:
                    schedule.viewMonth();
                    break;
                case 7:
                    schedule.viewSchedule();
                    break;
                case 8:
                    System.out.println("Input the file name you want to read from(e.g. Set1.json): ");
                    schedule.scanFromJSONFile(scan.nextLine().trim());
                    break;
                case 9:
                    System.out.println("Input the file name you want to write to (e.g. Set1.json): ");
                    schedule.rewriteToJSONFile(scan.nextLine().trim());
                    break;
                default:
                    System.out.println("Exiting. Goodbye");
                    System.exit(0);
                    break;
            }

        }
    }
}


import java.util.*;

public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to PSS");
        PSS schedule = new PSS();
        Scanner scan = new Scanner(System.in);
        int choice;
        while (true) {

            System.out.println("1: Add\n2: Edit\n3: Delete\n4: View Schedule\n" +
                    "5: Read Schedules from file\n6: Write All Schedules to File\n7: Find\n8: Exit\nEnter Choice:");
            try {
                choice = Integer.parseInt(scan.nextLine());
            }
            catch (Exception e )
            {
                System.out.println("Invalid input!");
                System.out.println();
                continue;
            }
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
                    System.out.println("Please enter 1 for Today, 2 for Week, 3 for Month or 4 for All Schedule:");
                    int timePeroid = Integer.parseInt(scan.nextLine());
                    switch (timePeroid)
                    {
                        case 1:
                            schedule.viewToday();
                            System.out.println("Do you want to write to file? [Y/N] ");
                            if(scan.nextLine().toUpperCase().equals("Y")) {
                                System.out.println("Input the valid file name:");
                                schedule.writeToday(scan.nextLine());
                            }
                            break;
                        case 2:
                            schedule.viewWeek();
                            System.out.println("Do you want to write to file? [Y/N] ");
                            if(scan.nextLine().toUpperCase().equals("Y")) {
                                System.out.println("Input the valid file name:");
                                schedule.writeWeek(scan.nextLine());
                            }
                            break;
                        case 3:
                            schedule.viewMonth();
                            System.out.println("Do you want to write to file? [Y/N] ");
                            if(scan.nextLine().toUpperCase().equals("Y")) {
                                System.out.println("Input the valid file name:");
                                schedule.writeMonth(scan.nextLine());
                            }
                            break;
                        case 4:
                            schedule.viewSchedule();
                            break;
                        default:
                            System.out.println("Invalid Input! Please try again!");
                            break;
                    }

                    break;
                case 5:
                    System.out.println("Input the file name you want to read from(e.g. Set1.json): ");
                    schedule.scanFromJSONFile(scan.nextLine().trim());
                    break;
                case 6:
                    System.out.println("Input the file name you want to write to (e.g. Set1.json): ");
                    schedule.rewriteToJSONFile(scan.nextLine().trim());
                    break;
                case 7:
                    schedule.find();
                    break;
                default:
                    System.out.println("Exiting. Goodbye");
                    System.exit(0);
                    break;
            }

        }
    }
}

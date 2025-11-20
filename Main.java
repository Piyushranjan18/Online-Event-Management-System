import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();
        EventService eventService = new EventService();

        System.out.println("=== Online Event Management System ===");

        while(true){
            System.out.println("\n1. Register User\n2. Create Event\n3. Approve Event\n4. Exit");
            int ch = sc.nextInt();
            sc.nextLine();

            switch(ch){
                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Password: ");
                    String pass = sc.nextLine();
                    System.out.print("Role (admin/organizer/attendee): ");
                    String role = sc.nextLine();
                    userService.registerUser(name, email, pass, role);
                    break;

                case 2:
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    System.out.print("Date (YYYY-MM-DD): ");
                    String dt = sc.nextLine();
                    System.out.print("Organizer ID: ");
                    int oid = sc.nextInt();
                    sc.nextLine();
                    eventService.createEvent(title, desc, dt, oid);
                    break;

                case 3:
                    System.out.print("Event ID: ");
                    int eid = sc.nextInt();
                    eventService.approveEvent(eid);
                    break;

                case 4:
                    System.out.println("Goodbye!");
                    return;
            }
        }
    }
}

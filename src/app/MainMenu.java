package app;
import java.util.HashMap;
import java.util.Map;
import models.user.User;
import util.Console;

public class MainMenu {
    
    private User user;
    private final Map<Integer, Runnable> menuActions = new HashMap<>();

    public MainMenu(User user) {
        this.user = user;
        initializeMenuActions();       
    }

    private void initializeMenuActions() {
        menuActions.put(1, () -> new ChatMenu(user).show());
        menuActions.put(2, () -> new NewChatMenu(user).show());
        menuActions.put(3, () -> new InstagramMenu(user).show());
        menuActions.put(4, () -> new ProfileMenu(user).show());
    }

    public void show() {
        while (true) {
            Console.clearScreen();
            System.out.println("\n Main Menu " + " | " + user.getNickname());
            Console.printSeparator();
            System.out.println("1. View Chats");
            System.out.println("2. Create New Chat");
            System.out.println("3. Instagram");
            System.out.println("4. My Profile");
            System.out.println("0. Logout");
            Console.printSeparator();
            System.out.print("Choose an option: ");
            int choice = Console.NextInt(MainApp.scanner);

            if (choice == 0) return;

            Runnable action = menuActions.get(choice);
            if (action != null) {
                action.run();
            } else {
                System.out.println("Invalid option. Try again.");
                Console.sleep(1000);
            }
        }
}
}
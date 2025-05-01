package app;
import models.user.User;
import util.Console;

public class MainMenu {
    
    private User user;

    public MainMenu(User user) {
        this.user = user;
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
            System.out.println("5. Logout");
            Console.printSeparator();
            System.out.print("Choose an option: ");
            int choice = Console.NextInt(MainApp.scanner);

            switch (choice) {
                case 1:
                    ChatMenu chatmenu = new ChatMenu(user);
                    chatmenu.show();
                    break;

                case 2:
                    NewChatMenu newChatMenu = new NewChatMenu(user);
                    newChatMenu.show();
                    break;
                case 3:
                    InstagramMenu InstagramMenu = new InstagramMenu(user);
                    InstagramMenu.show();
                    break;
                case 4:
                    ProfileMenu profileMenu = new ProfileMenu(user);
                    profileMenu.show();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
                    Console.sleep(1000);
            }
        }
}
}
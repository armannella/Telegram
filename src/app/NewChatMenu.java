package app;

import java.util.HashMap;
import java.util.Map;
import models.chat.*;
import models.exception.NoPermissionException;
import models.exception.UserNotFoundException;
import models.user.*;
import util.Console;


public class NewChatMenu {
    private User user;
    private final Map<Integer, Runnable> menuActions = new HashMap<>();

    public NewChatMenu(User user) {
        this.user = user;
        initializeMenuActions();
    }

    private void initializeMenuActions() {
        menuActions.put(1, this::createPrivateChat);
        menuActions.put(2, this::createGroupChat);
        menuActions.put(3, this::createChannel);
    }

    public void show() {
        while (true) {
            Console.clearScreen();
            System.out.println("Create New Chat:");
            Console.printSeparator();
            System.out.println("1. Create Private Chat (PV)");
            System.out.println("2. Create Group");
            System.out.println("3. Create Channel");
            System.out.println("0. Back");
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

    private void createPrivateChat() {
        Console.clearScreen();
        System.out.print("Enter username of the other user: ");
        String username = MainApp.scanner.nextLine();
        User other;
        try {
            other = MainApp.userManager.findUserByUsername(username);
            PV pv = MainApp.chatManager.createPrivateChat(user, other);
            System.out.println("Private Chat created successfully.");
            Console.sleep(1000);
            new ChatSessionMenu(user, pv).show();
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            Console.sleep(1000);
        }
    }

    private void createGroupChat() {
        Console.clearScreen();
        System.out.print("Enter Group Title : ");
        String title = MainApp.scanner.nextLine();
        Group group;
        try {
            group = MainApp.chatManager.createGroupChat(user, title);
            System.out.println("Group created successfully.");
            Console.sleep(1000);
            new ChatSessionMenu(user, group).show();

        } catch (NoPermissionException e) {
            System.out.println(e.getMessage());
            Console.sleep(1000);
        }
    }

    private void createChannel() {
        Console.clearScreen();
        System.out.print("Enter Channel Title : ");
        String title = MainApp.scanner.nextLine();
        try {
        Channel channel = MainApp.chatManager.createChannel(user, title);
        System.out.println("Channel created successfully.");
        Console.sleep(1000);
        new ChatSessionMenu(user, channel).show();

        } catch (NoPermissionException e) {
            System.out.println(e.getMessage());
            Console.sleep(1000);
        }
    }
}


package app;

import models.chat.*;
import models.exception.NoPermissionException;
import models.exception.UserNotFoundException;
import models.user.*;
import util.Console;


public class NewChatMenu {
    private User user;

    public NewChatMenu(User user) {
        this.user = user;
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

            switch (choice) {
                case 1:
                    createPrivateChat();
                    break;

                case 2:
                    createGroupChat();
                    break;

                case 3:
                    createChannel();
                    break;

                case 0:
                    return;

                default:
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


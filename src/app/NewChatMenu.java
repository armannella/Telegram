package app;

import models.chat.*;
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
            int choice = MainApp.scanner.nextInt();
            MainApp.scanner.nextLine();
            UserPlan userPlan = user.getUserplan();

            switch (choice) {
                case 1:
                    createPrivateChat();
                    break;

                case 2:
                    if(userPlan.canCreateGroup())
                    {
                        createGroupChat();
                    }
                    else
                    {
                        System.out.println("you Should Upgrade your userPlan to create Group");
                        Console.sleep(1000);
                    }
                    break;

                case 3:
                    if(userPlan.canCreateChannel())
                    {
                        createChannel();
                    }
                    else
                    {
                        System.out.println("you Should Upgrade your userPlan to create Channel");
                        Console.sleep(1000);
                    }
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
        User other = MainApp.userManager.findUserByUsername(username);

        if (other == null) {
            System.out.println("User not found.");
            Console.sleep(1000);
            return;
        }

        PV pv = MainApp.chatManager.createPrivateChat(user, other);
        System.out.println("Private Chat created successfully.");
        Console.sleep(1000);
        new ChatSessionMenu(user, pv).show();
    }

    private void createGroupChat() {
        Console.clearScreen();
        System.out.print("Enter Group Title : ");
        String title = MainApp.scanner.nextLine();
        Group group = MainApp.chatManager.createGroupChat(user, title);

        System.out.println("Group created successfully.");
        Console.sleep(1000);
        new ChatSessionMenu(user, group).show();
        
    }

    private void createChannel() {
        Console.clearScreen();
        System.out.print("Enter Channel Title : ");
        String title = MainApp.scanner.nextLine();
        Channel channel = MainApp.chatManager.createChannel(user, title);

        System.out.println("Channel created successfully.");
        Console.sleep(1000);
        new ChatSessionMenu(user, channel).show();
    }
}


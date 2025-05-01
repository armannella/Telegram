package app;


import models.chat.Chat;
import models.chat.ChatMember;
import models.exception.NoPermissionException;
import models.exception.UserAlreadyInChatException;
import models.exception.UserNotFoundException;
import models.exception.UserNotInChat;
import models.message.ActionType;
import models.message.InfoMessage;
import models.user.User;
import util.Console;

public class ChatDetails {

    private Chat chat;
    private User currentUser;

    public ChatDetails(Chat chat, User currentUser) {
        this.chat = chat;
        this.currentUser = currentUser;
    }

    public void show() {
        while (true) {
            Console.clearScreen();
            System.out.println(chat.getID() + ". " + chat.getType() + " Chat - " + chat.getTitle());
            Console.printSeparator();
            System.out.println("Members Count : " + chat.countMembers() + " Users");
            System.out.println("Messages Count : " + chat.countMessages() + " Messages");
            Console.printSeparator();
            System.out.println("Members : ");
            for (ChatMember member : chat.getMembers())
            {
                member.showMembers();
            }

            Console.printSeparator();
            System.out.println("Options:");
            System.out.println("1. Add Member");
            System.out.println("2. Delete Member");
            System.out.println("3. Add Admin");
            System.out.println("4. Delete Admin");
            System.out.println("5. Change Title");
            System.out.println("0. Back");
            Console.printSeparator();
            System.out.print("Choose an option: ");
            int choice = Console.NextInt(MainApp.scanner);

            switch (choice) {
                case 1:
                    addMember();
                    break;

                case 2:
                    removeMember();
                    break;

                case 3:
                    promoteAdmin();
                    break;

                case 4:
                    demoteAdmin();
                    break;

                case 5:
                    changeChatTitle();
                    break ;

                case 0:
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
                    Console.sleep(1000);
            }
        }
    }


    private void addMember() {
        Console.clearScreen();
        System.out.print("Enter username to add: ");
        String username = MainApp.scanner.nextLine();
        User userToAdd;
        try {
            userToAdd = MainApp.userManager.findUserByUsername(username);
            MainApp.chatManager.addMember(chat, currentUser, userToAdd);
            System.out.println("User added successfully.");
            chat.addMessage(new InfoMessage(currentUser, ActionType.ADDED,userToAdd));

        } catch (UserNotFoundException | UserAlreadyInChatException | NoPermissionException e) {
            System.out.println(e.getMessage());
        }
        Console.sleep(1000);
    }

    private void removeMember() {
        Console.clearScreen();
        System.out.print("Enter username to remove: ");
        String username = MainApp.scanner.nextLine();
        User userToRemove;
        try {
            userToRemove  = MainApp.userManager.findUserByUsername(username);;
            MainApp.chatManager.removeMember(chat, currentUser ,userToRemove);
            System.out.println("User removed successfully.");
            chat.addMessage(new InfoMessage(currentUser, ActionType.REMOVED,userToRemove));

        } catch (UserNotFoundException | UserNotInChat | NoPermissionException e) {
            System.out.println(e.getMessage());
        }

        Console.sleep(1000);
    }

    private void promoteAdmin() {
        Console.clearScreen();
        System.out.print("Enter username to promote: ");
        String username = MainApp.scanner.nextLine();
        User userToPromote;
        try {
            userToPromote = MainApp.userManager.findUserByUsername(username);
            MainApp.chatManager.promoteToAdmin(chat, currentUser, userToPromote);
            System.out.println("User promoted to Admin.");
        } catch (UserNotFoundException | UserNotInChat | NoPermissionException e) {
            System.out.println(e.getMessage());   
        }
        Console.sleep(1000);
    }

    private void demoteAdmin() {
        Console.clearScreen();
        System.out.print("Enter username to demote: ");
        String username = MainApp.scanner.nextLine();
        User userToDemote;
        try {
            userToDemote = MainApp.userManager.findUserByUsername(username);
            MainApp.chatManager.demoteFromAdmin(chat, currentUser, userToDemote);
            System.out.println("User demoted from Admin.");
        } catch (UserNotFoundException | UserNotInChat | NoPermissionException e) {
        System.out.println(e.getMessage());
        }
        Console.sleep(1000);
    }

    private void changeChatTitle() {
        Console.clearScreen();
        System.out.print("Enter new chat title: ");
        String newTitle = MainApp.scanner.nextLine();
        try {
            MainApp.chatManager.changeTitle(chat, currentUser, newTitle);
            System.out.println("Chat title changed successfully.");
            chat.addMessage(new InfoMessage(currentUser, ActionType.RENAMED));
        } catch (NoPermissionException e) {
            System.out.println(e.getMessage());
        }
        
        Console.sleep(1000);
    }
}

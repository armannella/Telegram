package app;


import java.nio.channels.Channel;
import models.chat.Chat;
import models.chat.ChatMember;
import models.chat.NormalUser;
import models.chat.Role;
import models.chat.Viewer;
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
            int choice = MainApp.scanner.nextInt();
            MainApp.scanner.nextLine();

            Role role = chat.getRoleofUser(currentUser);

            switch (choice) {
                case 1:
                    if (role.canAddMember()) {
                        addMember();
                    } else {
                        System.out.println("You do not have permission to add members.");
                        Console.sleep(1000);
                    }
                    break;

                case 2:
                    if (role.canDeleteMember()) {
                        removeMember();
                    } else {
                        System.out.println("You do not have permission to remove members.");
                        Console.sleep(1000);
                    }
                    break;

                case 3:
                    if (role.canManageAdmins()) {
                        promoteAdmin();
                    } else {
                        System.out.println("You do not have permission to promote admins.");
                        Console.sleep(1000);
                    }
                    break;

                case 4:
                    if (role.canManageAdmins()) {
                        demoteAdmin();
                    } else {
                        System.out.println("You do not have permission to demote admins.");
                        Console.sleep(1000);
                    }
                    break;

                case 5:
                    if (role.canChangeTopic()) {
                        changeChatTitle();
                    } else {
                        System.out.println("You do not have permission to change the chat title.");
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


    private void addMember() {
        Console.clearScreen();
        System.out.print("Enter username to add: ");
        String username = MainApp.scanner.nextLine();
        User userToAdd = MainApp.userManager.findUserByUsername(username);

        Role defaultrole = (chat instanceof Channel) ? new Viewer() : new NormalUser();

        if (userToAdd != null) {
            if (!chat.isUserinChat(userToAdd)) {
                if(chat.addMember(new ChatMember(userToAdd, defaultrole)));
                {
                        System.out.println("User added successfully.");
                        chat.addMessage(new InfoMessage(currentUser, ActionType.ADDED,userToAdd));
                }
                Console.sleep(1000);
            } 
            else {
                System.out.println("user is already a member.");
                Console.sleep(1000);
            }
        } else {
            System.out.println("User not found.");
            Console.sleep(1000);
        }
    }

    private void removeMember() {
        Console.clearScreen();
        System.out.print("Enter username to remove: ");
        String username = MainApp.scanner.nextLine();
        User userToRemove = MainApp.userManager.findUserByUsername(username);

        if (userToRemove != null && chat.isUserinChat(userToRemove)) {
            if(chat.removeMember(userToRemove))
            {
                System.out.println("User removed successfully.");
                chat.addMessage(new InfoMessage(currentUser, ActionType.REMOVED,userToRemove));
            }
            Console.sleep(1000);

        } 
        else {
            System.out.println("User not found in chat.");
            Console.sleep(1000);
        }
    }


    private void promoteAdmin() {
        Console.clearScreen();
        System.out.print("Enter username to promote: ");
        String username = MainApp.scanner.nextLine();
        User userToPromote = MainApp.userManager.findUserByUsername(username);

        if (userToPromote != null && chat.isUserinChat(userToPromote)) {
            if(chat.promoteToAdmin(userToPromote))
            {
                System.out.println("User promoted to Admin.");
            }
            Console.sleep(1000);

        } 
        else {
            System.out.println("User not found in chat.");
            Console.sleep(1000);
        }
    }

    private void demoteAdmin() {
        Console.clearScreen();
        System.out.print("Enter username to demote: ");
        String username = MainApp.scanner.nextLine();
        User userToDemote = MainApp.userManager.findUserByUsername(username);
        Role defaultrole = (chat instanceof Channel) ? new Viewer() : new NormalUser();

        if (userToDemote != null && chat.isUserinChat(userToDemote)) 
        {
            if(chat.demoteFromAdmin(userToDemote,defaultrole))
            {
                System.out.println("User demoted from Admin.");
            }
            Console.sleep(1000);
        } 
        else {
            System.out.println("User not found in chat.");
            Console.sleep(1000);
        }
    }

    private void changeChatTitle() {
        Console.clearScreen();
        System.out.print("Enter new chat title: ");
        String newTitle = MainApp.scanner.nextLine();
        chat.setTitle(newTitle);
        System.out.println("Chat title changed successfully.");
        chat.addMessage(new InfoMessage(currentUser, ActionType.RENAMED));
        Console.sleep(1000);
    }
}

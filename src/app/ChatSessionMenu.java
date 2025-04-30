package app;

import models.chat.Chat;
import models.chat.Role;
import models.message.Message;
import models.message.MultimediaMessage;
import models.message.TextMessage;
import models.user.User;
import util.Console;

public class ChatSessionMenu {
    private User user;
    private Chat chat;

    public ChatSessionMenu(User user, Chat chat) {
        this.user = user;
        this.chat = chat;
    }

    public void show() {
        while (true) {
            Console.clearScreen();
            System.out.println(chat.getTitle());
            Console.printSeparator();
            MainApp.messageManager.displayChatMessages(chat);
            Console.printSeparator();
            System.out.println("\nOptions:");
            System.out.println("1. Send Text Message");
            System.out.println("2. Send Media Message");
            System.out.println("3. Edit Message");
            System.out.println("4. Delete Message");
            System.out.println("5. Chat Details");
            System.out.println("0. Back");
            Console.printSeparator();
            System.out.print("Choose an option: ");
            int choice = MainApp.scanner.nextInt();
            MainApp.scanner.nextLine();
            Role role = chat.getRoleofUser(user);

            switch (choice) {
                case 1:
                    if (role.canSendMessage())
                    {
                        System.out.print("Enter text message: ");
                        String textContent = MainApp.scanner.nextLine();
                        MainApp.messageManager.sendMessage(chat, new TextMessage(user, textContent));
                    }
                    else
                    {
                        System.out.println("You dont have permission to send Message");
                        Console.sleep(1000);
                    }
                    break;

                case 2:
                    if (role.canSendMessage())
                    {
                        System.out.print("Enter media file path: ");
                        String mediaPath = MainApp.scanner.nextLine();
                        MainApp.messageManager.sendMessage(chat, new MultimediaMessage(user, mediaPath));
                    }
                    else
                    {
                        System.out.println("You dont have permission to send Message");
                        Console.sleep(1000);
                    }                    
                    break;

                case 3:
                    System.out.print("Enter message ID : ");
                    int inputidforedit = MainApp.scanner.nextInt();
                    MainApp.scanner.nextLine();
                    Message toeditmessage = chat.findMessageById(inputidforedit);
                    if (toeditmessage == null)
                    {
                        System.out.println("Invalid message ID");
                    }
                    else if (toeditmessage instanceof TextMessage)
                    {
                        System.out.print("Enter new Text: ");
                        String newcontent = MainApp.scanner.nextLine();
                        if (MainApp.messageManager.editMessage(chat, user, toeditmessage, newcontent))
                        {
                            System.out.println("Edited Successful");
                            Console.sleep(1000);
                        }
                        else {
                            System.out.println("You dont have access to Edit messages");
                            Console.sleep(1000);
                        }
                    }
                    else {
                        System.out.println("only Text messages can edit");
                    }
                    break;

                case 4:
                    System.out.print("Enter message ID : ");
                    int inputidfordelete = MainApp.scanner.nextInt();
                    MainApp.scanner.nextLine();
                    Message todeletemessage = chat.findMessageById(inputidfordelete);
                    if (todeletemessage == null)
                    {
                        System.out.println("Invalid message ID");
                    }
                    else
                    {
                        if (MainApp.messageManager.deleteMessage(chat, user, todeletemessage))
                        {
                            System.out.println("Deleted Successful");
                            Console.sleep(1000);
                        }
                        else {
                            System.out.println("You dont have access to Delete messages");
                            Console.sleep(1000);
                        }
                    }
                    break;
                
                case 5 :
                    ChatDetails chatDetails = new ChatDetails(chat, user);
                    chatDetails.show();
                    break ;
                    
                case 0:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
                    Console.sleep(1000);
            }
        }
    }

}

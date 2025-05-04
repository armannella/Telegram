package app;

import models.chat.Chat;
import models.exception.IdNotFoundException;
import models.exception.NoPermissionException;
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
            System.out.println("Options:");
            System.out.println("1. Send Text Message");
            System.out.println("2. Send Media Message");
            System.out.println("3. Edit Message");
            System.out.println("4. Delete Message");
            System.out.println("5. Chat Details");
            System.out.println("0. Back");
            Console.printSeparator();
            System.out.print("Choose an option: ");
            int choice = Console.NextInt(MainApp.scanner);

            switch (choice) {
                case 1:
                    sendTextMessage();
                    break;

                case 2:
                    sendMediaMessage();                 
                    break;

                case 3:
                    editMessage();
                    break;

                case 4:
                    deleteMessage();
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

    private void sendTextMessage() {

        System.out.print("Enter text message: ");
        String textContent = MainApp.scanner.nextLine();
        try {
        MainApp.messageManager.sendMessage(chat, new TextMessage(user, textContent));
        } 
        catch (NoPermissionException e) {
            System.out.println(e.getMessage());
            Console.sleep(1000);
        }
    }

    private void sendMediaMessage() {

        System.out.print("Enter media file path: ");
        String mediaPath = MainApp.scanner.nextLine();
        try {
            MainApp.messageManager.sendMessage(chat, new MultimediaMessage(user, mediaPath));
        } 
        catch (NoPermissionException e) {
            System.out.println(e.getMessage());
            Console.sleep(1000);
        }
    }

    private void editMessage(){
        System.out.print("Enter message ID : ");
        int inputidforedit = Console.NextInt(MainApp.scanner);
        Message toeditmessage;
        try {
            toeditmessage = chat.findMessageById(inputidforedit);
            if (toeditmessage instanceof TextMessage){
                System.out.print("Enter new Text: ");
                String newcontent = MainApp.scanner.nextLine();
                MainApp.messageManager.editMessage(chat, user, toeditmessage, newcontent);
                System.out.println("Edited Successful");
            }
            else {
                System.out.println("only Text messages can edit");
            }
        } catch (IdNotFoundException | NoPermissionException e) {
            System.out.println(e.getMessage());     
        }
        Console.sleep(1000);
    }

    private void deleteMessage() {
        System.out.print("Enter message ID : ");
        int inputidfordelete = Console.NextInt(MainApp.scanner);
        Message todeletemessage;
        try {
            todeletemessage = chat.findMessageById(inputidfordelete);
            MainApp.messageManager.deleteMessage(chat, user, todeletemessage);
            System.out.println("Deleted Successful");

        } catch (IdNotFoundException | NoPermissionException e) {
            System.out.println(e.getMessage());     
        }
        Console.sleep(1000);
    }

}

package app;

import java.util.HashMap;
import java.util.Map;
import models.chat.Chat;
import models.exception.IdNotFoundException;
import models.exception.NoPermissionException;
import models.exception.NotEditableMessageException;
import models.message.Message;
import models.message.MultimediaMessage;
import models.message.TextMessage;
import models.user.User;
import util.Console;

public class ChatSessionMenu {
    private User user;
    private Chat chat;
    private final Map<Integer, Runnable> menuActions = new HashMap<>();

    public ChatSessionMenu(User user, Chat chat) {
        this.user = user;
        this.chat = chat;
        initializeMenuActions();
    }

    private void initializeMenuActions() {
        menuActions.put(1, this::sendTextMessage);
        menuActions.put(2, this::sendMediaMessage);
        menuActions.put(3, this::editMessage);
        menuActions.put(4, this::deleteMessage);
        menuActions.put(5, this::showChatDetails);
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

    private void sendTextMessage() {
        System.out.print("Enter text message: ");
        String textContent = MainApp.scanner.nextLine();
        try {
            MainApp.messageManager.sendMessage(chat, new TextMessage(user, textContent));
        } catch (NoPermissionException e) {
            System.out.println(e.getMessage());
            Console.sleep(1000);
        }
    }

    private void sendMediaMessage() {
        System.out.print("Enter media file path: ");
        String mediaPath = MainApp.scanner.nextLine();
        try {
            MainApp.messageManager.sendMessage(chat, new MultimediaMessage(user, mediaPath));
        } catch (NoPermissionException e) {
            System.out.println(e.getMessage());
            Console.sleep(1000);
        }
    }

    private void editMessage() {
        System.out.print("Enter message ID: ");
        int messageId = Console.NextInt(MainApp.scanner);
        try {
                Message message = chat.findMessageById(messageId);
                System.out.print("Enter new text: ");
                String newText = MainApp.scanner.nextLine();
                MainApp.messageManager.editMessage(chat, user, message, newText);
                System.out.println("Edit successful.");

        } catch (IdNotFoundException | NoPermissionException | NotEditableMessageException e) {
            System.out.println(e.getMessage());
        } 
        Console.sleep(1000);
    }
    
    private void deleteMessage() {
        System.out.print("Enter message ID: ");
        int messageId = Console.NextInt(MainApp.scanner);
        try {
            Message message = chat.findMessageById(messageId);
            MainApp.messageManager.deleteMessage(chat, user, message);
            System.out.println("Delete successful.");
        } catch (IdNotFoundException | NoPermissionException e) {
            System.out.println(e.getMessage());
        }
        Console.sleep(1000);
    }

    private void showChatDetails() {
        ChatDetails chatDetails = new ChatDetails(chat, user);
        chatDetails.show();
    }
}

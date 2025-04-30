package app;
import java.util.ArrayList;
import models.chat.Chat;
import models.user.User;
import util.Console;

public class ChatMenu {

    private User user ;
    
    public ChatMenu(User user)
    {
        this.user = user ;
        
    }

    public void show()
    {
        while (true) {

            ArrayList<Chat> userChats = MainApp.chatManager.getChatsOfUser(user);

            Console.clearScreen();
            System.out.println("\n Your Chats:");
            


            for (Chat chat : userChats) {
                System.out.println(chat.getID() + ". " + chat.getType() + " Chat - " + chat.getTitle());
            }

            Console.printSeparator();
            System.out.println("0. Back to Main Menu");
            Console.printSeparator();
            System.out.print("Select a chat by ID: ");

            int choice = MainApp.scanner.nextInt();
            MainApp.scanner.nextLine();

            if (choice == 0) {
                return; 
            }

            Chat selectedChat = MainApp.chatManager.getChatById(choice);
            if (selectedChat != null && selectedChat.isUserinChat(user)) {
                
                System.out.println("Entering chat...");
                Console.sleep(1000);
                ChatSessionMenu chatSessionMenu = new ChatSessionMenu(user, selectedChat);
                chatSessionMenu.show();
                
            } else {
                System.out.println("Invalid chat ID or you are not a member!");
                Console.sleep(1000);
            }
        }
    }
}


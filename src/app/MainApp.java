package app;

import java.util.Scanner;
import manager.*;

public class MainApp {

    public static UserManager userManager = new UserManager();
    public static ChatManager chatManager = new ChatManager();
    public static MessageManager messageManager = new MessageManager();
    public static PostManager postManager = new PostManager();
    public static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        util.inputs.add_some_members();
        WelcomeMenu welcomeMenu = new WelcomeMenu();
        welcomeMenu.show();
    }
}


package app;

import java.util.HashMap;
import java.util.Map;
import models.exception.InvalidCredentialsException;
import models.exception.UserAlreadyExistsException;
import models.exception.UserNotFoundException;
import models.user.User;
import util.Console;

public class WelcomeMenu {

    private final Map<Integer, Runnable> menuActions = new HashMap<>();

    public WelcomeMenu() {
        initializeMenuActions();
    }
    
    private void initializeMenuActions() {
        menuActions.put(1, this::register);
        menuActions.put(2, this::login);
        menuActions.put(3, this::exitApp);
    }

    public void show() {  
        
        while (true) {

            Console.clearScreen();
            Console.printSeparator();
            System.out.println("Welcome to Telegram Messenger");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            Console.printSeparator();
            System.out.print("Choose an option: ");
            int choice = Console.NextInt(MainApp.scanner);

            Runnable action = menuActions.get(choice);
            if (action != null) {
                action.run();
            } else {
                System.out.println("Invalid option. Please try again.");
                Console.sleep(1000);
            }
        }
    }

    private void exitApp() {
        System.out.println("GoodBye.");
        Console.sleep(1000);
        System.exit(0);
    }

    private void register(){

        Console.clearScreen();
        System.out.println("Register New User");
        Console.printSeparator();
        System.out.print("Username: ");
        String username = MainApp.scanner.nextLine();
        System.out.print("Password: ");
        String password = MainApp.scanner.nextLine();
        System.out.print("Nickname: ");
        String nickname = MainApp.scanner.nextLine();
        System.out.print("Phone number: ");
        String phone = MainApp.scanner.nextLine();

        try {
        MainApp.userManager.register(username, password, nickname, phone);
        System.out.println("Register Successful");
        
        }
        catch (UserAlreadyExistsException e){
            System.out.println(e.getMessage());
        } 
        Console.sleep(1000);

    }

    private User performLogin() throws UserNotFoundException, InvalidCredentialsException 
    {
        Console.clearScreen();
        System.out.println("Login Menu");
        Console.printSeparator();
        System.out.print("Username: ");
        String username = MainApp.scanner.nextLine();
        System.out.print("Password: ");
        String password = MainApp.scanner.nextLine();

        User user = MainApp.userManager.login(username, password);
        System.out.println("Welcome, " + user.getNickname() + "!");
        return user;
    }

    private void login()
    {
        try {
            User user = performLogin();
            Console.sleep(1000);
            MainMenu mainMenu = new MainMenu(user);
            mainMenu.show();
        } catch(UserNotFoundException | InvalidCredentialsException e) {
            System.out.println(e.getMessage());
            Console.sleep(1000);
        }
    }
}

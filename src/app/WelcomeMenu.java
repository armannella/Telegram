package app;

import models.exception.InvalidCredentialsException;
import models.exception.UserAlreadyExistsException;
import models.exception.UserNotFoundException;
import models.user.User;
import util.Console;

public class WelcomeMenu {

    public void show()
    {
        
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

            switch (choice) {
                case 1:
                    register();
                    Console.sleep(1000);
                    break;

                case 2:
                    try {
                        User user = login();
                        Console.sleep(1000);
                        MainMenu mainMenu = new MainMenu(user);
                        mainMenu.show();
                    } catch(UserNotFoundException | InvalidCredentialsException e) {
                        System.out.println(e.getMessage());
                        Console.sleep(1000);
                    }
                    break;
 
                case 3:
                    System.out.println("GoodBye .");
                    Console.sleep(1000);
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
                    Console.sleep(1000);
                    break;
            }
        }
    }


    public void register()
    {
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
    }

    public User login() throws UserNotFoundException, InvalidCredentialsException 
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
}

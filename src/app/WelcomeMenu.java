package app;

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
            int choice = MainApp.scanner.nextInt();
            MainApp.scanner.nextLine();

            switch (choice) {
                case 1:
                    register();
                    Console.sleep(1000);
                    break;

                case 2:
                    User user = login();
                    Console.sleep(1000);
                    if (user != null) {
                        MainMenu mainMenu = new MainMenu(user);
                        mainMenu.show();
                        break;
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



        if(MainApp.userManager.userExists(username) || MainApp.userManager.phoneNumberExists(phone))
        {
            System.out.println("User Exists");
            return ;
        }

        else {
            MainApp.userManager.register(username, password, nickname, phone);
            System.out.println("Register Successful");
        }
        
        
    }
    public User login()
    {
        Console.clearScreen();
        System.out.println("Login Menu");
        Console.printSeparator();
        System.out.print("Username: ");
        String username = MainApp.scanner.nextLine();
        System.out.print("Password: ");
        String password = MainApp.scanner.nextLine();

        User user = MainApp.userManager.login(username, password);
        if (user == null) {
            System.out.println("Login failed. Try again.");
            return null;
        }
    
        System.out.println("Welcome, " + user.getNickname() + "!");
        return user;

    }
}

package app;

import models.user.BasicPlan;
import models.user.GoldPlan;
import models.user.SilverPlan;
import models.user.User;
import util.Console;

public class AccountDetails {
    
    private User user;

    public AccountDetails(User user) {
        this.user = user;
    }

    public void show() {
        while (true) {
            Console.clearScreen();
            System.out.println("Account Details:");
            Console.printSeparator();
            System.out.println("Username: " + user.getUsername());
            System.out.println("Nickname: " + user.getNickname());
            System.out.println("Phone Number: " + user.getPhonenumber());
            System.out.println("User Plan: " + user.getUserplan().getPlanName());
            System.out.println("Bio : " + user.getProfile().getBio());
            Console.printSeparator();
            
            System.out.println("1. Change Nickname");
            System.out.println("2. Change Password");
            System.out.println("3. Upgrade/Downgrade Plan");
            System.out.println("4. Change Bio");
            System.out.println("0. Back");
            Console.printSeparator();
            System.out.print("Choose an option: ");
            int choice = Console.NextInt(MainApp.scanner);

            switch (choice) {
                case 1:
                    changeNickname();
                    break;
                case 2:
                    changePassword();
                    break;
                case 3:
                    changePlan();
                    break;
                case 4:
                    changeBio();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
                    Console.sleep(1000);
            }
        }
    }

    private void changeNickname() {
        Console.clearScreen();
        System.out.print("Enter new nickname: ");
        String newNickname = MainApp.scanner.nextLine();
        user.setNickname(newNickname);
        System.out.println("Nickname changed successfully.");
        Console.sleep(1000);
    }

    private void changePassword() {
        Console.clearScreen();
        System.out.print("Enter current password: ");
        String currentPassword = MainApp.scanner.nextLine();
        if (user.checkPassword(currentPassword)) {
            System.out.print("Enter new password: ");
            String newPassword = MainApp.scanner.nextLine();
            user.setPassword(newPassword);
            System.out.println("Password changed successfully.");
        } else {
            System.out.println("Incorrect current password.");
        }
        Console.sleep(1000);
    }

    private void changePlan() {
        Console.clearScreen();
        System.out.println("Available Plans:");
        Console.printSeparator();
        System.out.println("1. Basic Plan");
        System.out.println("2. Silver Plan");
        System.out.println("3. Gold Plan");
        System.out.print("Choose a new plan: ");
        
        int planChoice = Console.NextInt(MainApp.scanner);

        switch (planChoice) {
            case 1:
                user.setUserplan(new BasicPlan());
                break;
            case 2:
                user.setUserplan(new SilverPlan());
                break;
            case 3:
                user.setUserplan(new GoldPlan());
                break;
            default:
                System.out.println("Invalid plan choice.");
                Console.sleep(1000);
                return;
        }

        System.out.println("User plan changed successfully.");
        Console.sleep(1000);
    }

    private void changeBio() {
        Console.clearScreen();
        System.out.print("Enter new Bio: ");
        String newBio = MainApp.scanner.nextLine();
        user.getProfile().setBio(newBio);;
        System.out.println("Bio changed successfully.");
        Console.sleep(1000);
    }
}

package app;

import models.user.Post;
import models.user.User;
import util.Console;

public class InstagramMenu {
    private User viewer; 

    public InstagramMenu(User viewer) {
        this.viewer = viewer;
    }

    public void show() {
        while (true) {
            Console.clearScreen();
            System.out.println("Instagram Section:");
            Console.printSeparator();
            System.out.println("1. View a User's Profile");
            System.out.println("0. Back");

            System.out.print("Choose an option: ");
            int choice = MainApp.scanner.nextInt();
            MainApp.scanner.nextLine();

            switch (choice) {
                case 1:
                    viewUserProfile();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
                    Console.sleep(1000);
            }
        }
    }

    private void viewUserProfile() {
        Console.clearScreen();
        System.out.print("Enter username to search: ");
        String username = MainApp.scanner.nextLine();
        User user = MainApp.userManager.findUserByUsername(username);

        if (user != null) {
            Console.clearScreen();
            System.out.println(" Profile of: " + user.getNickname() + " (" + user.getUsername() + ")");
            Console.printSeparator();
            System.out.println("Phone: " + user.getPhonenumber());
            System.out.println("Bio: " + user.getProfile().getBio());
            System.out.println("\nPosts:");
            Console.printSeparator();
            MainApp.postManager.showUserPosts(user);

            Console.printSeparator();
            System.out.println("1. Like a Post");
            System.out.println("0. Back");
            Console.printSeparator();
            System.out.print("Choose an option: ");
            int choice = MainApp.scanner.nextInt();
            MainApp.scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Post id to like : ");
                    int choiceID = MainApp.scanner.nextInt();
                    MainApp.scanner.nextLine();
                    Post post = user.getProfile().getPostbyID(choiceID);
                    if(post!= null)
                    {
                        post.addLike(viewer);
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
                    Console.sleep(1000);
                }
            } 
            else {
                System.out.println("User not found.");
                Console.sleep(1000);
        }
    }
}

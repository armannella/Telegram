package app;

import models.exception.IdNotFoundException;
import models.exception.UserNotFoundException;
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
            int choice = Console.NextInt(MainApp.scanner);

            switch (choice) {
                case 1:
                    searchUser();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
                    Console.sleep(1000);
            }
        }
    }

    private void searchUser() {
        Console.clearScreen();
        System.out.print("Enter username to search: ");
        String username = MainApp.scanner.nextLine();
        try {
            User user = MainApp.userManager.findUserByUsername(username);
            viewUserProfile(user);
        } catch (UserNotFoundException e)
        {
            System.out.println(e.getMessage());
            Console.sleep(1000);
        }
        
    }

    private void viewUserProfile(User user) {
        while (true) {
            Console.clearScreen();
            System.out.println(" Profile of: " + user.getNickname() + " (" + user.getUsername() + ")");
            Console.printSeparator();
            System.out.println("Phone: " + user.getPhonenumber());
            System.out.println("Bio: " + user.getProfile().getBio());
            System.out.println("Posts:");
            Console.printSeparator();
            MainApp.postManager.showUserPosts(user);
            Console.printSeparator();
            System.out.println("1. Like a Post");
            System.out.println("0. Back");
            Console.printSeparator();
            System.out.print("Choose an option: ");
            int choice = Console.NextInt(MainApp.scanner);

            switch (choice) {
                case 1:
                
                    System.out.print("Enter Post id to like : ");
                    int choiceID = Console.NextInt(MainApp.scanner);
                    Post post;
                    try {
                        post = user.getProfile().getPostbyID(choiceID);
                        post.addLike(viewer);
                    } catch (IdNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 0:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
                    Console.sleep(1000);
                }
            } 
    }
}

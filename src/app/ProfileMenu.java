package app;

import java.util.HashMap;
import java.util.Map;
import models.exception.IdNotFoundException;
import models.exception.PostLimitExceededException;
import models.user.Post;
import models.user.User;
import util.Console;

public class ProfileMenu {
    private User user;
    private final Map<Integer, Runnable> menuActions = new HashMap<>();

    public ProfileMenu(User user) {
        this.user = user;
        initializeMenuActions();
    }

    private void initializeMenuActions() {
        menuActions.put(1, this::viewPosts);
        menuActions.put(2, this::addPost);
        menuActions.put(3, this::removePost);
        menuActions.put(4, () -> new AccountDetails(user).show());
    }

    public void show() {
        while (true) {
            Console.clearScreen();
            System.out.println("My Profile:");
            Console.printSeparator();
            System.out.println("1. View My Posts");
            System.out.println("2. Add New Post");
            System.out.println("3. Remove Post");
            System.out.println("4. Change Personal Info");
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

    private void viewPosts() {
        Console.clearScreen();
        System.out.println("Your Posts:");
        Console.printSeparator();
        MainApp.postManager.showUserPosts(user);
        Console.printSeparator();
        System.out.println("0. Back");
        Console.printSeparator();
        System.out.print("Choose an option: ");
        int choice = Console.NextInt(MainApp.scanner);
        
        switch (choice) {
            case 0:
                return;
            default:
                System.out.println("Invalid option. Try again.");
                Console.sleep(1000);
        }

    }

    private void addPost() {
        Console.clearScreen();
        System.out.print("Enter caption: ");
        String caption = MainApp.scanner.nextLine();
        System.out.print("Enter image path: ");
        String imagePath = MainApp.scanner.nextLine();
        try {
        MainApp.postManager.addPost(user, caption, imagePath);
        System.out.println("Post added successfully.");
        } catch (PostLimitExceededException e) {
            System.out.println(e.getMessage());
        }
        Console.sleep(1000);
    }

    private void removePost() {
        Console.printSeparator();
        System.out.print("Enter Post ID to Delete: ");
        int index = Console.NextInt(MainApp.scanner);
        Post post;
        try {
            post = user.getProfile().getPostbyID(index);
            MainApp.postManager.removePost(user, post);
            System.out.println("Post removed successfully.");
        } catch (IdNotFoundException e) {
            System.out.println(e.getMessage());    
        }
        Console.sleep(1000);
    }

}

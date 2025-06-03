package app;

import java.util.HashMap;
import java.util.Map;
import models.exception.IdNotFoundException;
import models.exception.UserNotFoundException;
import models.user.Post;
import models.user.User;
import util.Console;

public class InstagramMenu {
    private User viewer;
    private User postowner;
    private final Map<Integer, Runnable> mainMenuActions = new HashMap<>();
    private final Map<Integer, Runnable> profileActions = new HashMap<>();

    public InstagramMenu(User viewer) {
        this.viewer = viewer;
        initializeMenuActions();
    }

    private void initializeMenuActions() {
        //menu action
        mainMenuActions.put(1, this::searchUser);

        //profile acction
        profileActions.put(1, () -> likePost(postowner));
    }

    public void show() {
        while (true) {
            Console.clearScreen();
            System.out.println("Instagram Section:");
            Console.printSeparator();
            System.out.println("1. View a User's Profile");
            System.out.println("0. Back");
            Console.printSeparator();
            System.out.print("Choose an option: ");
            int choice = Console.NextInt(MainApp.scanner);

            if (choice == 0) return;

            Runnable action = mainMenuActions.get(choice);
            if (action != null) {
                action.run();
            } else {
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
            this.postowner = user;
            viewUserProfile(user);
        } catch (UserNotFoundException e) {
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

            if (choice == 0) return;

            Runnable action = profileActions.get(choice);
            if (action != null) {
                action.run();
            } else {
                System.out.println("Invalid option. Try again.");
                Console.sleep(1000);
            }
        }
    }

    private void likePost(User postOwner) {
        System.out.print("Enter Post ID to like: ");
        int postId = Console.NextInt(MainApp.scanner);
        try {
            Post post = postOwner.getProfile().getPostbyID(postId);
            post.addLike(viewer);
            System.out.println("Post liked successfully.");
            Console.sleep(1000);
        } catch (IdNotFoundException e) {
            System.out.println(e.getMessage());
            Console.sleep(1000);
        }
    }
}

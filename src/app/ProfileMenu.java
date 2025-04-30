package app;

import models.user.Post;
import models.user.User;
import util.Console;

public class ProfileMenu {
    private User user;

    public ProfileMenu(User user) {
        this.user = user;
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
            int choice = MainApp.scanner.nextInt();
            MainApp.scanner.nextLine();

            switch (choice) {
                case 1:
                    viewPosts();
                    break;
                case 2:
                    addPost();
                    break;
                case 3:
                    removePost();
                    break;
                case 4:
                    AccountDetails accountDetails = new AccountDetails(user);
                    accountDetails.show();
                    break;
                case 0:
                    return;
                default:
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
        int choice = MainApp.scanner.nextInt();
        MainApp.scanner.nextLine();
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

        if(MainApp.postManager.addPost(user, caption, imagePath))
        {
            System.out.println("Post added successfully.");
        }
        Console.sleep(1000);
    }

    private void removePost() {
        Console.printSeparator();
        System.out.print("Enter Post ID to Delete: ");
        int index = MainApp.scanner.nextInt();
        MainApp.scanner.nextLine();

        
        Post post = user.getProfile().getPostbyID(index);
        if (post != null)
        {
            MainApp.postManager.removePost(user, post);
            System.out.println("Post removed successfully.");
        }

        else {
            System.out.println("Invalid ID.");
        }
        Console.sleep(1000);

    }

}

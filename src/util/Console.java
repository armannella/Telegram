package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception ex) {
            System.out.println("Could not clear screen.");
        }
    }

    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Sleep interrupted!");
        }
    }

    public static void printSeparator() {
        System.out.println("------------------------------------------");
    }

    public static int NextInt(Scanner scanner)
    {
            while (true) {
                try {
                    int input = scanner.nextInt();
                    scanner.nextLine();
                    return input;

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); 
                }
            }
    }
        
}


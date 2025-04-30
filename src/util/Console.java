package util;

public class Console {

    /* use it for clearing terminal before showing new menu **/
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

    /*print a separator line for better design in terminal **/
    public static void printSeparator() {
        System.out.println("------------------------------------------");
    }
}

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Trimmer started, enter your command:");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        Trimmer trimmer = new Trimmer();
        trimmer.start(command);
    }
}

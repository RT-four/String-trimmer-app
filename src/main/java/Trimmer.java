import java.util.List;
import java.util.Scanner;

public class Trimmer {
    private final String CommandPattern = "cut (-c|-w) ((-o ([^ ]+) )|())((([^ ]+) )|())range (([0-9]+-[0-9]+)|(-[0-9]+)|([0-9]+-))";
    private Command command;

    public void start() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Trimmer started, enter your command:");
        String input = scanner.nextLine();
        if (!input.matches(CommandPattern)) {
            throw new Exception("Illegal command");
        }
        System.out.println("Starting trimming");

        this.command = new Command(input);
    }


}

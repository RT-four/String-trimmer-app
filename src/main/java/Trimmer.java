import java.util.List;

public class Trimmer {
    private final String CommandPattern = "cut (-c|-w) ((-o ([^ ]+) )|())((([^ ]+) )|())range (([0-9]+-[0-9]+)|(-[0-9]+)|([0-9]+-))";
    private Command command;

    public void start(String input) throws Exception {
        if (!input.matches(CommandPattern)) {
            throw new Exception("Illegal command");
        }
        System.out.println("Starting trimming");

        this.command = new Command(input);
    }


}

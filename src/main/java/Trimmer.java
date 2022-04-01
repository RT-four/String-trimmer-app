import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Trimmer {
    private final String CommandPattern = "cut (-c|-w) ((-o ([^ ]+) )|())((([^ ]+) )|())range (([0-9]+-[0-9]+)|(-[0-9]+)|([0-9]+-))";
    private Command command;
    private List<String> inputData = new ArrayList<>();
    private List<String> outputData = new ArrayList<>();

    //cut -w -o dfdfd D:\Programming\StringTrimmingProgram\data.txt range 5-10
    public void process() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Trimmer started, enter your command:");
        String input = scanner.nextLine();
        if (!input.matches(CommandPattern)) {
            throw new Exception("Illegal command, may be your file paths contain spaces. They must have no spaces inside");
        }
        System.out.println("Starting trimming");
        this.command = new Command(input);


        if (!command.getInputFilePath().equals("console")) {
            File file = readFile();
        } else {
            // TODO: 01.04.2022 Console input
        }


        if (command.getMode().equals("w")) {
            trimByWords();
        } else {
            // TODO: 01.04.2022 Trim by char
        }
        System.out.println(outputData);
    }


    public void trimByWords() {
        for (String inString : inputData) {
            String newString = "";
            inString = inString.trim().replaceAll("\\\\s+", " ");
            List<String> words = List.of(inString.split(" "));
            Integer start;
            Integer end;
            if (command.getRangeFrom().equals("process")) {
                start = 0;
            } else {
                start = Integer.valueOf(command.getRangeFrom());
            }
            if (command.getRangeTo().equals("end")) {
                end = words.size();
            } else {
                end = Integer.valueOf(command.getRangeTo());
            }

            for (int i = start - 1; i < end; i++) {
                i = (i < 0) ? 0 : i;
                if (i < words.size()) {
                    newString = newString + " " + words.get(i);
                }
            }
            outputData.add(newString.trim());
        }
    }

    public File readFile() {
        File file = null;
        try {
            file = new File(command.getInputFilePath());
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                inputData.add(line);
                line = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


}

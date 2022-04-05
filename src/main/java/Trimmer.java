import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Trimmer {
    private final String CommandPattern = "cut (-c|-w) ((-o ([^ ]+) )|())((([^ ]+) )|())range (([0-9]+-[0-9]+)|(-[0-9]+)|([0-9]+-))";
    private Command command;
    private List<String> inputData = new ArrayList<>();
    private List<String> outputData = new ArrayList<>();

    //cut -w -o D:\Programming\String-trimmer-app\testDocs\output.txt D:\Programming\String-trimmer-app\testDocs\data1.txt range 5-6
    public void process() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Trimmer started, enter your command:");
        String input = scanner.nextLine();
        if (!input.matches(CommandPattern)) {
            throw new Exception("Illegal command, may be your file paths contain spaces. They must have no spaces inside");
        }
        try {
            System.out.println("Starting trimming");
            this.command = new Command(input);


            if (!command.getInputFilePath().equals("console")) {
                File file = readFile();
            } else {
                System.out.println("Ready to read your text from console, paste it:");
                ArrayList<String> ns = new ArrayList<>();
                while (true) {
                    String str = scanner.nextLine();
                    if (str.equals("")) {
                        break;
                    }
                    inputData.add(str);
                }
            }


            if (command.getMode().equals("w")) {
                trimByWords();
            } else {
                trimByChar();
            }

            if (!command.getOutputFilePath().equals("console")) {
                writeFile();
                System.out.println("Successfully trimmed!");
            } else {
                System.out.println("Successfully trimmed!");
                System.out.println();
                for (String string : outputData) {
                    System.out.println(outputData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void trimByChar() {
        try {
            for (String inString : inputData) {
                String newString = "";
                char[] chars = inString.toCharArray();
                Integer start;
                Integer end;
                if (command.getRangeFrom().equals("process")) {
                    start = 0;
                } else {
                    start = Integer.valueOf(command.getRangeFrom());
                }
                if (command.getRangeTo().equals("end")) {
                    end = chars.length;
                } else {
                    end = Integer.valueOf(command.getRangeTo());
                }

                for (int i = start - 1; i < end; i++) {
                    i = (i < 0) ? 0 : i;
                    if (i < chars.length) {
                        newString += chars[i];
                    }
                }
                outputData.add(newString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void trimByWords() {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void writeFile() throws IOException {
        try {
            FileWriter writer = new FileWriter(command.getOutputFilePath());
            for (String string : outputData) {
                writer.write(string);
                writer.write("\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }


}

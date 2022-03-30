import java.util.List;

public class Command {
    private String mode;
    private String inputFilePath;
    private String outputFilePath;
    private String rangeFrom;
    private String rangeTo;

    public Command(String command) {
        List<String> commandList = List.of(command.split(" "));
        this.mode = Character.toString(commandList.get(1).charAt(1));
        if (command.contains("-o")) {
            this.outputFilePath = commandList.get(3);
            this.inputFilePath = (commandList.size() == 7) ? commandList.get(4) : "console";
        } else {
            this.outputFilePath = "console";
            this.inputFilePath = (commandList.size() == 5) ? commandList.get(2) : "console";

        }
        String rangeString = commandList.get(commandList.size()-1);
        if(rangeString.startsWith("-")){
            this.rangeFrom = "start";
            this.rangeTo = rangeString.substring(1);
        }else if(rangeString.endsWith("-")){
            this.rangeFrom = rangeString.substring(0, rangeString.length()-1);
            this.rangeTo = "end";
        }else {
            this.rangeFrom = rangeString.substring(0, rangeString.indexOf('-'));
            this.rangeTo = rangeString.substring(rangeString.indexOf('-')+1);
        }
    }

    public String getMode() {
        return mode;
    }

    public String getInputFilePath() {
        return inputFilePath;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public String getRangeFrom() {
        return rangeFrom;
    }

    public String getRangeTo() {
        return rangeTo;
    }
}

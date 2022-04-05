import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandTest {

    @Test
    public void getMode() {
        Command command = new Command("cut -w -o D:\\Programming\\String-trimmer-app\\testDocs\\output.txt D:\\Programming\\String-trimmer-app\\testDocs\\data1.txt range 5-6");
        assertEquals("w", command.getMode());
    }

    @Test
    public void getInputFilePath() {
        Command command = new Command("cut -w -o D:\\Programming\\String-trimmer-app\\testDocs\\output.txt D:\\Programming\\String-trimmer-app\\testDocs\\data1.txt range 5-6");
        assertEquals("D:\\Programming\\String-trimmer-app\\testDocs\\data1.txt", command.getInputFilePath());
    }

    @Test
    public void getOutputFilePath() {
        Command command = new Command("cut -w -o D:\\Programming\\String-trimmer-app\\testDocs\\output.txt D:\\Programming\\String-trimmer-app\\testDocs\\data1.txt range 5-6");
        assertEquals("D:\\Programming\\String-trimmer-app\\testDocs\\output.txt", command.getOutputFilePath());
    }

    @Test
    public void getRangeFrom() {
        Command command = new Command("cut -w -o D:\\Programming\\String-trimmer-app\\testDocs\\output.txt D:\\Programming\\String-trimmer-app\\testDocs\\data1.txt range 5-6");
        assertEquals("5", command.getRangeFrom());
    }

    @Test
    public void getRangeTo() {
        Command command = new Command("cut -w -o D:\\Programming\\String-trimmer-app\\testDocs\\output.txt D:\\Programming\\String-trimmer-app\\testDocs\\data1.txt range 5-6");
        assertEquals("6", command.getRangeTo());
    }
}
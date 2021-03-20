import java.text.ParseException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws ParseException {
        Map<String, String> variablesMap = init();
        List<String> expressionList = UserInput.getInput();
        InfixProcessing ip = new InfixProcessing();
        String result = ip.process(expressionList, variablesMap);
        System.out.println("Result is: " + result);

    }

    private static Map<String, String> init() {
        ReadFile readFile = new ReadFile();
        return readFile.read();
    }
}

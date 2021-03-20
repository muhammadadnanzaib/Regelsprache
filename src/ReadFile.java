import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReadFile {
    private final String assignmentFilePath = Utils.ASSIGNMENT_FILE_PATH;
    VariableFileValidator variableFileValidator;

    public ReadFile() {
        this.variableFileValidator = new VariableFileValidator();
    }

    public Map<String, String> read() {
        Map<String, String> map = new HashMap<>();
        // https://www.w3schools.com/java/java_files_read.asp
        try {
            File myObj = new File(assignmentFilePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (!data.isEmpty()) {
                    String[] pairVarialbeValue = this.variableFileValidator.parseAssigment(data);
                    map.put(pairVarialbeValue[0], pairVarialbeValue[1]);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return map;
    }


}

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInput {

    public static List<String> getInput() {
        Scanner sc = new Scanner(System.in);    //System.in is a standard input stream
        System.out.println("Enter your expression to evaluate: ");
        String expression = sc.nextLine();
        System.out.println("Inputed expression is: " + expression);

        List<String> list = split(expression);
        return list;

    }

    private static List<String> split(String string) throws NullPointerException {
        if (string == null) {
            throw new NullPointerException("the given string is null!");
        }
        string = string.replace("(", "").replace(")", "");
        List<String> result = new ArrayList<String>();

        int index = 0;
        while (index < string.length()) {
            // find the index of the nearest operator
            int minimum = string.length();
            for (String operator : Utils.OPERATORS) {
                int i = string.indexOf(operator, index);
                if (i > -1)
                    minimum = Math.min(minimum, i);
            }

            // if an operator is found, split the string
            if (minimum < string.length()) {
                result.add(string.substring(index, minimum).trim());
                result.add("" + string.charAt(minimum));
                index = minimum + 1;
            } else {
                result.add(string.substring(index).trim());
                break;
            }
        }

        result.removeIf(item -> "".equals(item)); // remove empty strings
        if (result.get(0).equals("-"))
            result.add(0, result.remove(0) + result.remove(0)); //

        return result;
    }


}



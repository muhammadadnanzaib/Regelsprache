import java.text.NumberFormat;
import java.util.*;

public class Utils {
    public static final List<String> OPERATORS = Arrays.asList("+", "-", "*", "/");
    public static final String ASSIGNMENT_FILE_PATH = ".\\miscellaneous\\variable_assignments.txt";

    public static String trimQuotes(String str) {
        if (isQuotedString(str)) {
            str = str.substring(1, str.length() - 1);
        }
        return str;
    }

    public static boolean isQuotedString(String str) {
        return isSingleQuotedString(str) || isDoubleQuotedString(str);
    }

    public static boolean isSingleQuotedString(String str) {
        return str.startsWith("\'") && str.endsWith("\'");
    }

    public static boolean isDoubleQuotedString(String str) {
        return str.startsWith("\"") && str.endsWith("\"");
    }
    public static boolean isQuotationMismatched(String str) {
        return (str.startsWith("\"") && str.endsWith("\'"))
                ||
                (str.startsWith("\'") && str.endsWith("\""))
                ;
    }

    private static boolean isVariable(String str) {
        return startsWithLetter(str);
    }

    public static boolean isOperator(String value) {
        return OPERATORS.contains(value);
    }

    public static boolean isNumericValue(String variableValue) {
        // To fix regex for .5
//        https://stackoverflow.com/questions/43156077/how-to-check-a-string-is-float-or-int/43156135
        boolean valid = variableValue.matches("[-+]?[0-9]*\\.?[0-9]+");
        ;
        return valid;
    }

    public static boolean isNumber(String value) {
        return !isVariable(value) && !isOperator(value) && !isQuotedString(value);
    }

    public static boolean isFormatValid(String number) {
        // https://stackoverflow.com/questions/28360966/regular-expression-to-match-german-number
        return number.matches("[-+]?[0-9]{1,3}(?:\\.[0-9]{3})*(?:\\,[0-9]+)?");
    }

    public static boolean startsWithLetter(String str) {
        return str.length() != 0 ? Character.isLetter(str.charAt(0)) : false;
    }

    public static String addition(String operand1, String operand2) {
        String result;
        if (isNumericValue(operand1) && isNumericValue(operand2)) {
            double d = Double.parseDouble(operand1) + Double.parseDouble(operand2);
            result = d == (int) d ? String.valueOf((int) d) : String.valueOf(d); // https://stackoverflow.com/questions/703396/how-to-nicely-format-floating-numbers-to-string-without-unnecessary-decimal-0s
        } else if (!isNumericValue(operand1) && !isNumericValue(operand2)) {
            result = trimQuotes(operand1) + trimQuotes(operand2);
        } else if (isNumericValue(operand1) && !isNumericValue(operand2)) {
            result = operand1 + trimQuotes(operand2);
        } else {
            result = trimQuotes(operand1) + operand2;
        }
        return result;
    }

    public static String subtraction(String operand1, String operand2) {
        String result;
        if (isNumericValue(operand1) && isNumericValue(operand2)) {
            double d = Double.parseDouble(operand1) - Double.parseDouble(operand2);
            result = d == (int) d ? String.valueOf((int) d) : String.valueOf(d); // https://stackoverflow.com/questions/703396/how-to-nicely-format-floating-numbers-to-string-without-unnecessary-decimal-0s

        } else {
            throw new CustomException("Invalid operand types for subtraction. Error!");
        }
        return result;
    }

    public static String multiply(String operand1, String operand2) {
        String result;
        if (isNumericValue(operand1) && isNumericValue(operand2)) {
            double d = Double.parseDouble(operand1) * Double.parseDouble(operand2);
            result = d == (int) d ? String.valueOf((int) d) : String.valueOf(d); // https://stackoverflow.com/questions/703396/how-to-nicely-format-floating-numbers-to-string-without-unnecessary-decimal-0s
        } else {
            throw new CustomException("Invalid operand types for multiplication. Error!");
        }
        return result;
    }

    public static String division(String operand1, String operand2) {
        String result;
        if (isNumericValue(operand1) && isNumericValue(operand2)) {
            double d = Double.parseDouble(operand1) / Double.parseDouble(operand2);
            if (Math.abs(d) == Double.POSITIVE_INFINITY) {
                throw new CustomException("Division by zero not possible.Error");
            }
            result = d == (int) d ? String.valueOf((int) d) : String.valueOf(d); // https://stackoverflow.com/questions/703396/how-to-nicely-format-floating-numbers-to-string-without-unnecessary-decimal-0s

        } else {
            throw new CustomException("Invalid operand types for division. Error!");
        }
        return result;

    }


    public static String germanFormatToDefaultForm(String formattedNumber) {
        /* Converts 6.333.3432,64 to 63333432.64 */
        return formattedNumber.replace(".", "").replace(",", ".");
    }


    public static String formatToGermanNotation(String result) {
        /* Coverts "123456.78 to 123.456,78" */
        Double d = Double.parseDouble(result);
        return NumberFormat.getNumberInstance(Locale.GERMAN).format(d);
    }


}

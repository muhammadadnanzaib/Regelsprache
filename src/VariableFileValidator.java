

public class VariableFileValidator {

    public VariableFileValidator(){}


    public String[] parseAssigment(String assignmentExpression) throws CustomException {
        String variableName;
        String variableValue;
        // https://www.baeldung.com/java-split-string
        String[] arrNameValue = assignmentExpression.trim().split("\\s*=\\s*");

        variableName = arrNameValue[0];
        variableValue = arrNameValue[1];

        isVariableNameValid(variableName);

        if (Utils.isDoubleQuotedString(variableValue)) {
            arrNameValue[1] = Utils.trimQuotes(variableValue);

        } else if (Utils.isSingleQuotedString(variableValue)) {
            throw new CustomException("String with single quotation is not supported in variable assignment file. Error!");

        } else if(!Utils.isNumericValue(variableValue)) {
            throw new CustomException("Variable value is not valid. It must be integer or double. Error!");
        }
        return arrNameValue;
    }


    private boolean isVariableNameValid(String variableName) throws CustomException{
        boolean valid=true;

        if (variableName.contains("..")) {
            throw new CustomException("Variable name in variable assignment file cannot contain two consective periods. Error!");
        } else if (!Utils.startsWithLetter(variableName)){
            throw new CustomException("Variable name in variable assignment file does not start with a letter. Error!");
        } else {
            return valid;
        }
    }



}

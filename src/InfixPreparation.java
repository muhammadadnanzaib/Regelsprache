import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InfixPreparation {
    private List<String> expressionList;
    private List<String> keyList;
    private Map<String, String> variablesMap;

    public List<String> prepare(List<String> expressionList, Map<String, String> variablesMap) {
        this.expressionList = expressionList;
        this.variablesMap = variablesMap;

        isExpressionListValid();

        this.expressionList = replaceNumberFormat();
        this.keyList = getVariableList();

        if (this.keyList != null && !containsAllKeys()) {
            throw new CustomException("Not all variables in expression are declared.");
        }
        this.expressionList = replaceVariableNameByValue();
//        System.out.println("Printing expression list after conversion: " + this.expressionList);
        return this.expressionList;
    }

    private List<String> replaceNumberFormat() {
        List<String> resultList = new ArrayList<>();
        String toAdd;

        for (String value : this.expressionList) {
            if (!value.isEmpty() && Utils.isNumber(value)) {
                if (Utils.isFormatValid(value)) {
                    toAdd = Utils.germanFormatToDefaultForm(value);
                } else {
                    throw new CustomException("Invalid decimal format; german notation not followed");
                }
            } else {
                toAdd = value;
            }
            resultList.add(toAdd);
        }
        return resultList;
    }

    private List<String> getVariableList() {
        List<String> keyList = new ArrayList<>();

        for (String value : this.expressionList) {
            if (!Utils.isQuotedString(value)
                    && !Utils.isOperator(value)
                    && !Utils.isNumericValue(value)
            ) {
                keyList.add(value);
            }
        }
        return keyList;
    }

    private boolean containsAllKeys() {
        for (String key : this.keyList) {
            if (!this.variablesMap.keySet().contains(key)) {
                return false;
            }
        }
        return true;
    }

    private List<String> replaceVariableNameByValue() {
        List<String> resultList = new ArrayList<>();
        for (String value : this.expressionList) {
            String toAdd = this.keyList.contains(value) ? this.variablesMap.get(value) : value;
            resultList.add(toAdd);
        }
        return resultList;
    }
    private boolean isExpressionListValid() {
        /* This implementation assumes that all operators are binary */
        int i;
        for (i = 0; i < this.expressionList.size(); i++) {
            String value = this.expressionList.get(i);
            if (i % 2 == 0 == Utils.isOperator(value)) {
                throw new CustomException("Syntax error. Expression is not valid.");
            } else if (Utils.isQuotationMismatched(value)){
                throw new CustomException("String quotation mismatched. Used either single or doubles quotes..");
            }
        }
        if (Utils.isOperator(this.expressionList.get(i - 1))){
            throw new CustomException("Syntax error. Expression is not valid.");
        }
        return true;
    }
}

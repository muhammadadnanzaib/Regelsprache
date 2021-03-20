import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Stack;

public class InfixEvaluation {
    private Stack<String> operandStack;
    private Stack<String> operatorStack;
    private List<String> expressionList;

    public InfixEvaluation() {
        this.operandStack = new Stack<String>();
        this.operatorStack = new Stack<String>();
    }

    public String evaluate(List<String> expressionList) {
        String result;
        this.expressionList = expressionList;

        initializeStacks();
        result = evaluateStacks();
        result = Utils.isNumericValue(result) ? Utils.formatToGermanNotation(result) : result;
        return result;
    }



    private String evaluateStacks() {
        while (!this.operatorStack.isEmpty()) {
            performBinaryOperation(this.operatorStack.pop());
        }
        return this.operandStack.pop();
    }

    private void initializeStacks() {
        for (String value : this.expressionList) {

            if (Utils.isOperator(value)) {
                String peekedOperator = this.operatorStack.isEmpty() ? null : this.operatorStack.peek();
                if (peekedOperator != null && (peekedOperator == "*" || peekedOperator == "/")) {
                    performBinaryOperation(this.operatorStack.pop());
                } else {
                    this.operatorStack.push(value);
                }
            } else {
                this.operandStack.push(value);
            }
        }

    }

    private void performBinaryOperation(String operator) {
        String operand2 = this.operandStack.pop();
        String operand1 = this.operandStack.pop();
        if (operator.equals("+")) {
            this.operandStack.push(Utils.addition(operand1, operand2));
        } else if (operator.equals("-")) {
            this.operandStack.push(Utils.subtraction(operand1, operand2));
        } else if (operator.equals("*")) {
            this.operandStack.push(Utils.multiply(operand1, operand2));
        } else if (operator.equals("/")) {
            this.operandStack.push(Utils.division(operand1, operand2));
        }

    }

}

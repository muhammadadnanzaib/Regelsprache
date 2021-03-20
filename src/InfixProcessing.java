import java.util.List;
import java.util.Map;

public class InfixProcessing {
    private InfixPreparation infixPreparation;
    private InfixEvaluation infixEvaluation;

    public InfixProcessing() {
        this.infixPreparation = new InfixPreparation();
        this.infixEvaluation = new InfixEvaluation();
    }

    public String process(List<String> expressionList, Map<String, String> variablesMap) {
        expressionList = this.infixPreparation.prepare(expressionList, variablesMap);
        return this.infixEvaluation.evaluate(expressionList);
    }
}

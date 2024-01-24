package g60131.atlg.turingmachine.view;

import g60131.atlg.turingmachine.model.TuringMachine;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;

import java.util.HashMap;
import java.util.Map;

public class FxValidatorList extends FlowPane {
    private final Map<Integer, FxValidator> validators = new HashMap<>();
    private final TuringMachine turingMachine;
    public FxValidatorList(TuringMachine turingMachine) {
        this.setHgap(20);
        this.setVgap(10);
        this.setAlignment(Pos.TOP_CENTER);
        this.turingMachine = turingMachine;
    }

    /**
     * Clears the results of all validators associated with this container.
     * Invokes the 'clear' method for each individual validator.
     */
    public void clear() {
        for (Map.Entry<Integer, FxValidator> validator : validators.entrySet()) {
            validator.getValue().clear();
        }
    }

    /**
     * Adds a new validator to the container with the specified index and letter.
     * Creates a new instance of FxValidator, associates it with the given index and letter,
     * and adds it to the container.
     * @param validatorIndex The index of the validator.
     * @param letter         The letter associated with the validator.
     */
    public void addValidator(int validatorIndex, String letter) {
        FxValidator validator = new FxValidator(turingMachine, validatorIndex, letter.toUpperCase());
        validators.put(validatorIndex, validator);
        this.getChildren().add(validator);
    }

    /**
     * Refreshes the result display of a specific validator.
     * Updates the result for the validator with the given index.
     * @param validatorIndex The index of the validator to refresh.
     * @param result         The result to be displayed (null for no result).
     */
    public void refresh(int validatorIndex, Boolean result) {
        validators.get(validatorIndex).setResult(result);
    }
}
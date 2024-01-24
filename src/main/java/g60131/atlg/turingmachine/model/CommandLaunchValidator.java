package g60131.atlg.turingmachine.model;

import g60131.atlg.turingmachine.model.validator.Validator;
import g60131.atlg.turingmachine.util.Command;


public class CommandLaunchValidator implements Command {
    private final TuringMachine turingMachine;
    private final Round round;
    private final Validator validator;
    private int validatorTested;
    public CommandLaunchValidator(TuringMachine turingMachine, Round round, Validator validator, int validatorTested) {
        this.turingMachine = turingMachine;
        this.round = round;
        this.validator = validator;
        this.validatorTested = validatorTested;
    }

    @Override
    public void execute() {
        validatorTested++;
        round.addValidator(validator);
        turingMachine.setValidatorTested(validatorTested);

    }
    @Override
    public void unExecute() {
        validatorTested--;
        round.removeValidator(validator);
        turingMachine.setValidatorTested(validatorTested);
    }
}

package g60131.atlg.turingmachine.model;

import g60131.atlg.turingmachine.model.validator.Validator;

import java.util.HashMap;
import java.util.Map;

public class Round {
    private int[] proposedNb;
    private final Map<Validator, Boolean> validatorsTested = new HashMap<>();

    /**
     * Adds a validator to the round for testing. If the validator has already been tested,
     * an exception is thrown.
     * @param validator The validator to be added and tested.
     * @throws IllegalArgumentException If the validator has already been tested in this round.
     */
    public void addValidator(Validator validator) {
        if (validatorsTested.get(validator) != null) {
            throw new IllegalArgumentException("You have already tested this validator");
        }
        validatorsTested.put(validator, validator.launchValidator(proposedNb));
    }

    /**
     * Removes a validator from the list of tested validators.
     * @param validator The validator to be removed.
     */
    protected void removeValidator(Validator validator) {
        validatorsTested.remove(validator);
    }

    /**
     * Gets the array of proposed numbers for this round.
     * @return The array of proposed numbers.
     */
    public int[] getProposedNb() {
        return this.proposedNb;
    }

    /**
     * Gets the number of validators tested in this round.
     * @return The number of validators tested.
     */
    public int getNbValidatorsTested() {return validatorsTested.size();}

    /**
     * Gets the result of a specific validator tested in this round.
     * @param validator The validator for which to retrieve the result.
     * @return The result of the specified validator (true if passed, false if failed).
     */
    public Boolean getValidatorResult(Validator validator) {
        return validatorsTested.get(validator);
    }

    /**
     * Set the proposed number of the current round
     * @param proposedNb the proposedNb
     */
    public void setProposedNb(int[] proposedNb) {
        this.proposedNb = proposedNb;
    }
}

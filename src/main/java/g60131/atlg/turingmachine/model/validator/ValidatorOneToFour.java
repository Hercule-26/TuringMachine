package g60131.atlg.turingmachine.model.validator;

public class ValidatorOneToFour implements Validator {
    @Override
    public boolean launchValidator(int[] proposedNb) {
        return validator1To4(proposedNb);
    }

    private final int[] solution;
    private final int index;
    private final int value;

    public ValidatorOneToFour(int[] solution, int index, int value) {
        this.solution = solution;
        this.index = index;
        this.value = value;
    }

    /**
     * The methode verify if the proposed number and the solution
     * are smaller, equals or bigger than the value
     * @param proposedNb proposed number but split in 3
     * @return return true if the proposed number and the solution have the same result
     * or return false if it's not the same
     */
    public boolean validator1To4(int[] proposedNb) {
        return (proposedNb[index] < value && solution[index] < value)
                || (proposedNb[index] == value && solution[index] == value)
                || (proposedNb[index] > value && solution[index] > value);
    }
}
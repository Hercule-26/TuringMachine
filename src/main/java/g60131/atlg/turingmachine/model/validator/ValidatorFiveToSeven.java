package g60131.atlg.turingmachine.model.validator;

public class ValidatorFiveToSeven implements Validator {
    @Override
    public boolean launchValidator(int[] proposedNb) {
        return validator5To7(proposedNb);
    }

    private final int[] solution;
    private final int index;

    public ValidatorFiveToSeven(int[] solution, int index) {
        this.solution = solution;
        this.index = index;
    }

    /**
     * This method verify if the proposed number and the solution id even or odd
     * @param proposedNb the proposed number but split in 3
     * @return return true if the proposed number and the solution has the same result
     * or return false if it's not
     */
    public boolean validator5To7(int[] proposedNb) {
        return (proposedNb[index] % 2 == 0 && solution[index] % 2 == 0)
                || (proposedNb[index] % 2 == 1 && solution[index] % 2 == 1);
    }
}

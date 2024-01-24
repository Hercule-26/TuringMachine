package g60131.atlg.turingmachine.model.validator;

public class ValidatorNineTeen implements Validator {
    @Override
    public boolean launchValidator(int[] proposedNb) {
        return validator19(proposedNb);
    }

    private final int[] solution;

    public ValidatorNineTeen(int[] solution) {
        this.solution = solution;
    }

    /**
     * This method look if the sum of the first and the second number of the proposed number and the solution
     * is smaller, equals or bigger than 6
     * @param proposedNb the proposed number but split in 3
     * @return return true if the proposed number and the solution have the same result
     * or return false if it's not the same result
     */
    public boolean validator19(int[] proposedNb) {
        int proposedSum = proposedNb[0] + proposedNb[1];
        int solutionSum = solution[0] + solution[1];

        return ((proposedSum < 6) && (solutionSum < 6))
                || ((proposedSum == 6) && (solutionSum == 6))
                || ((proposedSum > 6) && (solutionSum > 6));
    }
}

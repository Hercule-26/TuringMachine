package g60131.atlg.turingmachine.model.validator;

public class ValidatorEighteen implements Validator {
    @Override
    public boolean launchValidator(int[] proposedNb) {
        return validator18(proposedNb);
    }

    private final int[] solution;

    public ValidatorEighteen(int[] solution) {
        this.solution = solution;
    }

    /**
     * This method look if the sum of proposed number and the sum solution
     * is even or odd
     * @param proposedNb the proposed number but split in 3
     * @return return true if the proposed number and the solution
     * have the same result or return false if it's not
     */
    public boolean validator18(int[] proposedNb) {
        int proposedSum = 0;
        int solutionSum = 0;
        for (int i = 0; i < solution.length; i++) {
            proposedSum += proposedNb[i];
            solutionSum += solution[i];
        }
        return ((proposedSum % 2 == 0) && (solutionSum % 2 == 0))
                || ((proposedSum % 2 == 1) && (solutionSum % 2 == 1));
    }

}

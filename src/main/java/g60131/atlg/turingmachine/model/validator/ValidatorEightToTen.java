package g60131.atlg.turingmachine.model.validator;

public class ValidatorEightToTen implements Validator {
    @Override
    public boolean launchValidator(int[] proposedNb) {
        return validator8To10(proposedNb);
    }
    private final int value;
    private final int[] solution;

    public ValidatorEightToTen(int[] solution, int value) {
        this.solution = solution;
        this.value = value;
    }

    /**
     * This method verify how much time a value is in the proposed number
     * and the same for the solution
     * @param proposedNb the proposed number but split in 3
     * @return return true if the proposed number and the solution have the same value
     * or false if it's not
     */
    public boolean validator8To10(int[] proposedNb) {
        int countProposed = 0;
        int countSolution = 0;
        for (int i = 0; i < solution.length; i++) {
            if (proposedNb[i] == value) {
                countProposed++;
            }
            if (solution[i] == value) {
                countSolution++;
            }
        }
        return countProposed == countSolution;
    }
}

package g60131.atlg.turingmachine.model.validator;

public class ValidatorElevenToThirteen implements Validator {
    @Override
    public boolean launchValidator(int[] proposedNb) {
        return validator11To13(proposedNb);
    }

    private final int[] solution;
    private final int firstNb;
    private final int secondNb;

    public ValidatorElevenToThirteen(int[] solution, int firstNb, int secondNb) {
        this.solution = solution;
        this.firstNb = firstNb;
        this.secondNb = secondNb;
    }

    /**
     * This method verify if the first number is smaller, equals or bigger than the second number of
     * the proposed number and the solution
     * @param proposedNb the proposed number but spit in 3
     * @return return true if the proposed number has the same result with the solution
     * or return false if it's not
     */
    public boolean validator11To13(int[] proposedNb) {
        return (proposedNb[firstNb] < proposedNb[secondNb] && solution[firstNb] < solution[secondNb])
                || (proposedNb[firstNb] == proposedNb[secondNb] && solution[firstNb] == solution[secondNb])
                || (proposedNb[firstNb] > proposedNb[secondNb] && solution[firstNb] > solution[secondNb]);
    }
}

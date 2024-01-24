package g60131.atlg.turingmachine.model.validator;


public class ValidatorFourteenToFifteen implements Validator {
    @Override
    public boolean launchValidator(int[] proposedNb) {
        return verifier14To15(proposedNb);
    }
    private final int[] solution;
    private final boolean min;

    public ValidatorFourteenToFifteen(int[] solution, boolean min) {
        this.solution = solution;
        this.min = min;
    }

    /**
     * This method look witch number in the smaller and witch one is bigger
     * @param proposedNb the proposed number but split in 3
     * @return return true if the proposed number has the same result
     * with the solution or return false if it's not the same result
     */
    public boolean verifier14To15(int[] proposedNb) {
        if (min) {
            return min(proposedNb) == min(solution);
        } else {
            return max(proposedNb) == max(solution);
        }
    }
    private int min(int[] number) {
        int min = number[0];
        int index = 0;
        for (int i = 1; i < number.length; i++) {
            if (number[i] == min) {
                index = -1;
            }
            if (min > number[i]) {
                min = number[i];
                index = i;
            }
        }
        return index;
    }
    private int max(int[] number) {
        int max = number[0];
        int index = 0;
        for (int i = 1; i < number.length; i++) {
            if (number[i] == max) {
                index = -1;
            }
            if (max < number[i]) {
                max = number[i];
                index = i;
            }
        }
        return index;
    }

}

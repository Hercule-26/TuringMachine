package g60131.atlg.turingmachine.model.validator;

public class ValidatorTwentyTwo implements Validator {
    @Override
    public boolean launchValidator(int[] proposedNb) {
        return validator22(proposedNb);
    }

    private final int[] solution;

    public ValidatorTwentyTwo(int[] solution) {
        this.solution = solution;
    }

    /**
     * This method look if the proposed number and the solution is
     * ascending, descending or none of these two
     * @param proposedNb the proposed number but split in 3
     * @return return true if the proposed number have the same with the solution or false if it's not
     */
    public boolean validator22(int[] proposedNb) {
        boolean propIsAscending = isAscending(proposedNb);
        boolean propIsDescending = isDescending(proposedNb);
        boolean solIsAscending = isAscending(solution);
        boolean solIsDescending = isDescending(solution);

        if ((propIsAscending && solIsAscending) || (propIsDescending && solIsDescending)) {
            return true;
        } else {
            return (!propIsAscending && !solIsAscending)
                    && (!propIsDescending && !solIsDescending);
        }
    }

    /**
     * This method look if an array is ascending
     * @param tab the array to look
     * @return return true if the array is ascending or false if it's not
     */
    private boolean isAscending(int[] tab) {
        for (int i = 0; i < tab.length - 1; i++) {
            if (tab[i] > tab[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method look if an array is descending
     * @param tab the array to look
     * @return return true if the array is descending or false if it's not
     */
    private boolean isDescending(int[] tab) {
        for (int i = 0; i < tab.length - 1; i++) {
            if (tab[i] < tab[i + 1]) {
                return false;
            }
        }
        return true;
    }
}

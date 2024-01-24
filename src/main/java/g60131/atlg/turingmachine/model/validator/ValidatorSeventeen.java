package g60131.atlg.turingmachine.model.validator;


public class ValidatorSeventeen implements Validator {
    @Override
    public boolean launchValidator(int[] proposedNb) {
        return validator17(proposedNb);
    }

    private final int[] solution;

    public ValidatorSeventeen(int[] solution) {
        this.solution = solution;
    }

    /**
     * The methode verify how much even number they are
     * @param proposedNb proposed number but split in 3
     * @return return true if the proposed number and the solution have the same result
     * or return false if it's not the same
     */
    public boolean validator17(int[] proposedNb) {
        int propEven = 0;
        int solEven = 0;
        for (int i = 0; i < solution.length; i++) {
            if (proposedNb[i] % 2 == 0) propEven++;
            if (solution[i] % 2 == 0) solEven++;
        }
        return (propEven == solEven);
    }
}

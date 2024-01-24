package g60131.atlg.turingmachine.model.validator;


public class ValidatorSixteen implements Validator {
    @Override
    public boolean launchValidator(int[] proposedNb) {
        return validator16(proposedNb);
    }

    private final int[] solution;

    public ValidatorSixteen(int[] solution) {
        this.solution = solution;
    }

    /**
     * The methode verify they are more even number or more odd number
     * @param proposedNb proposed number but split in 3
     * @return return true if the proposed number and the solution have the same result
     * or return false if it's not the same
     */
    public boolean validator16(int[] proposedNb) {
        int propEven = 0;
        int propOdd = 0;
        int solEven = 0;
        int solOdd = 0;
        for(int i = 0; i < solution.length; i++) {
            if (proposedNb[i] % 2 == 0) {
                propEven++;
            } else {
                propOdd++;
            }
            if (solution[i] % 2 == 0) {
                solEven++;
            } else {
                solOdd++;
            }
        }
        return (((propEven > propOdd) && (solEven > solOdd))
                || ((propOdd > propEven) && (solOdd > solEven)));
    }
}

package g60131.atlg.turingmachine.model.validator;



public class ValidatorTwentyOne implements Validator {
    @Override
    public boolean launchValidator(int[] proposedNb) {
        return validator21(proposedNb);
    }

    private final int[] solution;

    public ValidatorTwentyOne(int[] solution) {
        this.solution = solution;
    }

    /**
     * This method verify if the proposed number and the solution have
     * a number in exactly twice
     * @param proposedNb proposed number but spli in 3
     * @return return true if the proposed number and the solution have the same result
     * or return false if it's not
     */
    public boolean validator21(int[] proposedNb) {
        int propLastOcc = 0;
        int propCurrOcc = 0;
        int solLastOcc = 0;
        int solCurrOcc = 0;
        for (int i = 0; i < solution.length; i++) {
            int propTmp = proposedNb[i];
            int solTmp = solution[i];
            for (int j = i; j < solution.length; j++) {
                if (proposedNb[j] == propTmp) propCurrOcc++;
                if (solution[j] == solTmp) solCurrOcc++;
            }
            if (propCurrOcc > propLastOcc) propLastOcc = propCurrOcc;
            if (solCurrOcc > solLastOcc) solLastOcc = solCurrOcc;
            propCurrOcc = 0;
            solCurrOcc = 0;
        }
        return ((propLastOcc == 2) && (solLastOcc == 2))
                || ((propLastOcc !=2 ) && (solLastOcc != 2));
    }
}

package g60131.atlg.turingmachine.model.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorSeventeenTest {
    /*
        Validator 17 :
        Count how much Even number they are in the digit code.
    */
    @Test
    void validatorCountEventOk() {
        int[] proposedNb = {2, 4, 1};
        int[] solution = {4, 5, 2};
        Validator validator = new ValidatorSeventeen(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorCountEvenNotOk() {
        int[] proposedNb = {2, 4, 1};
        int[] solution = {3, 5, 2};
        Validator validator = new ValidatorSeventeen(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }
}
package g60131.atlg.turingmachine.model.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTwentyOneTest {
    /*
        Validator 21 :
        Verify if there is a number in exactly 2 time in the digit code
    */
    @Test
    void validatorExactly2TimeOk() {
        int[] proposedNb = {3, 4, 3};
        int[] solution = {5, 5, 2};
        Validator validator = new ValidatorTwentyOne(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorExactly2TimeNotOk() {
        int[] proposedNb = {3, 4, 3};
        int[] solution = {3, 5, 2};
        Validator validator = new ValidatorTwentyOne(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }
    @Test
    void validatorNotExactly2TimeOk() {
        int[] proposedNb = {3, 3, 3};
        int[] solution = {3, 5, 2};
        Validator validator = new ValidatorTwentyOne(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorNotExactly2TimeNotOk() {
        int[] proposedNb = {3, 3, 3};
        int[] solution = {3, 2, 2};
        Validator validator = new ValidatorTwentyOne(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }
}
package g60131.atlg.turingmachine.model.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTwentyTest {
    /*
        Validator 20 :
        Verify if they are repetition in the digit number and how much time.
    */
    @Test
    void validatorNoRepetitionOk() {
        int[] proposedNb = {3, 4, 1};
        int[] solution = {3, 5, 2};
        Validator validator = new ValidatorTwenty(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorNoRepetitionNotOk() {
        int[] proposedNb = {3, 4, 1};
        int[] solution = {3, 2, 3};
        Validator validator = new ValidatorTwenty(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }
    @Test
    void validatorRepetitionInDoubleOk() {
        int[] proposedNb = {1, 1, 4};
        int[] solution = {3, 5, 3};
        Validator validator = new ValidatorTwenty(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorRepetitionInDoubleNotOk() {
        int[] proposedNb = {1, 1, 4};
        int[] solution = {3, 3, 3};
        Validator validator = new ValidatorTwenty(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }
    @Test
    void validatorRepetitionInTripleOk() {
        int[] proposedNb = {4, 4, 4};
        int[] solution = {2, 2, 2};
        Validator validator = new ValidatorTwenty(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorRepetitionInTripleNotOk() {
        int[] proposedNb = {4, 4, 4};
        int[] solution = {3, 5, 2};
        Validator validator = new ValidatorTwenty(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }
}
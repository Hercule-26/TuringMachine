package g60131.atlg.turingmachine.model.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTwentyTwoTest {
    /*
        Validator 22 :
        Verify if the digit code is ascending, descending or no one of these two
    */
    @Test
    void validatorAscendingOk() {
        int[] proposedNb = {1, 2, 5};
        int[] solution = {3, 4, 5};
        Validator validator = new ValidatorTwentyTwo(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorAscendingNotOk() {
        int[] proposedNb = {1, 2, 5};
        int[] solution = {4, 2, 1};
        Validator validator = new ValidatorTwentyTwo(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }
    @Test
    void validatorDescendingOk() {
        int[] proposedNb = {5, 3, 1};
        int[] solution = {3, 2, 1};
        Validator validator = new ValidatorTwentyTwo(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorDescendingNotOk() {
        int[] proposedNb = {5, 3, 1};
        int[] solution = {3, 5, 2};
        Validator validator = new ValidatorTwentyTwo(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }
    @Test
    void validatorNoOneOk() {
        int[] proposedNb = {3, 4, 3};
        int[] solution = {3, 5, 2};
        Validator validator = new ValidatorTwentyTwo(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorNoOneNotOk() {
        int[] proposedNb = {3, 4, 3};
        int[] solution = {5, 3, 1};
        Validator validator = new ValidatorTwentyTwo(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }
}
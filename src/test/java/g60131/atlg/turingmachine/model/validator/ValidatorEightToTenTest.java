package g60131.atlg.turingmachine.model.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorEightToTenTest {
    /*
        Validator 8 to 10 :
        Verify how much time a number is in the digit code.
    */
    @Test
    void validatorCpr0TimeOk() {
        int[] proposedNb = {2, 5, 3};
        int[] solution = {4, 2, 2};
        Validator validator = new ValidatorEightToTen(solution, 1);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorCpr0TimeNotOk() {
        int[] proposedNb = {2, 5, 3};
        int[] solution = {4, 2, 1};
        Validator validator = new ValidatorEightToTen(solution, 1);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }
    @Test
    void validatorCpr1TimeOk() {
        int[] proposedNb = {1, 5, 3};
        int[] solution = {4, 2, 1};
        Validator validator = new ValidatorEightToTen(solution, 1);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorCpr1TimeNotOk() {
        int[] proposedNb = {1, 5, 3};
        int[] solution = {4, 1, 1};
        Validator validator = new ValidatorEightToTen(solution, 1);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }
    @Test
    void validatorCpr2TimeOk() {
        int[] proposedNb = {1, 1, 3};
        int[] solution = {1, 2, 1};
        Validator validator = new ValidatorEightToTen(solution, 1);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorCpr2TimeNotOk() {
        int[] proposedNb = {1, 1, 3};
        int[] solution = {4, 2, 5};
        Validator validator = new ValidatorEightToTen(solution, 1);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }
    @Test
    void validatorCpr3TimeOk() {
        int[] proposedNb = {1, 1, 1};
        int[] solution = {1, 1, 1};
        Validator validator = new ValidatorEightToTen(solution, 1);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorCpr3TimeNotOk() {
        int[] proposedNb = {1, 1, 1};
        int[] solution = {1, 5, 1};
        Validator validator = new ValidatorEightToTen(solution, 1);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }


}
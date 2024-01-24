package g60131.atlg.turingmachine.model.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorNineTeenTest {
    /*
        Validator 19 :
        Compare the sum of the first and second number in digit code is smaller, equals or Bigger than 6.
    */
    @Test
    void validatorSumSmallerOk() {
        int[] proposedNb = {1, 4, 1};
        int[] solution = {1, 3, 2};
        Validator validator = new ValidatorNineTeen(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorSumSmallerNotOk() {
        int[] proposedNb = {1, 4, 1};
        int[] solution = {3, 5, 2};
        Validator validator = new ValidatorNineTeen(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }
    @Test
    void validatorSumEqualsOk() {
        int[] proposedNb = {2, 4, 1};
        int[] solution = {1, 5, 2};
        Validator validator = new ValidatorNineTeen(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorSumEqualsNotOk() {
        int[] proposedNb = {2, 4, 1};
        int[] solution = {5, 5, 2};
        Validator validator = new ValidatorNineTeen(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }
    @Test
    void validatorSumBiggerOk() {
        int[] proposedNb = {3, 4, 1};
        int[] solution = {3, 5, 2};
        Validator validator = new ValidatorNineTeen(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorSumBiggerNotOk() {
        int[] proposedNb = {3, 4, 1};
        int[] solution = {1, 1, 2};
        Validator validator = new ValidatorNineTeen(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }
}
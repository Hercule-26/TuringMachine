package g60131.atlg.turingmachine.model.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorEighteenTest {
    /*
        Validator 18 :
        Calculate if the sum of the digit code is Even or Odd.
    */
    @Test
    void validatorSumEvenOk() {
        int[] proposedNb = {3, 4, 1};
        int[] solution = {3, 5, 2};
        Validator validator = new ValidatorEighteen(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorSumEvenNotOk() {
        int[] proposedNb = {3, 4, 1};
        int[] solution = {3, 5, 1};
        Validator validator = new ValidatorEighteen(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }
    @Test
    void validatorSumOddOk() {
        int[] proposedNb = {3, 4, 2};
        int[] solution = {3, 5, 3};
        Validator validator = new ValidatorEighteen(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorSumOddNotOk() {
        int[] proposedNb = {3, 4, 2};
        int[] solution = {3, 5, 2};
        Validator validator = new ValidatorEighteen(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }
}
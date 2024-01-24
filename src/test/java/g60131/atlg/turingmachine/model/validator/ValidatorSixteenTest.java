package g60131.atlg.turingmachine.model.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorSixteenTest {
    /*
       Validator 16 :
       Verify if they are more Even or Odd number in the digit code.
   */
    @Test
    void validatorMoreEvenOk() {
        int[] proposedNb = {4, 4, 1};
        int[] solution = {3, 4, 2};
        Validator validator = new ValidatorSixteen(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorMoreEvenNotOk() {
        int[] proposedNb = {4, 4, 1};
        int[] solution = {3, 5, 2};
        Validator validator = new ValidatorSixteen(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }
    @Test
    void validatorMoreOddOk() {
        int[] proposedNb = {3, 4, 1};
        int[] solution = {3, 5, 2};
        Validator validator = new ValidatorSixteen(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorMoreOddNotOk() {
        int[] proposedNb = {3, 5, 1};
        int[] solution = {3, 4, 2};
        Validator validator = new ValidatorSixteen(solution);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }
}
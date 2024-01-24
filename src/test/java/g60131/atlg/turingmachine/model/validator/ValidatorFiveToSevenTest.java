package g60131.atlg.turingmachine.model.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorFiveToSevenTest {
    /*
        Validator 5 to 7 :
        Verify if the number at index is Even or Odd.
    */
    @Test
    void validatorCprEvenOk() {
        int[] proposedNb = {2, 5, 3};
        int[] solution = {4, 2, 2};
        Validator validator = new ValidatorFiveToSeven(solution, 0);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorCprEvenNotOk() {
        int[] proposedNb = {2, 5, 3};
        int[] solution = {3, 2, 2};
        Validator validator = new ValidatorFiveToSeven(solution, 0);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }
    @Test
    void validatorCprOddOk() {
        int[] proposedNb = {2, 5, 3};
        int[] solution = {4, 3, 2};
        Validator validator = new ValidatorFiveToSeven(solution, 1);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorCprOddNotOk() {
        int[] proposedNb = {2, 5, 3};
        int[] solution = {4, 2, 2};
        Validator validator = new ValidatorFiveToSeven(solution, 1);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }


}
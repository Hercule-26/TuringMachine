package g60131.atlg.turingmachine.model.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorElevenToThirteenTest {
    /*
        Validator 11 to 13 :
        Verify if the first and second element is smaller, equal or bigger;
    */
    @Test
    void validatorCprSmallerOk() {
        int[] proposedNb = {3, 4, 1};
        int[] solution = {1, 3, 2};
        Validator validator = new ValidatorElevenToThirteen(solution, 0, 1);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorCprSmallerNotOk() {
        int[] proposedNb = {3, 4, 1};
        int[] solution = {4, 3, 2};
        Validator validator = new ValidatorElevenToThirteen(solution, 0, 1);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }
    @Test
    void validatorCprEqualsOk() {
        int[] proposedNb = {4, 1, 4};
        int[] solution = {3, 5, 3};
        Validator validator = new ValidatorElevenToThirteen(solution, 0, 2);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorCprEqualsNotOk() {
        int[] proposedNb = {4, 1, 4};
        int[] solution = {3, 5, 1};
        Validator validator = new ValidatorElevenToThirteen(solution, 0, 2);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }
    @Test
    void validatorCprBiggerOk() {
        int[] proposedNb = {4, 4, 1};
        int[] solution = {3, 5, 3};
        Validator validator = new ValidatorElevenToThirteen(solution, 1, 2);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorCprBiggerNotOk() {
        int[] proposedNb = {4, 4, 1};
        int[] solution = {3, 1, 3};
        Validator validator = new ValidatorElevenToThirteen(solution, 1, 2);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }
}
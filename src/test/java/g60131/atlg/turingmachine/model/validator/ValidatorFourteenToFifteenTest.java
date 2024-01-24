package g60131.atlg.turingmachine.model.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorFourteenToFifteenTest {
    /*
        Validator 14 to 15 :
        Determinate with number in digit code is the smaller, bigger or no one of these two.
    */
    @Test
    void validatorSmallerOk() {
        int[] proposedNb = {4, 3, 1};
        int[] solution = {3, 4, 2};
        Validator validator = new ValidatorFourteenToFifteen(solution, true);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorSmallerNotOk() {
        int[] proposedNb = {4, 4, 1};
        int[] solution = {3, 2, 4};
        Validator validator = new ValidatorFourteenToFifteen(solution, true);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }
    @Test
    void validatorBiggerOk() {
        int[] proposedNb = {4, 3, 1};
        int[] solution = {5, 1, 2};
        Validator validator = new ValidatorFourteenToFifteen(solution, false);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorBiggerNotOk() {
        int[] proposedNb = {4, 3, 1};
        int[] solution = {1, 1, 2};
        Validator validator = new ValidatorFourteenToFifteen(solution, false);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }
    @Test
    void validatorNoOneOfTheseTwoOk() {
        int[] proposedNb = {4, 4, 4};
        int[] solution = {3, 3, 3};
        Validator validator = new ValidatorFourteenToFifteen(solution, true);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorNoOneOfTheseTwoNotOk() {
        int[] proposedNb = {1, 1, 2};
        int[] solution = {3, 3, 2};
        Validator validator = new ValidatorFourteenToFifteen(solution, false);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }
    @Test
    void validatorMinWithDuplicateOk() {
        int[] proposedNb = {4, 1, 1};
        int[] solution = {1, 1, 3};
        Validator validator = new ValidatorFourteenToFifteen(solution, true);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorMinWithDuplicateNotOk() {
        int[] proposedNb = {1, 4, 1};
        int[] solution = {3, 1, 3};
        Validator validator = new ValidatorFourteenToFifteen(solution, true);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }
    @Test
    void validatorMAxWithDuplicateOk() {
        int[] proposedNb = {1, 4, 4};
        int[] solution = {3, 3, 1};
        Validator validator = new ValidatorFourteenToFifteen(solution, false);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorMaxWithDuplicateNotOk() {
        int[] proposedNb = {1, 4, 1};
        int[] solution = {3, 1, 3};
        Validator validator = new ValidatorFourteenToFifteen(solution, false);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }
}
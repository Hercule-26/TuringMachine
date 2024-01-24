package g60131.atlg.turingmachine.model.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorOneToFourTest {
    @Test
    void validatorCprUnderOk() {
        int[] proposedNb = {2, 3, 1};
        int[] solution = {1, 4, 5};
        Validator validator = new ValidatorOneToFour(solution, 0, 3);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }
    @Test
    void validatorCprUnderNotOk() {
        int[] proposedNb = {2, 3, 1};
        int[] solution = {3, 4, 5};
        Validator validator = new ValidatorOneToFour(solution, 0, 3);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }

    @Test
    void validatorCprEqualOk() {
        int[] proposedNb = {3, 3, 1};
        int[] solution = {3, 4, 5};
        Validator validator = new ValidatorOneToFour(solution, 0, 3);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }

    @Test
    void validatorCprEqualNotOk() {
        int[] proposedNb = {5, 3, 1};
        int[] solution = {3, 4, 5};
        Validator validator = new ValidatorOneToFour(solution, 0, 3);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }

    @Test
    void validatorCprMoreOk() {
        int[] proposedNb = {5, 3, 1};
        int[] solution = {4, 4, 5};
        Validator validator = new ValidatorOneToFour(solution, 0, 3);
        boolean result = validator.launchValidator(proposedNb);
        assertTrue(result);
    }

    @Test
    void validatorCprMoreNotOk() {
        int[] proposedNb = {2, 3, 1};
        int[] solution = {5, 4, 5};
        Validator validator = new ValidatorOneToFour(solution, 0, 3);
        boolean result = validator.launchValidator(proposedNb);
        assertFalse(result);
    }

}
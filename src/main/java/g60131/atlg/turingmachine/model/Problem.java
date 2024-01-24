package g60131.atlg.turingmachine.model;

import g60131.atlg.turingmachine.model.validator.*;

import java.util.*;

public class Problem {
    private final List<Integer> problems;
    private final int[] solution;
    private final int difficulty;
    private final int luck;
    private final Map<Integer, Validator> validators = new HashMap<>();
    public Problem(List<Integer> problem) {
        this.difficulty = problem.get(1);
        this.luck = problem.get(2);
        this.solution = splitSolution(problem.get(3));
        this.problems = onlyValidators(problem);
        addValidator();
    }

    /**
     * Launches the specified validator with the proposed numbers.
     * @param index       The index of the validator.
     * @param proposedNb  The proposed numbers for validation.
     * @return True if the validation is successful, false otherwise.
     * @throws IllegalArgumentException If the specified validator is not found.
     */
    public boolean launchValidator(int index, int[] proposedNb) {
        if (validators.get(index) == null) {
            throw new IllegalArgumentException("The validator was not found");
        }
        Validator checkValidator = validators.get(index);
        return checkValidator.launchValidator(proposedNb);
    }

    /**
     * Retrieves the specified validator.
     * @param index The index of the validator.
     * @return The specified validator.
     * @throws IllegalArgumentException If the specified validator is not found.
     */
    public Validator getValidator(int index) {
        Validator validator = validators.get(index);
        if (validator == null) {
            throw new IllegalArgumentException("The validator was not found");
        }
        return validator;
    }

    /**
     * Gets a copy of the list of validator numbers.
     * @return A list of validator numbers.
     */
    public List<Integer> getValidator() {return new ArrayList<>(problems);}

    /**
     * Checks if the proposed numbers match the solution.
     * @param proposedNb The proposed numbers to be checked.
     * @return True if the proposed numbers match the solution, false otherwise.
     */
    public boolean checkSolution(int[] proposedNb) {
        for (int i = 0; i < solution.length; i++) {
            if (proposedNb[i] != solution[i]) return false;
        }
        return true;
    }

    /**
     * Gets the solution as a formatted string.
     * @return The solution as a formatted string.
     */
    public String getSolution() {
        return solution[0] + " " +solution[1] + " " + solution[2];
    }

    /**
     * Gets the difficulty level of the problem.
     * @return The difficulty level of the problem.
     */
    public int getDifficulty() {return difficulty;}

    /**
     * Gets the luck factor of the problem.
     * @return The luck factor of the problem.
     */
    public int getLuck() {return luck;}

    /**
     * Splits a three-digit number into an array of its digits.
     * @param number The three-digit number to be split.
     * @return An array containing the hundreds, tens, and units digits.
     */
    private int[] splitSolution(int number) {
        int hundred = number / 100;
        int moduloHundred = number % 100;
        int ten = moduloHundred / 10;
        int unit = moduloHundred % 10;

        return new int[]{hundred, ten, unit};
    }

    /**
     * Extracts and returns only the validator numbers from the problem description.
     * @param problem The list of integers representing the problem description.
     * @return A list of validator numbers.
     */
    private List<Integer> onlyValidators(List<Integer> problem) {
        List<Integer> validators = new LinkedList<>();
        for (int i = 4; i < problem.size(); i++) {
            validators.add(problem.get(i));
        }
        return validators;
    }

    /**
     * Adds validators to the internal map based on the problem's validator numbers.
     */
    private void addValidator() {
        for(int validator : problems) {
            switch (validator) {
                case 1 -> {
                    Validator validator1 = new ValidatorOneToFour(solution, 0, 1);
                    validators.put(1, validator1);
                }
                case 2 -> {
                    Validator validator2 = new ValidatorOneToFour(solution, 0, 3);
                    validators.put(2, validator2);
                }
                case 3 -> {
                    Validator validator3 = new ValidatorOneToFour(solution, 1, 3);
                    validators.put(3, validator3);
                }
                case 4 -> {
                    Validator validator4 = new ValidatorOneToFour(solution, 1, 4);
                    validators.put(4, validator4);
                }
                case 5 -> {
                    Validator validator5 = new ValidatorFiveToSeven(solution, 0);
                    validators.put(5, validator5);
                }
                case 6 -> {
                    Validator validator6 = new ValidatorFiveToSeven(solution, 1);
                    validators.put(6, validator6);
                }
                case 7 -> {
                    Validator validator7 = new ValidatorFiveToSeven(solution, 2);
                    validators.put(7, validator7);
                }
                case 8 -> {
                    Validator validator8 = new ValidatorEightToTen(solution, 1);
                    validators.put(8, validator8);
                }
                case 9 -> {
                    Validator validator9 = new ValidatorEightToTen(solution, 3);
                    validators.put(9, validator9);
                }
                case 10 -> {
                    Validator validator10 = new ValidatorEightToTen(solution, 4);
                    validators.put(10, validator10);
                }
                case 11 -> {
                    Validator validator11 = new ValidatorElevenToThirteen(solution, 0, 1);
                    validators.put(11, validator11);
                }
                case 12 -> {
                    Validator validator12 = new ValidatorElevenToThirteen(solution, 0, 2);
                    validators.put(12, validator12);
                }
                case 13 -> {
                    Validator validator13 = new ValidatorElevenToThirteen(solution, 1, 2);
                    validators.put(13, validator13);
                }
                case 14 -> {
                    Validator validator14 = new ValidatorFourteenToFifteen(solution, true);
                    validators.put(14, validator14);
                }
                case 15 -> {
                    Validator validator15 = new ValidatorFourteenToFifteen(solution, false);
                    validators.put(15, validator15);
                }
                case 16 -> {
                    Validator validator16 = new ValidatorSixteen(solution);
                    validators.put(16, validator16);
                }
                case 17 -> {
                    Validator validator17 = new ValidatorSeventeen(solution);
                    validators.put(17, validator17);
                }
                case 18 -> {
                    Validator validator18 = new ValidatorEighteen(solution);
                    validators.put(18, validator18);
                }
                case 19 -> {
                    Validator validator19 = new ValidatorNineTeen(solution);
                    validators.put(19, validator19);
                }
                case 20 -> {
                    Validator validator20 = new ValidatorTwenty(solution);
                    validators.put(20, validator20);
                }
                case 21 -> {
                    Validator validator21 = new ValidatorTwentyOne(solution);
                    validators.put(21, validator21);
                }
                case 22 -> {
                    Validator validator22 = new ValidatorTwentyTwo(solution);
                    validators.put(22, validator22);
                }
                default -> throw new IllegalArgumentException("Error with adding and checking validators");
            }
        }
    }
}

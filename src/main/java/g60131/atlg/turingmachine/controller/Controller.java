package g60131.atlg.turingmachine.controller;

import g60131.atlg.turingmachine.model.TuringMachine;
import g60131.atlg.turingmachine.util.Observer;
import g60131.atlg.turingmachine.view.ConsoleView;

import java.util.List;
import java.util.Scanner;

public class Controller implements Observer {
    public static final String RESET = "\033[0m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    private TuringMachine turingMachine;
    private final ConsoleView consoleView = new ConsoleView();
    private boolean quit = false;

    /**
     * Starts the Turing Machine game with the specified problem.
     * @param problem A list of integers representing the initial problem.
     */
    public void start(List<Integer> problem) {
        Scanner clavier = new Scanner(System.in);
        turingMachine = new TuringMachine(problem);
        turingMachine.register(this);

        int number = consoleView.askInt("Enter a number", 111, 555);
        int [] proposedSplit = splitNumber(number);
        while (!allNumber1To5(proposedSplit)) {
            System.out.println(RED + "Enter a digit code with all number is between 1 and 5" + RESET);
            number = consoleView.askInt("Enter a number", 111, 555);
            proposedSplit = splitNumber(number);
        }
        turingMachine.nextRound();
        turingMachine.setRoundProposedNb(proposedSplit);

        consoleView.displayCommand();
        while (!quit) {
            System.out.println("Enter a command");
            String command = clavier.nextLine();
            String[] commandPart = command.split("\\s+");
            if (executeCommand(commandPart)) {
                System.out.println(GREEN + "Command complete" + RESET);
            } else {
                System.out.println(RED + "Command invalid" + RESET);
            }
        }
    }

    /**
     * Executes the specified command parts.
     * @param commandPart An array of strings representing the command parts.
     * @return True if the command is executed successfully, false otherwise.
     */
    private boolean executeCommand(String[] commandPart) {
        switch (commandPart[0].toLowerCase()) {
            case "test" -> {
                return test(commandPart);
            }
            case "check" -> {
                if (check()) {
                    quit = true;
                } else {
                    while (!undoOrExit()) {
                        System.out.println(RED + "Command Invalid" + RESET);
                    }
                }
                return true;
            }
            case "next" -> {
                return nextRound();
            }
            case "undo" -> {
                turingMachine.undo();
                return true;
            }
            case "redo" -> {
                turingMachine.redo();
                return true;
            }
            case "help" -> {
                consoleView.displayCommand();
                return true;
            }
            case "exit" -> {
                quit = true;
                consoleView.displaySolution(turingMachine.getSolution());
                return true;
            }
            default -> {return false;}
        }
    }

    /**
     * Executes the "test" command to launch a validator.
     * @param commandPart An array of strings representing the command parts.
     * @return True if the "test" command is executed successfully, false otherwise.
     */
    private boolean test(String[] commandPart) {
        try {
            int validator = consoleView.convertToInteger(commandPart[1]);
            turingMachine.launchValidator(validator);
            return true;
        } catch (Exception e) {
            if (e.getClass() == IllegalArgumentException.class) {
                System.out.println(RED + e.getMessage() + RESET);
            }
            return false;
        }
    }

    /**
     * Executes the "check" command to check the solution.
     * @return True if the "check" command is executed successfully, false otherwise.
     */
    private boolean check() {
        try {
            boolean result = turingMachine.checkSolution();
            if (result) {
                System.out.println(YELLOW + "Congratulation you found the code !!! :)" + RESET);
                System.out.println("Your stat : ");
                consoleView.displayStat(turingMachine.getRound(), turingMachine.getValidatorTested());
                return true;
            } else {
                System.out.println(RED + "Your code is not the solution :(" + RESET);
                return false;
            }
        } catch (Exception e) {
            System.out.println(RED + e.getMessage() + RESET);
            return false;
        }
    }

    /**
     * Initiates the next round with new proposed numbers.
     * @return True if the next round is initiated successfully, false otherwise.
     */
    private boolean nextRound() {
        int number = consoleView.askInt("Enter a digit code with all number is between 1 and 5", 111, 555);
        int[] proposedSplit = splitNumber(number);
        while (!allNumber1To5(proposedSplit)) {
            System.out.println(RED + "Enter a digit code with all number is between 1 and 5" + RESET);
            number = consoleView.askInt("Enter a number", 111, 555);
            proposedSplit = splitNumber(number);
        }
        turingMachine.nextRound();
        turingMachine.setRoundProposedNb(proposedSplit);
        return true;
    }

    /**
     * Prompts the user to choose between "undo" and "exit" commands.
     * @return True if the user chooses "undo" or "exit," false otherwise.
     */
    private boolean undoOrExit() {
        consoleView.displayUndoOrExit();
        Scanner clavier = new Scanner(System.in);
        String command;
        boolean commandOk = false;
        command = clavier.nextLine();
        while (!commandOk) {
            switch (command.toLowerCase()) {
                case "undo" -> {
                    commandOk = true;
                }
                case "exit" -> {
                    quit = true;
                    consoleView.displaySolution(turingMachine.getSolution());
                    commandOk = true;
                }
                default -> {
                }
            }
        }
        return true;
    }
    /**
     * Asks the user a yes-or-no question.
     * @param message The message to be displayed to the user.
     * @return The user's response (yes or no).
     */
    public String askYesOrNo(String message) {
        return  consoleView.askYesOrNo(message);
    }

    /**
     * Asks the user for an integer input.
     * @param message The message to be displayed to the user.
     * @return The user's input as an integer.
     */
    public int askInt(String message) {
        return consoleView.askInt(message);
    }

    /**
     * Retrieves the problem at the specified index.
     * @param index The index of the problem.
     * @return A list of integers representing the problem.
     */
    public List<Integer> getProblem(int index) {
        return consoleView.getProblem(index);
    }

    /**
     * Displays the current problem to the user.
     */
    public void displayProblem() {
        consoleView.displayProblem();
    }

    /**
     * Splits a three-digit number into an array of its digits.
     * @param number The three-digit number to be split.
     * @return An array containing the hundreds, tens, and units digits.
     */
    private int[] splitNumber(int number) {
        int hundred = number / 100;
        int moduloHundred = number % 100;
        int ten = moduloHundred / 10;
        int unit = moduloHundred % 10;
        return new int[]{hundred, ten, unit};
    }

    /**
     * Checks if all numbers in the array are between 1 and 5 (inclusive).
     * @param proposedNb The array of numbers to be checked.
     * @return True if all numbers are between 1 and 5, false otherwise.
     */
    private boolean allNumber1To5(int[] proposedNb) {
        for (int element : proposedNb) {
            if (element < 1 || 5 < element) {
                return false;
            }
        }
        return true;
    }

    /**
     * Updates the game state and displays relevant information to the user.
     */
    @Override
    public void update() {
        if (turingMachine.getProposedNb() != null) {
            stat();
            consoleView.displayValidator(turingMachine.getValidators());
        }
    }

    /**
     * Displays statistical information about the current game state.
     */
    private void stat() {
        int round = turingMachine.getRound();
        int validatorTested = turingMachine.getValidatorTested();
        int[] proposedNb = turingMachine.getProposedNb();
        consoleView.displayStat(round, validatorTested, proposedNb);
    }
}

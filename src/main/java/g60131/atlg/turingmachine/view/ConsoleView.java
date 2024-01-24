package g60131.atlg.turingmachine.view;

import java.io.*;
import java.util.*;

public class ConsoleView {
    private static final String RESET = "\033[0m";
    private static final String RED = "\033[0;31m";
    private static final String GREEN = "\033[0;32m";
    private final List<List<Integer>> problems = new LinkedList<>();

    public ConsoleView() {
        bufferReader();
    }

    public void displayValidator(Map<Integer, Boolean> validators) {
        System.out.println("Validators : ");
        for (Map.Entry<Integer, Boolean> entry : validators.entrySet()) {
            if (entry.getValue() == null) {
                validator(entry.getKey(),"");
            } else if (entry.getValue()){
                validator(entry.getKey(), GREEN + " V " + RESET);
            } else {
                validator(entry.getKey(), RED + " X " + RESET);
            }
        }
    }

    /**
     * Displays available commands to the console.
     */
    public void displayCommand() {
        System.out.println("========================================");
        System.out.println("Commands : ");
        System.out.println(" - test (validator number)");
        System.out.println(" - check (check your code with solution)");
        System.out.println(" - next (next round)");
        System.out.println(" - undo");
        System.out.println(" - redo");
        System.out.println(" - help");
        System.out.println(" - exit");
        System.out.println("========================================");
    }

    /**
     * Displays statistical information about the current game state, including the proposed code.
     * @param nbRound The current round number.
     * @param nbValidatorTested The number of validators tested.
     * @param proposedNb The proposed code for the round.
     */
    public void displayStat(int nbRound, int nbValidatorTested, int[] proposedNb) {
        System.out.println("Round : " + nbRound + " | Validator(s) tested : " + nbValidatorTested);
        System.out.print("Your Code : ");
        for (int i : proposedNb) {
            System.out.print(i);
        }
        System.out.println();
    }

    /**
     * Displays statistical information about the current game state.
     * @param nbRound The current round number.
     * @param nbValidatorTested The number of validators tested.
     */
    public void displayStat(int nbRound, int nbValidatorTested) {
        System.out.println("Round : " + nbRound);
        System.out.println("Validator(s) tested : " + nbValidatorTested);
    }

    /**
     * Displays a prompt for the user to choose between "undo" and "exit."
     */
    public void displayUndoOrExit() {
        System.out.println("Undo or Exit ?");
    }

    /**
     * Displays the list of problems with their respective difficulty and luck.
     */
    public void displayProblem() {
        for (List<Integer> problem : problems) {
            System.out.println("Problem n°" + problem.get(0) + " : "
                            + "Difficulty : " + problem.get(1)
                            + " Luck : " + problem.get(2));
        }
    }

    /**
     * Displays the solution to the user.
     * @param solution The correct solution.
     */
    public void displaySolution(String solution) {
        System.out.println("The solution was : " + solution);
    }

    /**
     * Retrieves the problem at the specified index.
     * @param index The index of the problem.
     * @return A list of integers representing the problem.
     */
    public List<Integer> getProblem(int index) {
        if(index <= 0 || index > problems.size()) {
            System.out.println(RED + "Problem was not found" + RESET);
            return null;
        }
        return problems.get(index-1);
    }

    /**
     * Asks the user a yes-or-no question and returns the user's response.
     * @param message The message to be displayed to the user.
     * @return The user's response (either "y" or "n").
     */
    public String askYesOrNo(String message) {
        Scanner clavier = new Scanner(System.in);
        System.out.println(message);
        String yesOrNo = clavier.next().toLowerCase();
        while (!yesOrNo.equals("y") && !yesOrNo.equals("n")) {
            System.out.println(RED + "Enter y or n" + RESET);
            yesOrNo = clavier.next().toLowerCase();
        }
        return yesOrNo;
    }

    /**
     * Asks the user for an integer input within a specified range.
     * @param message The message to be displayed to the user.
     * @param min The minimum allowed value.
     * @param max The maximum allowed value.
     * @return The user's input as an integer within the specified range.
     */
    public int askInt(String message, int min, int max) {
        int value = askInt(message + " (between "+ min +" and "+ max +")");
        while (! (min <= value && value <= max)) {
            System.out.println(RED + "the value is not between "+ min +" and "+ max + RESET);
            value = askInt(message + "(between "+ min +" and "+ max +")");
        }
        return value;
    }

    /**
     * Asks the user for an integer input.
     * @param message The message to be displayed to the user.
     * @return The user's input as an integer.
     */
    public int askInt(String message) {
        Scanner clavier = new Scanner(System.in);
        System.out.println(GREEN + message + RESET);
        while (!clavier.hasNextInt()) {
            clavier.next();
            System.out.println(RED + "Is not a value !" + RESET);
            System.out.println(GREEN + message + RESET);
        }
        return clavier.nextInt();
    }

    /**
     * Reads problems from a CSV file and populates the problems list.
     */
    public void bufferReader() {
        String csvFile = "src/main/resources/TuringMachine-assets/known_problems.csv";
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                List<String> problemStr = new LinkedList<>();
                while (tokenizer.hasMoreTokens()) {
                    String column = tokenizer.nextToken();
                    problemStr.add(column);
                }
                problems.add(convertToInteger(problemStr));
            }
            br.close();
        } catch (IOException e) {
            System.out.println(RED + "Problem with the csv file reader" + RESET);
        }
    }

    /**
     * Converts a list of strings to a list of integers.
     * @param problemSplitStr The list of strings representing a problem.
     * @return A list of integers representing the problem.
     */
    private List<Integer> convertToInteger(List<String> problemSplitStr) {
        List<Integer> commandToInt = new LinkedList<>();
        for (String s : problemSplitStr) {
            try {
                commandToInt.add(Integer.parseInt(s));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Incorrect problem value (conversion to integer)");
            }
        }
        return commandToInt;
    }

    /**
     * Converts a string to an integer.
     * @param s The string to be converted.
     * @return The converted integer.
     */
    public int convertToInteger(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Problem with the conversion (conversion to integer)");
        }
    }

    /**
     * Displays information about a specific validator.
     * @param validator The validator number.
     * @param msg Additional message information.
     */
    private void validator(int validator, String msg) {
        switch (validator) {
            case 1 -> {
                System.out.println(validator + " : Verify if the first digit code is smaller, equals or bigger than 1" + msg);
            }
            case 2 -> {
                System.out.println(validator + " : Verify if the first digit code is smaller, equals or bigger than 3" + msg);
            }
            case 3 -> {
                System.out.println(validator + " : Verify if the second digit code is smaller, equals or bigger than 3" + msg);
            }
            case 4 -> {
                System.out.println(validator + " : Verify if the second digit code is smaller, equals or bigger than 4" + msg);
            }
            case 5 -> {
                System.out.println(validator + " : Verify if the first digit code is even or odd" + msg);
            }
            case 6 -> {
                System.out.println(validator + " : Verify if the second digit code is even or odd" + msg);
            }
            case 7 -> {
                System.out.println(validator + " : Verify if the third digit code is even or odd" + msg);
            }
            case 8 -> {
                System.out.println(validator + " : Count how much time they are the number 1 in your code" + msg);
            }
            case 9 -> {
                System.out.println(validator + " : Count how much time they are the number 3 in your code" + msg);
            }
            case 10 -> {
                System.out.println(validator + " : Count how much time they are the number 4 in your code" + msg);
            }
            case 11 -> {
                System.out.println(validator + " : Compare the first and the second number in your code if it's smaller, equals or bigger" + msg);
            }
            case 12 -> {
                System.out.println(validator + " : Compare the first and the third number in your code if it's smaller, equals or bigger" + msg);
            }
            case 13 -> {
                System.out.println(validator + " : Compare the second and the third number in your code if it's smaller, equals or bigger" + msg);
            }
            case 14 -> {
                System.out.println(validator + " : Look witch number in your code is the smaller one (or they are not)" + msg);
            }
            case 15 -> {
                System.out.println(validator + " : Look witch number in your code is the bigger one (or they are not)" + msg);
            }
            case 16 -> {
                System.out.println(validator + " : Verify if they are more even number or odd number in your code" + msg);
            }
            case 17 -> {
                System.out.println(validator + " : Count of much even number they are in your code" + msg);
            }
            case 18 -> {
                System.out.println(validator + " : Verify if the sum of all number in your code is even or odd" + msg);
            }
            case 19 -> {
                System.out.println(validator + " : Compare the sum of the first and second number if it's smaller, equals or bigger than 6" + msg);
            }
            case 20 -> {
                System.out.println(validator + " : Look if they are number repetition (no repetition, in double or in triple)" + msg);
            }
            case 21 -> {
                System.out.println(validator + " : Verify if they are a number in exactly 2 time" + msg);
            }
            case 22 -> {
                System.out.println(validator + " : Verify if your code is ascending, descending or no one of these 2" + msg);
            }
            default -> throw new IllegalArgumentException("Error with Displaying validators");
        }
    }
}
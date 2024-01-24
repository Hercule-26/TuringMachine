package g60131.atlg.turingmachine.model;

import g60131.atlg.turingmachine.model.validator.Validator;
import g60131.atlg.turingmachine.util.Command;
import g60131.atlg.turingmachine.util.CommandManager;
import g60131.atlg.turingmachine.util.Observable;
import g60131.atlg.turingmachine.util.Observer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TuringMachine implements Observable {
    private final List<Observer> observers = new LinkedList<>();
    private boolean firstRound = true;
    private int validatorTested = 0;
    private int nbRound = 0;
    private final Problem problem;
    private Round currentRound;
    private final CommandManager commandManager = new CommandManager();
    public TuringMachine(List<Integer> problem) {
        this.problem = new Problem(problem);
    }

    /**
     * Launches the validator with the specified index in the current round.
     * @param index The index of the validator to be launched.
     * @throws IllegalArgumentException If the maximum number of validators (3) for the current round is reached.
     */
    public void launchValidator(int index) {
        int max3Test = currentRound.getNbValidatorsTested();
        if (max3Test == 3) {
            throw new IllegalArgumentException("Your can test max 3 validators");
        }
        Validator validator = problem.getValidator(index);
        Command command = new CommandLaunchValidator(this, currentRound, validator, validatorTested);
        commandManager.newCommand(command);
        notifyObserver();
    }

    /**
     * Retrieves the result of the validator with the specified index in the last round.
     * @param index The index of the validator.
     * @return The result of the specified validator (true if passed, false if failed).
     */
    public Boolean getValidatorResult(int index) {
        Validator validator = problem.getValidator(index);
        return currentRound.getValidatorResult(validator);
    }

    /**
     * Checks if the proposed numbers in the last round match the solution.
     * @return True if the proposed numbers match the solution, false otherwise.
     */
    public boolean checkSolution() {
        int[] proposedNb = currentRound.getProposedNb();
        return problem.checkSolution(proposedNb);
    }

    /**
     * Initiates the next round with the specified proposed numbers.
     */
    public void nextRound() {
        if (firstRound) {
            currentRound = new Round();
            nbRound++;
            firstRound = false;
        } else {
            Command command = new CommandNextRound(this, currentRound, nbRound);
            commandManager.newCommand(command);
        }
        notifyObserver();
    }

    /**
     * Set the digit code of the current round
     * @param proposedNb the digit code to set in the current round.
     */
    public void setRoundProposedNb(int[] proposedNb) {
        currentRound.setProposedNb(proposedNb);
        notifyObserver();
    }

    /**
     * Undoes the last command in the command history.
     */
    public void undo() {
        commandManager.undo();
        notifyObserver();
    }

    /**
     * Redoes the last undone command in the command history.
     */
    public void redo() {
        commandManager.redo();
        notifyObserver();
    }

    /**
     * Registers an observer to receive updates from the game.
     * @param obs The observer to be registered.
     */
    @Override
    public void register(Observer obs) {
        observers.add(obs);
    }

    /**
     * Unregisters an observer, stopping it from receiving updates from the game.
     * @param obs The observer to be unregistered.
     */
    @Override
    public void unregister(Observer obs) {
        observers.remove(obs);
    }

    /**
     * Notifies all registered observers about changes in the game state.
     */
    public void notifyObserver() {
        for (Observer obs : observers) {
            obs.update();
        }
    }

    public void setCurrentRound(Round currentRound) {
        this.currentRound = currentRound;
    }

    /**
     * set the number of round(s)
     * @param nbRound number of round(s)
     */
    public void setNbRound(int nbRound) {
        this.nbRound = nbRound;
    }

    public void setValidatorTested(int validatorTested) {
        this.validatorTested = validatorTested;
    }

    /**
     * Retrieves the solution to the current problem.
     * @return The solution as a formatted string.
     */
    public String getSolution() {
        return problem.getSolution();
    }

    /**
     * Gets the total number of validators tested across all rounds.
     * @return The number of validators tested.
     */
    public int getValidatorTested() {
        return validatorTested;
    }
    /**
     * Gets the proposed numbers from the last round.
     * @return The array of proposed numbers from the last round.
     */
    public int[] getProposedNb() {return currentRound.getProposedNb();}

    /**
     * Gets the difficulty level of the current problem.
     * @return The difficulty level of the current problem.
     */
    public int getDifficulty() {return problem.getDifficulty();}

    /**
     * Gets the luck factor of the current problem.
     * @return The luck factor of the current problem.
     */
    public int getLuck() {return problem.getLuck();}

    /**
     * Gets the current round number.
     * @return The number of round(s).
     */
    public int getRound() {
        return nbRound;
    }

    /**
     * Retrieves the results of all validators tested in the last round.
     * @return A map where the keys are validator indices and the values are their corresponding results.
     */
    public Map<Integer, Boolean> getValidators() {
        Map<Integer, Boolean> validators = new HashMap<>();
        for (int index : problem.getValidator()) {
            validators.put(index, currentRound.getValidatorResult(problem.getValidator(index)));
        }
        return validators;
    }
}

package g60131.atlg.turingmachine.model;

import g60131.atlg.turingmachine.util.Command;

public class CommandNextRound implements Command {
    private final Round oldRound;
    private final Round newRound;
    private final TuringMachine turingMachine;
    private int nbRound;

    public CommandNextRound(TuringMachine turingMachine, Round round, int nbRound) {
        this.turingMachine = turingMachine;
        this.oldRound = round;
        this.nbRound = nbRound;
        newRound = new Round();
    }

    @Override
    public void execute() {
        nbRound++;
        turingMachine.setCurrentRound(newRound);
        turingMachine.setNbRound(nbRound);
    }

    @Override
    public void unExecute() {
        nbRound--;
        turingMachine.setCurrentRound(oldRound);
        turingMachine.setNbRound(nbRound);
    }
}

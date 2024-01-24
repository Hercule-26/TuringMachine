package g60131.atlg.turingmachine.util;

public interface Command {
    /**
     * Execute a command
     */
    void execute();

    /**
     * Unexecute a command
     */
    void unExecute();
}

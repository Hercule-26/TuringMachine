package g60131.atlg.turingmachine.util;

import java.util.Stack;

public class CommandManager {
    private final Stack<Command> undoStack = new Stack<>();
    private final Stack<Command> redoStack = new Stack<>();

    /**
     * Undo (unexecute) a command
     */
    public void undo() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            command.unExecute();
            redoStack.push(command);
        }
    }

    /**
     * Redo (execute) a command
     */
    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.execute();
            undoStack.push(command);
        }
    }

    /**
     * Add a new command
     * @param command the command to execute and add
     */
    public void newCommand(Command command) {
        command.execute();
        undoStack.add(command);
        redoStack.clear();
    }
}

package g60131.atlg.turingmachine.view;

import g60131.atlg.turingmachine.model.TuringMachine;
import g60131.atlg.turingmachine.util.Observer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class FxGame extends VBox implements Observer {
    private final Stage stage;
    private final VBox root;
    private final VBox start;
    private final List<Integer> problem;
    private final TuringMachine turingMachine;
    private final FxInfo info;
    private final FxValidatorList validators;
    private final FxCodeInput input;
    private HBox controllerButtons;
    private final FxScore score;

    public FxGame(List<Integer> problem, Stage stage, VBox start, VBox root) {
        this.stage = stage;
        this.root = root;
        this.start = start;
        this.problem = problem;
        this.turingMachine = new TuringMachine(this.problem);
        turingMachine.nextRound(); // first Round.
        this.turingMachine.register(this);

        this.info = new FxInfo(problem.get(0), turingMachine.getDifficulty(), turingMachine.getLuck());
        this.validators = new FxValidatorList(turingMachine);
        this.input = new FxCodeInput(turingMachine);

        validators.setDisable(true);
        this.setAlignment(Pos.TOP_CENTER);
        this.getChildren().addAll(info, validators, input);
        this.setSpacing(15);
        initValidators();
        initButtons();
        score = new FxScore(turingMachine);
    }

    /**
     * The ValidatorInitializer class initializes validators based on the given problem.
     */
    private void initValidators() {
        for (int i = 4; i < problem.size(); i++) {
            switch (i-3) {
                case 1 -> {
                    validators.addValidator(problem.get(i), "A");
                }
                case 2 -> {
                    validators.addValidator(problem.get(i), "B");
                }
                case 3 -> {
                    validators.addValidator(problem.get(i), "C");
                }
                case 4 -> {
                    validators.addValidator(problem.get(i), "D");
                }
                case 5 -> {
                    validators.addValidator(problem.get(i), "E");
                }
                case 6 -> {
                    validators.addValidator(problem.get(i), "F");
                }
            }
        }
    }

    /**
     * The ButtonInitializer class initializes buttons for the graphical user interface.
     */
    private void initButtons() {
        Button undo = new Button("Undo");
        undo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                turingMachine.undo();
            }
        });
        Button redo = new Button("Redo");
        redo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                turingMachine.redo();
            }
        });
        Button nextRound = new Button("Next round");
        nextRound.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                turingMachine.nextRound();
            }
        });
        Button result = new Button("Check result");
        result.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                finalAlert(turingMachine.checkSolution());
            }
        });
        Button score = new Button("Show score");
        score.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!isShowing()) {
                    showScore();
                }
            }
        });
        controllerButtons = new HBox(undo, redo, nextRound, result, score);
        controllerButtons.setSpacing(10);
        controllerButtons.setAlignment(Pos.CENTER);
        controllerButtons.setPadding(new Insets(0, 0, 15, 0));
        controllerButtons.setDisable(true);
        this.getChildren().add(controllerButtons);
    }

    private boolean isShowing() {
        return score.isShowing();
    }
    private void showScore() {
        score.showScore();
    }
    @Override
    public void update() {
        if (turingMachine.getProposedNb() == null) {
            validators.setDisable(true);
            input.setEnable();
            validators.clear();
            input.clearNumber();
            controllerButtons.setDisable(true);
        } else {
            validators.setDisable(false);
            controllerButtons.setDisable(false);
            input.setDisable();
            int[] proposedNb = turingMachine.getProposedNb();
            input.setCode(proposedNb);
            updateInfo();
            updateValidators();
        }
    }

    /**
     * Updates the information displayed on the graphical user interface.
     */
    private void updateInfo() {
        info.refresh(turingMachine.getRound(), turingMachine.getValidatorTested());
    }

    /**
     * Updates the display of validators on the graphical user interface.
     */
    private void updateValidators() {
        for (int i = 4; i < problem.size(); i++) {
            int validatorIndex = problem.get(i);
            validators.refresh(validatorIndex, turingMachine.getValidatorResult(validatorIndex));
        }
    }

    /**
     * Displays a final alert to the user based on the result of the game.
     * @param result The result of the game (true if the code is correct, false otherwise).
     */
    private void finalAlert(boolean result) {
        if (result) {
            alertGameFinish();
        } else {
            alertGameLose();
        }
    }

    /**
     * Pop-up an alert about the end of the game when the user found the code
     */
    private void alertGameFinish() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Congratulation");
        alert.setContentText("Congratulation, you found the code !!!");

        ButtonType exit = new ButtonType("Exit");
        ButtonType restart = new ButtonType("restart");
        alert.getButtonTypes().setAll(restart, exit);
        alert.showAndWait().ifPresent(response -> {
            if (response == exit) {
                alert.close();
                stage.close();
            } else if (response == restart) {
                root.getChildren().remove(this);
                root.getChildren().add(start);
            }
        });
    }

    /**
     * Pop-up an alert about the end of the game when the user didn't found the code
     */
    private void alertGameLose() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Sorry");
        alert.setContentText("You don't found the code."
                + "\nThe code was : " + turingMachine.getSolution());

        ButtonType undo = new ButtonType("Undo");
        ButtonType restart = new ButtonType("restart");
        ButtonType exit = new ButtonType("Exit");
        alert.getButtonTypes().setAll(undo, restart, exit);
        alert.showAndWait().ifPresent(response -> {
            if (response == undo) {
                alert.close();
            } else if (response == exit) {
                alert.close();
                stage.close();
            } else if (response == restart) {
                root.getChildren().remove(this);
                root.getChildren().add(start);
            }
        });
    }
}

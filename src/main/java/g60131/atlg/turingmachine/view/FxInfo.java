package g60131.atlg.turingmachine.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class FxInfo extends HBox {
    private final Label problemNumber = new Label("");
    private final Label difficulty = new Label("");
    private final Label luck = new Label("");
    private final Label round = new Label("Round : ");
    private final Label validatorsTested = new Label("All validator(s) tested : ");
    public FxInfo(int problemNb, int difficulty, int luck) {
        this.setSpacing(15);
        this.setPadding(new Insets(5, 0, 0, 15));
        problemNumber.setFont(new Font(16));
        this.difficulty.setFont(new Font(16));
        this.luck.setFont(new Font(16));
        round.setFont(new Font(16));
        validatorsTested.setFont(new Font(16));
        round.setAlignment(Pos.CENTER);
        validatorsTested.setAlignment(Pos.CENTER);

        setProblemNumber(problemNb);
        setDifficulty(difficulty);
        setLuck(luck);
        this.getChildren().addAll(problemNumber, this.difficulty, this.luck, round, validatorsTested);
    }

    /**
     * Refreshes the display with the current round and the number of validators tested.
     * @param round The current round number.
     * @param validatorsTested The number of validators tested.
     */
    public void refresh(int round, int validatorsTested) {
        this.round.setText("Round : " + round);
        this.validatorsTested.setText("All validator(s) tested : " + validatorsTested);
    }

    /**
     * Sets the luck value and updates the display.
     * @param luck The luck value to be set.
     */
    public void setLuck(int luck) {
        this.luck.setText("Luck : " + luck);
    }

    /**
     * Sets the difficulty value and updates the display.
     * @param difficulty The difficulty value to be set.
     */
    public void setDifficulty(int difficulty) {
        this.difficulty.setText("Difficulty : " + difficulty);
    }

    /**
     * Sets the problem number and updates the display.
     * @param problemNumber The problem number to be set.
     */
    public void setProblemNumber(int problemNumber) {
        this.problemNumber.setText("Problem n°" + problemNumber);
    }
}

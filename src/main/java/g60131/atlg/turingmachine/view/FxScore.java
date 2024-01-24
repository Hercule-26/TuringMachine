package g60131.atlg.turingmachine.view;

import g60131.atlg.turingmachine.model.TuringMachine;
import g60131.atlg.turingmachine.util.Observer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FxScore extends VBox implements Observer {
    private final TuringMachine turingMachine;
    private Label round;
    private Label validatorsTested;
    private final Stage stage;

    public FxScore(TuringMachine turingMachine) {
        this.turingMachine = turingMachine;
        turingMachine.register(this);

        this.setSpacing(10);
        this.setPadding(new Insets(15, 15, 15, 15));
        this.setBackground(Background.fill(Paint.valueOf("#70cd77")));

        initLabel();

        var scene = new Scene(this, 300, 150);

        stage = new Stage();
        stage.setScene(scene);
    }

    private void initLabel() {
        round = new Label();
        round.setFont(new Font(20));

        validatorsTested = new Label();
        validatorsTested.setFont(new Font(20));

        this.getChildren().addAll(round, validatorsTested);
    }

    @Override
    public void update() {
        round.setText("Round : " + turingMachine.getRound());
        validatorsTested.setText("Validator(s) tested : " + turingMachine.getValidatorTested());

    }

    public void showScore() {
        stage.show();
    }
    public boolean isShowing() {
        return stage.isShowing();
    }
}

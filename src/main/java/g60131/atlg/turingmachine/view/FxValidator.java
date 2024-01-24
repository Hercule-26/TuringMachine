package g60131.atlg.turingmachine.view;

import g60131.atlg.turingmachine.model.TuringMachine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class FxValidator extends VBox {
    private Rectangle validatorResult;
    private final HBox buttonAndResult = new HBox();
    private final TuringMachine turingMachine;
    private final int validatorIndex;
    public FxValidator(TuringMachine turingMachine, int validatorIndex, String letter) {
        this.turingMachine = turingMachine;
        this.validatorIndex = validatorIndex;
        this.setSpacing(5);
        this.setAlignment(Pos.CENTER);

        String card = "/TuringMachine-assets/card" + validatorIndex +".png";
        Image cardImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(card)));
        ImageView cardImageView = new ImageView(cardImage);
        cardImageView.setPreserveRatio(true);
        cardImageView.setFitWidth(250);

        String robot = "/TuringMachine-assets/robot" + letter +".png";
        Image robotImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(robot)));
        ImageView robotImageView = new ImageView(robotImage);
        robotImageView.setPreserveRatio(true);
        robotImageView.setFitWidth(50);

        this.getChildren().addAll(robotImageView, cardImageView);
        initButtonAndResult();
    }
    /**
     * Sets the color of the validator result indicator based on the provided result.
     * @param result The result of the validation. If null, sets the color to white.
     * If true, sets the color to green. If false, sets the color to red.
     */
    public void setResult(Boolean result) {
        if (result == null) {
            validatorResult.setFill(Color.WHITE);
        } else if(result) {
            validatorResult.setFill(Color.GREEN);
        } else {
            validatorResult.setFill(Color.RED);
        }
    }

    /**
     * Clears the validator result indicator by setting the color to white.
     */
    public void clear() {
        validatorResult.setFill(Color.WHITE);
    }

    /**
     * Initializes the button and result components, setting up their appearance and behavior.
     */
    private void initButtonAndResult() {
        buttonAndResult.setSpacing(10);
        validatorResult = new Rectangle(135, 25, Color.WHITE);
        validatorResult.setStroke(Color.BLACK);
        validatorResult.setArcWidth(20);
        validatorResult.setArcHeight(20);
        Button button = new Button("Launch Validator");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    turingMachine.launchValidator(validatorIndex);
                } catch (Exception e) {
                    alert(e.getMessage());
                }

            }
        });
        buttonAndResult.getChildren().addAll(button, validatorResult);
        this.getChildren().add(buttonAndResult);
    }

    /**
     * Displays an alert with the specified error message.
     * @param message The error message to be displayed in the alert.
     */
    private void alert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Incorrect");
        alert.setContentText(message);
        alert.show();
    }
}
